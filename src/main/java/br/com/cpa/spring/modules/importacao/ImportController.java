package br.com.cpa.spring.modules.importacao;

import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/import")



public class ImportController {

    @Autowired
    private PatientRepository patientRepository;


    @GetMapping(value = "/relatorioTxt/{}", produces = "text/plain")
    public ResponseEntity<byte[]> getRelatorio() {

        List<Patient> relatorio = patientRepository.findAll();
        return null;
    }
}



    