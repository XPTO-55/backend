package br.com.cpa.spring.modules.auth.services;

import br.com.cpa.spring.models.RefreshToken;
import br.com.cpa.spring.modules.auth.RefreshTokenRepository;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private String SECRET_KEY = "cpa";

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private PatientRepository patientRepository;

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(patientRepository.findById(userId).get());
        refreshToken.setExpirationTime((new Date(System.currentTimeMillis() + 1000 * 60 * 2)));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }


    public String generateTokenFromUsername(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 2)).signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpirationTime().compareTo(Date.from(Instant.now())) < 0) {
            refreshTokenRepository.delete(token);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return token;
    }

    @Transactional // do spring
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteTokenByUser(patientRepository.findById(userId).get());
    }

}
