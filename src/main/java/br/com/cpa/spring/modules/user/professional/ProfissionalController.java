package br.com.cpa.spring.modules.user.professional;

import br.com.cpa.spring.models.Profissional;
import br.com.cpa.spring.modules.user.professional.dtos.CreateProfissionalDTO;
import br.com.cpa.spring.modules.user.professional.dtos.UpdateProfissionalDTO;
import br.com.cpa.spring.modules.user.professional.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Profissional", description = "Profissional Routes")
@SecurityRequirement(name = "jwtauth")
@RequestMapping("/professionals")
public class ProfissionalController {
    @Autowired
    public CreateProfissionalService createProfissionalService;
    @Autowired
    public CreateProfissionalRoleService createProfessionalRoleService;

    @Autowired
    public FindProfissionalService findProfissionalService;

    @Autowired
    public FindOneProfissionalService findOneProfissionalService;

    @Autowired
    public UpdateProfissionalService updateProfissionalService;

    @Autowired
    public DeleteProfissionalService deleteProfissionalService;

    @Autowired
    public updateProfessionalProfileImage updatePatientProfileImage;

    @GetMapping
    @Operation(summary = "Get all professionals")
    public ResponseEntity<List<Profissional>> index() {
        List<Profissional> professionals = findProfissionalService.execute();
        int status = professionals.isEmpty() ? 204 : 200;
        return ResponseEntity.status(status).body(professionals);
    }
//    @PreAuthorize("hasRole('ADMIN')")

    @PostMapping
    @Operation(summary = "Create new professional")
    public ResponseEntity<Profissional> store(@RequestBody @Valid CreateProfissionalDTO user) {
        Profissional professional = createProfissionalService.execute(user);
        return ResponseEntity.status(201).body(professional);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get existing specific professional by ID")
    public ResponseEntity<Profissional> show(@PathVariable Long id) {
        Profissional professional = findOneProfissionalService.execute(id);
        return ResponseEntity.status(200).body(professional);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing specific professional by ID")
    public ResponseEntity<Profissional> update(@PathVariable Long id, @RequestBody @Valid UpdateProfissionalDTO user) {
        Profissional professional = updateProfissionalService.execute(id, user);
        return ResponseEntity.status(201).body(professional);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete existing specific professional by ID")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        deleteProfissionalService.execute(id);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping(value = "/{id}/profileImage", consumes = "image/*")
    public ResponseEntity<Void> patchProfileImage(
            @PathVariable Long id,
            @RequestBody byte[] novaFoto) {

        updatePatientProfileImage.execute(id, novaFoto);

        return ResponseEntity.status(200).build();
    }

}
