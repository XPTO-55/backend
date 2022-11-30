package br.com.cpa.spring.modules.appointment;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cpa.spring.modules.appointment.dtos.CreateAppointmentDTO;
import br.com.cpa.spring.modules.appointment.dtos.ListAppointmentResponseDTO;
import br.com.cpa.spring.modules.appointment.services.CreateAppointmentService;
import br.com.cpa.spring.modules.appointment.services.FindAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/appointments")
@Tag(name = "Appoinment", description = "Appointments Routes")
@SecurityRequirement(name = "jwtauth")
public class AppointmentController {
  @Autowired
  CreateAppointmentService createAppointmentService;
  @Autowired
  FindAppointmentService findAppointmentService;

  @Operation(summary = "List appointments")
  @GetMapping("/{userId}")
  public ResponseEntity<List<ListAppointmentResponseDTO>> get(@PathVariable Long userId) {
    List<ListAppointmentResponseDTO> appointments = findAppointmentService.execute(userId);
    return appointments.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(appointments);
  }

  @Operation(summary = "Create new appointment")
  @PostMapping
  public ResponseEntity<Void> save(@RequestBody @Valid CreateAppointmentDTO appointmentData) {
    System.out.println("patient: " + appointmentData.getPatientId());
    System.out.println("professional: " + appointmentData.getProfissionalId());
    createAppointmentService.execute(appointmentData);
    return ResponseEntity.status(201).build();
  }

  @Operation(summary = "Delete appointment")
  @DeleteMapping("/{appointmentId}")
  public ResponseEntity<Void> delete(@PathVariable Long appointmentId) {
    return ResponseEntity.status(201).build();
  }
}
