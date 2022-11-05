package br.com.cpa.spring.modules.auth.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginRequestDTO implements Serializable {
    private String email;
    private String password;
}
