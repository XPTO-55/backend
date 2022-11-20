package br.com.cpa.spring.modules.user.patient.services;

import br.com.cpa.spring.errors.ResourceAlreadyExists;
import br.com.cpa.spring.models.Address;
import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.models.Role;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import br.com.cpa.spring.modules.user.patient.dtos.CreatePatientDTO;

import br.com.cpa.spring.repositories.AddressRepository;
import br.com.cpa.spring.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

// Diz que essa classe uma service do sistema (Camada que contém a regra de negócio do sistema)
@Service
public class CreatePatientService {
    // Injeta(importa) o repository (Camada do sistema que manipula o banco de dados)
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Patient execute(CreatePatientDTO createUserData) {
        try {
            Patient emailAlreadyExists = patientRepository.findByEmail(createUserData.getEmail());

            if (emailAlreadyExists != null) {
                throw new ResourceAlreadyExists("Email já cadastrado");
            }

            Patient patient = new Patient();
            patient.setName(createUserData.getName());
            patient.setCpf(createUserData.getCpf());
            patient.setTelefoneFixo(createUserData.getAddressline());
            patient.setTelefoneCelular(createUserData.getPhone());
            patient.setDataDeNascimento(createUserData.getBirthday());
            patient.setEmail(createUserData.getEmail());
            patient.setAbout(createUserData.getAbout());
            patient.setPassword(passwordEncoder().encode(createUserData.getPassword()));

            if (createUserData.getAddress() != null) {
                Address address = new Address();
                address.setStreet(createUserData.getAddress().getStreet());
                address.setDistrict(createUserData.getAddress().getDistrict());
                address.setNumber(createUserData.getAddress().getNumber());
                address.setComplement(createUserData.getAddress().getComplement());
                address.setZipcode(createUserData.getAddress().getZipcode());
                address.setCity(createUserData.getAddress().getCity());
                address.setUf(createUserData.getAddress().getUf());
                addressRepository.save(address);
                patient.setAddress(address);
            }

            Role roleUser = roleRepository.findByName("USER");
            patient.addRole(roleUser);

            return patientRepository.save(patient);
        } catch (Exception err) {
            err.printStackTrace();
            System.out.println(err.getMessage());
            return null;
        }
    }
}
