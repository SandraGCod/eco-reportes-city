package com.java.eco_reportes_city.security;

import com.java.eco_reportes_city.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey key;
    private final long expirationMs;

    public JwtService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.expiration-ms}") long expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    public String generarToken(User user) {
        Date ahora = new Date();
        return Jwts.builder()
                .subject(user.getCorreo())
                .claim("id", user.getId())
                .claim("rol", user.getRol())
                .issuedAt(ahora)
                .expiration(new Date(ahora.getTime() + expirationMs))
                .signWith(key)
                .compact();
    }

    public UsuarioAutenticado validar(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        Long id = ((Number) claims.get("id")).longValue();
        String rol = claims.get("rol", String.class);
        return new UsuarioAutenticado(id, claims.getSubject(), rol);
    }
}