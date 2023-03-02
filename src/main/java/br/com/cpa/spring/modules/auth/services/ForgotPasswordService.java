package br.com.cpa.spring.modules.auth.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.cpa.spring.models.Mail;
import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.models.Profissional;
import br.com.cpa.spring.models.User;
import br.com.cpa.spring.modules.auth.dtos.ForgotPasswordDTO;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import br.com.cpa.spring.modules.user.professional.ProfissionalRepository;
import br.com.cpa.spring.providers.EmailProvider;

@Service
public class ForgotPasswordService {
  @Autowired
  PatientRepository patientRepository;
  @Autowired
  ProfissionalRepository profissionalRepository;
  @Autowired
  private EmailProvider emailProvider;

  public void execute(ForgotPasswordDTO data) {
    User user = patientRepository.findByEmail(data.getEmail());

    if (user == null) {
      user = profissionalRepository.findByEmail(data.getEmail());
      if (user == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
      }
    }

    user.setToken(UUID.randomUUID().toString());
    user.setExpiryTokenDate(30);

    if (user instanceof Patient) {
      patientRepository.save((Patient) (user));
    }

    if (user instanceof Profissional) {
      profissionalRepository.save((Profissional) (user));
    }
    Mail mail = new Mail();
    mail.setFrom("no-reply@memorynotfound.com");
    mail.setTo("reset-passwords@inbound.devmail.email");
    mail.setSubject("Password reset request");

    Map<String, Object> model = new HashMap<>();
    // model.put("token", user.getToken());
    model.put("user", user);
    // model.put("signature", "https://memorynotfound.com");
    // model.put("resetUrl", "/reset-password?token=" + user.getToken());
    mail.setModel(model);
    // emailProvider.sendEmail(mail);
    return;
  }
}
