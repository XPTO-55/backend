package br.com.cpa.spring.modules.appointment.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class ListAppointmentResponseDTO {
  private String patient;
  private String professional;
  private String especiality;
  private Date date;

  public ListAppointmentResponseDTO(Date date, String patient, String professional, String especiality) {
    this.patient = patient;
    this.professional = professional;
    this.especiality = especiality;
    this.date = date;
  }
}
