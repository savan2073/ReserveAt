package com.example.ReserveAt.Service;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.attribute.UserPrincipal;
import java.security.SignatureException;
import java.util.Collections;
import java.util.Date;


@Service
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    //metoda do generowania tokena JWT
    public String generateToken(Authentication authentication) {
        //uzyskanie nazwy użytkownika z obiektu authentication
        String username = authentication.getName();

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 864000000);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    //metoda weryfikacji tokena JWT
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {

        } catch (ExpiredJwtException ex) {

        } catch (UnsupportedJwtException ex) {

        } catch (IllegalArgumentException ex) {

        }
        return false;
    }

    //metoda wydobywania informacji uzytkownika z tokena
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Authentication getAuthentication(String token) {
        String username = getUsernameFromToken(token);

        // Tutaj możesz dodać logikę do pobrania szczegółowych informacji o użytkowniku
        // i jego rolach/ uprawnieniach, jeśli jest to potrzebne.

        // Na potrzeby przykładu, tworzymy prostą autoryzację bez rzeczywistych ról.

        return new UsernamePasswordAuthenticationToken(username, "", Collections.emptyList());

    }
}
