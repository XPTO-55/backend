package br.com.cpa.spring.modules.appointment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.models.Profissional;
import br.com.cpa.spring.models.Appointment;
import br.com.cpa.spring.modules.appointment.AppointmentRepository;
import br.com.cpa.spring.modules.appointment.dtos.CreateAppointmentDTO;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import br.com.cpa.spring.modules.user.professional.ProfissionalRepository;

@Service
public class CreateAppointmentService {
  @Autowired
  AppointmentRepository repository;
  @Autowired
  PatientRepository patientRepository;
  @Autowired
  ProfissionalRepository profissionalRepository;

  public void execute(CreateAppointmentDTO appointmentData) {
    System.out.println("id: " + appointmentData.getPatientId());
    System.out.println("id: " + appointmentData.getProfissionalId());
    if (!this.patientRepository.existsById(appointmentData.getPatientId())) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
    }
    Patient patient = new Patient();
    patient.setId(appointmentData.getPatientId());
    if (!this.profissionalRepository.existsById(appointmentData.getProfissionalId())) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professional not found");
    }
    Profissional professional = new Profissional();
    professional.setId(appointmentData.getProfissionalId());

    Appointment appointment = new Appointment();
    appointment.setProfessional(professional);
    appointment.setPatient(patient);
    this.repository.save(appointment);
  }
}
