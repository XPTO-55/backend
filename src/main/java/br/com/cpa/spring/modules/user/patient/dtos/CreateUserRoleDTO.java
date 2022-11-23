package br.com.cpa.spring.modules.user.patient.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CreateUserRoleDTO {
    private Long idUser;
    private List<Long> idsRoles;
}
