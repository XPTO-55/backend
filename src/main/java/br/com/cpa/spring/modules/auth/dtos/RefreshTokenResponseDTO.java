package br.com.cpa.spring.modules.auth.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenResponseDTO {

    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";


    public RefreshTokenResponseDTO(String accessToken, String refreshToken, String tokenType) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
    }
}
