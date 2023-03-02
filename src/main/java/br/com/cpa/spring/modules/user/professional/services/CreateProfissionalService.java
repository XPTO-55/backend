package br.com.cpa.spring.modules.user.professional.services;

import br.com.cpa.spring.errors.ResourceAlreadyExists;
import br.com.cpa.spring.models.Address;
import br.com.cpa.spring.models.Profissional;
import br.com.cpa.spring.models.Role;
import br.com.cpa.spring.modules.user.professional.ProfissionalRepository;
import br.com.cpa.spring.modules.user.professional.dtos.CreateProfissionalDTO;
import br.com.cpa.spring.repositories.AddressRepository;
import br.com.cpa.spring.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// Diz que essa classe uma service do sistema (Camada que contém a regra de negócio do sistema)
@Service
public class CreateProfissionalService {
    // Injeta(importa) o repository (Camada do sistema que manipula o banco de dados)
    @Autowired
    private ProfissionalRepository profissionalRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Profissional execute(CreateProfissionalDTO createUserData) {
        try {
            Profissional emailAlreadyExists = profissionalRepository.findByEmail(createUserData.getEmail());

            if (emailAlreadyExists != null) {
                throw new ResourceAlreadyExists("Email já cadastrado");
            }

            Profissional profissional = new Profissional();
            profissional.setName(createUserData.getName());
            profissional.setCpf(createUserData.getCpf());
            profissional.setAbout(createUserData.getAbout());
            profissional.setGraduacao(createUserData.getGraduacao());
            profissional.setEspecialidade(createUserData.getEspecialidade());
            profissional.setLandline(createUserData.getLandline());
            profissional.setPhone(createUserData.getPhone());
            profissional.setBirthday(createUserData.getBirthday());
            profissional.setEmail(createUserData.getEmail());
            profissional.setPassword(passwordEncoder().encode(createUserData.getPassword()));

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
                profissional.setAddress(address);
            }

            Role roleUser = roleRepository.findByName("PROFISSIONAL");
            profissional.addRole(roleUser);

            return profissionalRepository.save(profissional);
        } catch (Exception err) {
            err.printStackTrace();
            System.out.println(err.getMessage());
            return null;
        }
    }
}
