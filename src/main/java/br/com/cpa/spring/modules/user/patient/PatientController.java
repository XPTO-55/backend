package br.com.cpa.spring.modules.user.patient;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.modules.user.patient.services.*;
import br.com.cpa.spring.modules.user.patient.dtos.*;

@RestController
@Tag(name = "Patient", description = "Patients Routes")
@SecurityRequirement(name = "jwtauth")
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    public CreatePatientService createPatientService;
    @Autowired
    public CreateUserRoleService createUserRoleService;

    @Autowired
    public FindPatientService findPatientService;

    @Autowired
    public FindOnePatientService findOnePatientService;

    @Autowired
    public UpdatePatientService updatePatientService;

    @Autowired
    public DeletePatientService deletePatientService;

    @GetMapping
    @Operation(summary = "Get all patients")
    public ResponseEntity index() {
        try {
            List<Patient> patients = findPatientService.execute();
            int status = patients.isEmpty() ? 204 : 200;
            return ResponseEntity.status(status).body(patients);
        } catch (Exception err) {
            return ResponseEntity.status(400).body(err.getMessage());
        }
    }
//    @PreAuthorize("hasRole('ADMIN')")

    @PostMapping
    @Operation(summary = "Create new patient")
    public ResponseEntity store(@RequestBody @Valid CreatePatientDTO user) {
        try {
            Patient patient = createPatientService.execute(user);
            return ResponseEntity.status(201).body(patient);
        } catch (Exception err) {
            return ResponseEntity.status(400).body(err.getMessage());
        }
    }

//    @PostMapping("/roles")
//    public ResponseEntity roles(@RequestBody @Valid CreateProfissionalRoleDTO userRoleDTO) {
//        try {
//            Patient patient = createUserRoleService.execute(userRoleDTO);
//            return ResponseEntity.status(201).body(patient);
//        } catch (Exception err) {
//            return ResponseEntity.status(400).body(err.getMessage());
//        }
//    }

    @GetMapping("/{id}")
    @Operation(summary = "Get existing specific patient by ID")
    public ResponseEntity show(@PathVariable Long id) {
        try {
            Patient patient = findOnePatientService.execute(id);
            return ResponseEntity.status(200).body(patient);
        } catch (Exception err) {
            return ResponseEntity.status(400).body(err.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing specific patient by ID")
    public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody @Valid UpdatePatientDTO user) {
        Patient patient = updatePatientService.execute(id, user);
        return ResponseEntity.status(201).body(patient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete existing specific patient by ID")
    public ResponseEntity destroy(@PathVariable Long id) {
        deletePatientService.execute(id);
        return ResponseEntity.status(204).build();
    }

}
