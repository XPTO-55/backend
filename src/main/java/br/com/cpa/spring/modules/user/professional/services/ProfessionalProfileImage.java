package br.com.cpa.spring.modules.user.professional.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.util.IOUtils;

import br.com.cpa.spring.helpers.AwsHelper;
import br.com.cpa.spring.models.Profissional;
import br.com.cpa.spring.modules.user.professional.ProfissionalRepository;

@Service
public class ProfessionalProfileImage {
  @Autowired
  private ProfissionalRepository profissionalRepository;

  @Autowired
  AwsHelper awsHelper;

  public void save(Long id, byte[] foto) {
    if (!profissionalRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    Profissional p = profissionalRepository.findById(id).get();
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
    String filename = p.getEmail() + "-" + currentDateTime.format(formatter);
    awsHelper.save(foto, filename);
    p.setProfileUrl(filename);
    profissionalRepository.save(p);
  }

  public byte[] get(Long id) throws IOException {
    if (!profissionalRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    Profissional p = profissionalRepository.findById(id).get();
    return IOUtils.toByteArray(awsHelper.findByName(p.getProfileUrl()));
  }
}
