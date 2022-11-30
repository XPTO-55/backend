package br.com.cpa.spring.modules.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cpa.spring.modules.auth.dtos.ForgotPasswordDTO;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import br.com.cpa.spring.modules.user.professional.ProfissionalRepository;

@Service
public class ForgotPasswordService {
  @Autowired
  PatientRepository patientRepository;
  @Autowired
  ProfissionalRepository profissionalRepository;

  public void execute(ForgotPasswordDTO data) {
    return;
  }
}
