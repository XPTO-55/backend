package br.com.cpa.spring.modules.user.patient.services;

import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.models.Role;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import br.com.cpa.spring.modules.user.patient.dtos.CreateUserRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreateUserRoleService {
    @Autowired
    PatientRepository patientRepository;

    public Patient execute(CreateUserRoleDTO createUserRoleDTO) {
        Optional<Patient> userExists = patientRepository.findById(createUserRoleDTO.getIdUser());
        List<Role> roles = new ArrayList();

        if (userExists.isEmpty()) {
            throw new Error("User does not exists");
        }

        roles = createUserRoleDTO.getIdsRoles()
                .stream().map(Role::new)
                .collect(Collectors.toList());

        Patient patient = userExists.get();
//        patient.setRoles(roles);
        patientRepository.save(patient);

        return patient;

    }
}
