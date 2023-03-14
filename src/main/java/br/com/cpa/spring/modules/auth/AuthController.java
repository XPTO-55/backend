package br.com.cpa.spring.modules.auth;

import br.com.cpa.spring.models.RefreshToken;
import br.com.cpa.spring.modules.auth.dtos.ForgotPasswordDTO;
import br.com.cpa.spring.modules.auth.dtos.LoginRequestDTO;
import br.com.cpa.spring.modules.auth.dtos.LoginResponseDTO;
import br.com.cpa.spring.modules.auth.dtos.RefreshTokenRequestDTO;
import br.com.cpa.spring.modules.auth.dtos.RefreshTokenResponseDTO;
import br.com.cpa.spring.modules.auth.services.ForgotPasswordService;
import br.com.cpa.spring.modules.auth.services.LoginService;
import br.com.cpa.spring.modules.auth.services.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication routes")
public class AuthController {
    @Autowired
    LoginService loginService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    ForgotPasswordService forgotPasswordService;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @PostMapping("/forgot-password")
    @Operation(summary = "Forgot password")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> login(@RequestBody ForgotPasswordDTO forgotPasswordData) {
        forgotPasswordService.execute(forgotPasswordData);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    @Operation(summary = "Login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) throws Exception {
        return loginService.login(loginRequest);
    }

    @PostMapping("/refreshtoken")
    @Operation(summary = "Refresh jwt token")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> refreshtoken(@RequestBody RefreshTokenRequestDTO request) {

        return refreshTokenRepository.findByToken(request.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = refreshTokenService.generateTokenFromUsername(user.getName());
                    return ResponseEntity.ok(new RefreshTokenResponseDTO(token, request.getRefreshToken(), "Bearer"));
                })
                .orElseThrow(() -> new CredentialsExpiredException(request.getRefreshToken()));
    }
}
