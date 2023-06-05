package br.com.cpa.spring.modules.auth.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginResponseDTO implements Serializable {

    public LoginResponseDTO(String jwtToken, String type, String userType, String refreshToken, String email,
            String username, String profileUrl, Long id, String phone) {
        this.jwtToken = jwtToken;
        this.type = type;
        this.userType = userType;
        this.refreshToken = refreshToken;
        this.email = email;
        this.username = username;
        this.profileUrl = profileUrl;
        this.phone = phone;
        this.id = id;
    }

    private final String jwtToken;
    private String type = "Bearer";
    private String userType;
    private String refreshToken;
    private String username;
    private String email;
    private String profileUrl;
    private String phone;
    private Long id;


}
