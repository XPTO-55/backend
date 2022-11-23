package br.com.cpa.spring.modules.user.patient.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.util.IOUtils;

import br.com.cpa.spring.helpers.AwsHelper;
import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.modules.user.patient.PatientRepository;

@Service
public class PatientProfileImage {
  @Autowired
  private PatientRepository patientRepository;

  @Autowired
  AwsHelper awsHelper;

  public void save(Long id, byte[] foto) {
    if (!patientRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    Patient p = patientRepository.findById(id).get();
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
    String filename = p.getEmail() + "-" + currentDateTime.format(formatter);
    awsHelper.save(foto, filename);
    p.setProfileUrl(filename);
    patientRepository.save(p);
  }

  public byte[] get(Long id) throws IOException {
    if (!patientRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    Patient p = patientRepository.findById(id).get();
    return IOUtils.toByteArray(awsHelper.findByName(p.getProfileUrl()));
  }
}
