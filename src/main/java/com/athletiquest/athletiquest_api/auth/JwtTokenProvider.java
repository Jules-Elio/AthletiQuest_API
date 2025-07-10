package com.athletiquest.athletiquest_api.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final long JWT_EXPIRATION_DATE = 3600000; //1h in milliseconds
    private final String jwtSecret = generateSecretKey();

    public String generateToken(Authentication authentication) {

        String userEmail = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWT_EXPIRATION_DATE);

        return Jwts.builder()
                   .subject(userEmail)
                   .issuedAt(new Date())
                   .expiration(expireDate)
                   .signWith(secretKey())
                   .compact();
    }

    private SecretKey secretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // extract username from JWT token
    public String getUserEmail(String token) {
        return Jwts.parser().verifyWith(secretKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }

    // validate JWT token
    public boolean validateToken(String token) {
        Jwts.parser().verifyWith(secretKey()).build().parse(token);
        return true;
    }

    public String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }
}
