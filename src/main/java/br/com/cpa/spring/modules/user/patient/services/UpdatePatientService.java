package br.com.cpa.spring.modules.user.patient.services;

import br.com.cpa.spring.models.Address;
import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import br.com.cpa.spring.modules.user.patient.dtos.UpdatePatientDTO;
import br.com.cpa.spring.repositories.AddressRepository;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

// Diz que essa classe uma service do sistema (Camada que contém a regra de negócio do sistema)
@Service
public class UpdatePatientService {
    // Injeta(importa) o repository (Camada do sistema que manipula o banco de dados)
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AddressRepository addressRepository;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Patient execute(Long id, @NotNull UpdatePatientDTO updatePatientDTO) {
        if (!patientRepository.existsById(id)) {
            new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }

        // Criando um novo paciente com os argumentos que foram passamos para  afunção
        Patient patient = patientRepository.findById(id).get();
        patient.setName(updatePatientDTO.getName());
        patient.setCpf(updatePatientDTO.getCpf());
        patient.setLandline(updatePatientDTO.getLandline());
        patient.setPhone(updatePatientDTO.getPhone());
        patient.setBirthday(updatePatientDTO.getBirthday());
        patient.setEmail(updatePatientDTO.getEmail());
        patient.setAbout(updatePatientDTO.getAbout());

        if (updatePatientDTO.getAddress() != null) {
            Address address = new Address();
            address.setStreet(updatePatientDTO.getAddress().getStreet());
            address.setDistrict(updatePatientDTO.getAddress().getDistrict());
            address.setNumber(updatePatientDTO.getAddress().getNumber());
            address.setComplement(updatePatientDTO.getAddress().getComplement());
            address.setZipcode(updatePatientDTO.getAddress().getZipcode());
            address.setCity(updatePatientDTO.getAddress().getCity());
            address.setUf(updatePatientDTO.getAddress().getUf());
            addressRepository.save(address);
            patient.setAddress(address);
        }
        // Atualizando o paciente no banco de dados com a função do repository
        // Note que no JPA, a função save também atualiza, mas precisamos passar o id para essa função para caso ele encontre no banco ele atualize
        return patientRepository.save(patient);
    }
}
