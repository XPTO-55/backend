package br.com.cpa.spring.modules.user.professional.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CreateProfissionalRoleDTO {
    private Long idUser;
    private List<Long> idsRoles;
}
