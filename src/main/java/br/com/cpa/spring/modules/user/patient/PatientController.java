package br.com.cpa.spring.modules.user.patient;

import java.util.List;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.modules.user.patient.services.*;
import br.com.cpa.spring.modules.user.patient.dtos.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Tag(name = "Patient", description = "Patients Routes")
@SecurityRequirement(name = "jwtauth")
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    public CreatePatientService createPatientService;

    @Autowired
    public FindPatientService findPatientService;

    @Autowired
    public FindOnePatientService findOnePatientService;

    @Autowired
    public UpdatePatientService updatePatientService;

    @Autowired
    public DeletePatientService deletePatientService;

    @Autowired
    public updateProfileImage updateProfileImage;

    @GetMapping
    @Operation(summary = "Get all patients")
    public ResponseEntity<List<Patient>> index() {
        List<Patient> patients = findPatientService.execute();
        int status = patients.isEmpty() ? 204 : 200;
        return ResponseEntity.status(status).body(patients);
    }

//    @PreAuthorize("hasRole('ADMIN')")

    @PostMapping(value = "/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Create new patient")
    public ResponseEntity<Patient> store(@RequestBody @Valid CreatePatientDTO user) {
        Patient patient = createPatientService.execute(user);
        return ResponseEntity.status(201).body(patient);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get existing specific patient by ID")
    public ResponseEntity<Patient> show(@PathVariable Long id) {
        Patient patient = findOnePatientService.execute(id);
        return ResponseEntity.status(200).body(patient);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing specific patient by ID")
    public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody @Valid UpdatePatientDTO user) {
        Patient patient = updatePatientService.execute(id, user);
        return ResponseEntity.status(201).body(patient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete existing specific patient by ID")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        deletePatientService.execute(id);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping(value = "/{id}/profileImage", consumes = "image/*")
    public ResponseEntity<Void> patchProfileImage(
            @PathVariable Long id,
            @RequestBody byte[] novaFoto) {

        updateProfileImage.execute(id, novaFoto);

        return ResponseEntity.status(200).build();
    }

}
