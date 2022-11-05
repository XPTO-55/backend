package br.com.cpa.spring.modules.auth;

import br.com.cpa.spring.models.RefreshToken;
import br.com.cpa.spring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteTokenByUser(User user);
}
