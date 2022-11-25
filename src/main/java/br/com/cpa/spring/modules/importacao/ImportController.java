package br.com.cpa.spring.modules.importacao;

import br.com.cpa.spring.helpers.HotSite;
import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/import")



public class ImportController {

    @Autowired
    private PatientRepository patientRepository;


    @GetMapping(value = "/relatorioTxt/{}", produces = "text/plain")
    public ResponseEntity<String> getRelatorio() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
        String filename = "Pacientes - " + currentDateTime.format(formatter) + ".txt";
        List<Patient> relatorio = patientRepository.findAll();
        HotSite.gravaArquivoTxt(relatorio,filename);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename = pacientes.txt");
        return new ResponseEntity<>(HotSite.leArquivoTxt(filename),headers, HttpStatus.OK);
    }
}



    