package br.com.cpa.spring.modules.auth.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginRequestDTO implements Serializable {
    private String email;
    private String password;
}
