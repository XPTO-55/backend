package br.com.cpa.spring.modules.auth.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginResponseDTO implements Serializable {

    public LoginResponseDTO(String jwtToken, String type, String refreshToken, String email, String username, Long id) {
        this.jwtToken = jwtToken;
        this.type = type;
        this.refreshToken = refreshToken;
        this.email = email;
        this.username = username;
        this.id = id;
    }

    private final String jwtToken;
    private String type = "Bearer";
    private String refreshToken;
    private String username;
    private String email;
    private Long id;


}
