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

    @GetMapping
    @Operation(summary = "Get all professionals")
    public ResponseEntity index() {
        try {
            List<Profissional> professionals = findProfissionalService.execute();
            int status = professionals.isEmpty() ? 204 : 200;
            return ResponseEntity.status(status).body(professionals);
        } catch (Exception err) {
            return ResponseEntity.status(400).body(err.getMessage());
        }
    }
//    @PreAuthorize("hasRole('ADMIN')")

    @PostMapping
    @Operation(summary = "Create new professional")
    public ResponseEntity store(@RequestBody @Valid CreateProfissionalDTO user) {
        try {
            Profissional professional = createProfissionalService.execute(user);
            return ResponseEntity.status(201).body(professional);
        } catch (Exception err) {
            return ResponseEntity.status(400).body(err.getMessage());
        }
    }

//    @PostMapping("/roles")
//    public ResponseEntity roles(@RequestBody @Valid CreateProfissionalRoleDTO userRoleDTO) {
//        try {
//            Profissional professional = createUserRoleService.execute(userRoleDTO);
//            return ResponseEntity.status(201).body(professional);
//        } catch (Exception err) {
//            return ResponseEntity.status(400).body(err.getMessage());
//        }
//    }

    @GetMapping("/{id}")
    @Operation(summary = "Get existing specific professional by ID")
    public ResponseEntity show(@PathVariable Long id) {
        try {
            Profissional professional = findOneProfissionalService.execute(id);
            return ResponseEntity.status(200).body(professional);
        } catch (Exception err) {
            return ResponseEntity.status(400).body(err.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing specific professional by ID")
    public ResponseEntity<Profissional> update(@PathVariable Long id, @RequestBody @Valid UpdateProfissionalDTO user) {
        Profissional professional = updateProfissionalService.execute(id, user);
        return ResponseEntity.status(201).body(professional);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete existing specific professional by ID")
    public ResponseEntity destroy(@PathVariable Long id) {
        deleteProfissionalService.execute(id);
        return ResponseEntity.status(204).build();
    }

}
