package br.com.cpa.spring.config.security.jwt;

import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.models.User;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import br.com.cpa.spring.modules.user.professional.ProfissionalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    ProfissionalRepository profissioonalRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User emailAlreadyExists = patientRepository.findByEmailFetchRoles(email);

        if (emailAlreadyExists == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return UserPrincipal.create(emailAlreadyExists);
    }
}
