package br.com.cpa.spring.modules.appointment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.cpa.spring.modules.appointment.AppointmentRepository;

@Service
public class DeleteAppointmentService {
  @Autowired
  AppointmentRepository repository;

  public void execute(Long id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found");
    }
    this.repository.deleteById(id);
  }
}
