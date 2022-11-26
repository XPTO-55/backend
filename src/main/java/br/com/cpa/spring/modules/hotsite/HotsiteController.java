package br.com.cpa.spring.modules.hotsite;

import br.com.cpa.spring.helpers.HotSite;
import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.modules.hotsite.service.ImportArquivoTxt;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/hotsite")
@Tag(name = "Hotsite", description = "Hotsite Routes")
@SecurityRequirement(name = "jwtauth")
public class HotsiteController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ImportArquivoTxt importArquivoTxtService;

    @Operation(summary = "Export patients to txt file")
    @GetMapping(value = "/relatorio/txt", produces = "text/plain")
    public ResponseEntity<String> getRelatorio() {
        String filename = generateFileName() + ".txt";
        List<Patient> relatorio = patientRepository.findAll();
        HotSite.gravaArquivoTxt(relatorio, filename);
        HttpHeaders headers = new HttpHeaders();
        // String export = HotSite.leArquivoTxt(filename);
        try {
            String export = new String(Files.readAllBytes(Paths.get(filename)));
            headers.add("Content-Disposition", "attachment; filename = pacientes.txt");
            Files.delete(Paths.get(filename));
            return ResponseEntity.status(200).headers(headers).body(export.toString());
        } catch (IOException ex) {
            return ResponseEntity.internalServerError().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Export patients to .csv file")
    @GetMapping(value = "/relatorio/csv", produces = "text/csv")
    public ResponseEntity<byte[]> getRelatorioCsv() {
        String filename = generateFileName();
        List<Patient> relatorio = patientRepository.findAll();
        HotSite.gravaArquivoCsv(relatorio, filename);
        HttpHeaders headers = new HttpHeaders();
        // String export = HotSite.leArquivoTxt(filename);
        try {
            byte[] export = Files.readAllBytes(Paths.get(filename));
            headers.add("Content-Disposition", "attachment; filename = pacientes.csv");
            Files.delete(Paths.get(filename));
            return ResponseEntity.status(200).headers(headers).body(export);
        } catch (IOException ex) {
            return ResponseEntity.internalServerError().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Import patients from .txt file")
    @PostMapping(value = "/import", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Void> patchProfileImage(
            @RequestBody byte[] arquivo) {

        importArquivoTxtService.execute(new String(arquivo));

        return ResponseEntity.status(200).build();
    }

    // @PostMapping(value = "/import", consumes =
    // "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    // public ResponseEntity<Void> importTxt(@RequestBody byte[] arquivo) {
    // importArquivoTxtService.execute(new String(arquivo));
    // return ResponseEntity.status(200).build();
    // }

    private String generateFileName() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
        String filename = "Pacientes-" + currentDateTime.format(formatter) + ".txt";
        return filename;
    }
}
