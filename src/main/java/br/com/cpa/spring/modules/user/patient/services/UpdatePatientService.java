package br.com.cpa.spring.modules.user.patient.services;

import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import br.com.cpa.spring.modules.user.patient.dtos.UpdatePatientDTO;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

// Diz que essa classe uma service do sistema (Camada que contém a regra de negócio do sistema)
@Service
public class UpdatePatientService {
    // Injeta(importa) o repository (Camada do sistema que manipula o banco de dados)
    @Autowired
    private PatientRepository patientRepository;

    public Patient execute(Long id, @NotNull UpdatePatientDTO updatePatientDTO) {
        // Criando um novo paciente com os argumentos que foram passamos para  afunção
        Patient patient = new Patient();
        patient.setId(id);
        patient.setName(updatePatientDTO.getName());
        patient.setEmail(updatePatientDTO.getEmail());
        patient.setPassword(updatePatientDTO.getPassword());
        // Atualizando o paciente no banco de dados com a função do repository
        // Note que no JPA, a função save também atualiza, mas precisamos passar o id para essa função para caso ele encontre no banco ele atualize
        return patientRepository.save(patient);
    }
}
