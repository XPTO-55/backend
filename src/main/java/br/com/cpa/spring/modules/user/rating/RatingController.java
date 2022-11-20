package br.com.cpa.spring.modules.user.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cpa.spring.models.Rating;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import br.com.cpa.spring.modules.user.professional.ProfissionalRepository;
import br.com.cpa.spring.modules.user.rating.dto.CreateRatingDTO;
import br.com.cpa.spring.repositories.RatingRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Ratings", description = "Ratings Routes")
@SecurityRequirement(name = "jwtauth")
@RequestMapping("/")
public class RatingController {
  @Autowired
  RatingRepository repository;
  @Autowired
  ProfissionalRepository profissionalRepository;
  @Autowired
  PatientRepository patientRepository;

  @PostMapping("/professional/{id}/rating")
  @Operation(summary = "create rating to professional")
  public ResponseEntity<Void> createProfessionalRating(@PathVariable Long id, CreateRatingDTO ratingData) {
    if (!profissionalRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    Rating rating = new Rating();
    rating.setRating(ratingData.getRating());
    rating.setComment(ratingData.getComment());
    rating.setProfissional(profissionalRepository.findById(id).get());
    repository.save(rating);
    return ResponseEntity.status(201).build();
  }

  // @PostMapping("/patient/{id}/rating")
  // @Operation(summary = "create rating to patient")
  // public ResponseEntity<Void> createPatientRating(@PathVariable Long id,
  // CreateRatingDTO ratingData) {
  // if (!patientRepository.existsById(id)) {
  // return ResponseEntity.notFound().build();
  // }
  // Rating rating = new Rating();
  // rating.setRating(ratingData.getRating());
  // rating.setComment(ratingData.getComment());
  // rating.setProfissional(patientRepository.findById(id).get());
  // repository.save(rating);
  // return ResponseEntity.status(201).build();
  // }
}
