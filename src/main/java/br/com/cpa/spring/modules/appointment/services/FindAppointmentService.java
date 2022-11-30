package br.com.cpa.spring.modules.appointment.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.cpa.spring.modules.appointment.AppointmentRepository;
import br.com.cpa.spring.modules.appointment.dtos.ListAppointmentResponseDTO;
import br.com.cpa.spring.modules.user.patient.PatientRepository;

@Service
public class FindAppointmentService {
  @Autowired
  AppointmentRepository repository;
  @Autowired
  PatientRepository patientRepository;

  public List<ListAppointmentResponseDTO> execute(Long userId) {
    if (!patientRepository.existsById(userId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    return repository.findAllByPatientId(userId);
  }
}
