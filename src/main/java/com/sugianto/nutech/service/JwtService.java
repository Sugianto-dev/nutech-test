package com.sugianto.nutech.service;

import com.sugianto.nutech.config.AppConfig;
import com.sugianto.nutech.exception.UnauthorizedException;
import com.sugianto.nutech.repository.AppUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AppConfig appConfig;

    public String generateToken(UserDetails userDetails) {
        Map<String, String> claims = new HashMap<>();
        claims.put("iss", appConfig.getJwtIss());
        Instant now = Instant.now();
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(3600 * 12)))
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode(appConfig.getJwtSecretKey());
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public String extractUsername(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getSubject();
    }

    public Claims getClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public boolean isExpired(String jwt) {
        try {
            Claims claims = getClaims(jwt);
            return claims.getExpiration().before(Date.from(Instant.now()));
        } catch (Exception e) {
            throw new UnauthorizedException("Token tidak valid atau kadaluwarsa");
        }
    }

}
