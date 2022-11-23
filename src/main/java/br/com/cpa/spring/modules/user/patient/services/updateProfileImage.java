package br.com.cpa.spring.modules.user.patient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.cpa.spring.modules.user.patient.PatientRepository;

public class updateProfileImage {
  @Autowired
  private PatientRepository patientRepository;

  public void execute(Long id, byte[] foto) {
    if (!patientRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    patientRepository.deleteById(id);
  }
}
