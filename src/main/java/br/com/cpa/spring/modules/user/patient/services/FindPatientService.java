package br.com.cpa.spring.modules.user.patient.services;

import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import br.com.cpa.spring.modules.user.patient.dtos.CreatePatientDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Diz que essa classe uma service do sistema (Camada que contém a regra de negócio do sistema)
@Service
public class FindPatientService {
    // Injeta(importa) o repository (Camada do sistema que manipula o banco de dados)
    @Autowired
    public PatientRepository patientRepository;

    public List<Patient> execute() {

        // utilizando as funçoes do repositoru para buscar todos os usuários
        List<Patient> patients = patientRepository.findAllAndRoles();

        return patients;
    }
}
