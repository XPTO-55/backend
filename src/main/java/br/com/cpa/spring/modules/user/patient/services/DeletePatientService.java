package br.com.cpa.spring.modules.user.patient.services;

import br.com.cpa.spring.modules.user.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

// Diz que essa classe uma service do sistema (Camada que contém a regra de negócio do sistema)
@Service
public class DeletePatientService {
    // Injeta(importa) o repository (Camada do sistema que manipula o banco de dados)
    @Autowired
    private PatientRepository patientRepository;

    public void execute(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        patientRepository.deleteById(id);
    }
}
