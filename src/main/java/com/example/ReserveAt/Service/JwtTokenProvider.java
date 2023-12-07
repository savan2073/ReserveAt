package com.example.ReserveAt.Service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class JwtTokenProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    //metoda do generowania tokena JWT
    public String generateToken(Authentication authentication) {
        //logika generowania tokena
    }

    //metoda weryfikacji tokena JWT
    public boolean validateToken(String token) {
        //logika weryfikacji tokena
    }

    //metoda wydobywania informacji uzytkownika z tokena
    public String getUsernameFromToken(String token) {
        //logika wydobycia informacji
    }
}
