package br.com.cpa.spring.modules.user.patient.services;

import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

// Diz que essa classe uma service do sistema (Camada que contém a regra de negócio do sistema)
@Service
public class FindOnePatientService {
    // Injeta(importa) o repository (Camada do sistema que manipula o banco de dados)
    @Autowired
    public PatientRepository patientRepository;

    public Patient execute(Long id) {

        // utilizando as funçoes do repositoru para buscar todos os usuários
        Optional<Patient> patientExists = patientRepository.findById(id);

        if (patientExists.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        return patientExists.get();

    }
}
