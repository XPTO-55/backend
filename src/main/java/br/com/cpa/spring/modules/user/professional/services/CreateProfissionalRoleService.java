package br.com.cpa.spring.modules.user.professional.services;

import br.com.cpa.spring.models.Profissional;
import br.com.cpa.spring.models.Role;
import br.com.cpa.spring.modules.user.professional.ProfissionalRepository;
import br.com.cpa.spring.modules.user.professional.dtos.CreateProfissionalRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreateProfissionalRoleService {
    @Autowired
    ProfissionalRepository profissionalRepository;

    public Profissional execute(CreateProfissionalRoleDTO createUserRoleDTO) {
        Optional<Profissional> userExists = profissionalRepository.findById(createUserRoleDTO.getIdUser());
        List<Role> roles = new ArrayList();

        if (userExists.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Profissional não encontrado");
        }

        roles = createUserRoleDTO.getIdsRoles()
                .stream().map(Role::new)
                .collect(Collectors.toList());

        Profissional profissional = userExists.get();
//        profissional.setRoles(roles);
        profissionalRepository.save(profissional);

        return profissional;

    }
}
