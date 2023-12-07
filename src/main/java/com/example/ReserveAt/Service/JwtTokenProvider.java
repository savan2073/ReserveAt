package com.example.ReserveAt.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.attribute.UserPrincipal;
import java.util.Date;


@Service
public class JwtTokenProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    //metoda do generowania tokena JWT
    public String generateToken(Authentication authentication) {
        //uzyskanie nazwy użytkownika z obiektu authentication
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 864000000);

        return Jwts.builder()
                .setSubject(userPrincipal.getName())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, "")
                .compact();
    }

    //metoda weryfikacji tokena JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey("");
            return true;
        }
    }

    //metoda wydobywania informacji uzytkownika z tokena
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("")
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
