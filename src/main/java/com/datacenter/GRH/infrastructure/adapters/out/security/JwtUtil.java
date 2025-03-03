package com.datacenter.GRH.infrastructure.adapters.out.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY;
    private final long expirationTime;

    public JwtUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expirationTime) {
        if (secret == null || secret.trim().isEmpty()) {
            throw new IllegalArgumentException("⚠️ La clave secreta JWT no puede estar vacía.");
        }
        this.SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.expirationTime = expirationTime;
    }

    public String extractDocumentNumber(String token) {
        return extractClaim(token, claims -> claims.get("documentNumber", String.class));
    }

    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {
        return extractClaim(token, claims -> {
            Object rolesObject = claims.get("roles");
            if (rolesObject instanceof List<?>) {
                return (List<String>) rolesObject;
            }
            return List.of();
        });
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new IllegalArgumentException("⚠️ Error en la validación del token JWT: " + e.getMessage());
        }
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails, String documentNumber, List<String> roles) {
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("⚠️ No se pueden generar tokens sin roles.");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("documentNumber", documentNumber);
        claims.put("roles", roles); // ✅ Asegurar que los roles se agreguen correctamente

        // 🔍 Debug: Verificar que los roles se están agregando al token
        System.out.println("🔑 Generando token con roles: " + roles);

        return createToken(claims, userDetails.getUsername(), expirationTime);
    }

    private String createToken(Map<String, Object> claims, String subject, long customExpiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + customExpiration))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String documentNumber = extractDocumentNumber(token);
            List<String> roles = extractRoles(token); // ✅ Verificar los roles extraídos

            System.out.println("🔍 Validando token: " + token);
            System.out.println("📌 DocumentNumber extraído: " + documentNumber);
            System.out.println("📌 Roles extraídos: " + roles);

            return documentNumber != null && documentNumber.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (Exception e) {
            System.err.println("⚠️ Error validando token: " + e.getMessage());
            return false;
        }
    }

    public String generateRecoveryToken(String documentNumber) {
        if (documentNumber == null || documentNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("⚠️ No se puede generar un token de recuperación sin documentNumber.");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("documentNumber", documentNumber);

        return createToken(claims, documentNumber, 15 * 60 * 1000); // 15 minutos de validez
    }
}
