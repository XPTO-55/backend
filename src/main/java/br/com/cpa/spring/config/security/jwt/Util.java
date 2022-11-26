package br.com.cpa.spring.config.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class Util {

    @Value("${jwt.secret}")
    private String JWT_SECRET_KEY;

    @Value("${jwt.expiration}")
    private Integer JWT_EXPIRATION;

    public String getUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public Date verifyExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        // try {
        return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
            // } catch (ExpiredJwtException ex) {
            // throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token expired");
            // } catch (MalformedJwtException ex) {
            // throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
            // } catch (BadCredentialsException ex) {
            // throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad
            // Credentials");
            // } catch (Exception ex) {
            // throw ex;
            // }
    }

    private Boolean verifyTokenExpired(String token) {
        return verifyExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claimns = new HashMap<>();
        return createToken(claimns, userDetails.getUsername(), userDetails);
    }

    public String createToken(Map<String, Object> claimns, String subject, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(claimns)
                .claim("roles", userDetails.getAuthorities())
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .compact();
    }

    public Boolean verifyToken(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername()) && !verifyTokenExpired(token));
    }
}
