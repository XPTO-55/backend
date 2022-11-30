package br.com.cpa.spring.modules.appointment.dtos;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateAppointmentDTO {
  @NotNull
  Long patientId;
  @NotNull
  Long profissionalId;
}
