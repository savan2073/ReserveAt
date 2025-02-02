package com.example.ReserveAt.Service;

import com.example.ReserveAt.Model.User;
import com.example.ReserveAt.Repository.UserRepository;
import io.jsonwebtoken.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.attribute.UserPrincipal;
import java.security.SignatureException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;


@Service
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Autowired
    private UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    //metoda do generowania tokena JWT
    public String generateToken(Authentication authentication, String accountType) {
        //uzyskanie nazwy użytkownika z obiektu authentication
        String username = authentication.getName();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + username));
        Long userId = user.getUserId();
        log.info("username pobrane z authentication do generate token: " + username);

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 12000000);

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("accountType", accountType)
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

    public Authentication getAuthentication(String token) {
        //wydobywanie informacji uzytkownika z tokena
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        String accountType = claims.get("accountType", String.class); // Dodanie odczytu accountType

        // Przypisywanie ról na podstawie accountType
        Collection<? extends GrantedAuthority> authorities;
        if ("business".equals(accountType)) {
            authorities = AuthorityUtils.createAuthorityList("ROLE_BUSINESS");
        } else {
            authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        }

        return new UsernamePasswordAuthenticationToken(username, "", authorities);

    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.get("userId", Long.class);
    }

}
