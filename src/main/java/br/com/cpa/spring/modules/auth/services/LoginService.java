package br.com.cpa.spring.modules.auth.services;

import br.com.cpa.spring.config.security.jwt.Util;
import br.com.cpa.spring.models.RefreshToken;
import br.com.cpa.spring.modules.auth.dtos.LoginRequestDTO;
import br.com.cpa.spring.modules.auth.dtos.LoginResponseDTO;
import br.com.cpa.spring.config.security.jwt.CustomUserDetailsService;
import br.com.cpa.spring.config.security.SecurityConfig;
import br.com.cpa.spring.config.security.jwt.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private Util util;

    @Autowired
    private CustomUserDetailsService userAuthenticationService;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    RefreshTokenService refreshTokenService;


    public ResponseEntity<?> login(LoginRequestDTO loginRequest) throws Exception {
        try {
            System.out.println("LOGIN REQUEST: " + loginRequest);

            Authentication authentication =
                    securityConfig.authenticationManagerBean().authenticate(
                            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
                    );

            System.out.println("AUTH: " + authentication);

            final UserDetails userDetails = userAuthenticationService
                    .loadUserByUsername(loginRequest.getEmail());

            System.out.println("LOGIN userDetails: " + userDetails);

            UserPrincipal appUser = (UserPrincipal) authentication.getPrincipal();

            final String jwtToken = util.generateToken(userDetails);

            System.out.println("APPUSER: " + appUser);

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(appUser.getUser().getId());


            return ResponseEntity.ok(
                    new LoginResponseDTO(jwtToken, "Bearer", refreshToken.getToken(), appUser.getUsername(),
                            appUser.getUser().getId()));

        } catch (BadCredentialsException e) {

            throw new Error("Invalid credentials");
        }
    }

}
