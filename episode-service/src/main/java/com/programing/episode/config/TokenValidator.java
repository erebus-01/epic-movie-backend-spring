package com.programing.episode.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.List;

@Service
public class TokenValidator {

    public static final String SECRET_KEY = "464CC3302A0EF7CDBB1509B5ABA5C29488CD1B8A8B4BEFE2070B81CBA9C8F089";

    public Key getSignInKey() {
        byte[] ketBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(ketBytes);
    }

    public Claims decodeToken(final String token) {
        return (Claims) Jwts
                .parser()
                .verifyWith((SecretKey) getSignInKey())
                .build()
                .parseSignedClaims(token);
    }

    public boolean hasRole(String token, String roleName) {
        Claims claims = decodeToken(token);
        List<String> roles = claims.get("roles", List.class);

        return roles != null && roles.contains(roleName);
    }

    public Jws<Claims> validateToken(final String token) {
        return Jwts.parser().verifyWith((SecretKey) getSignInKey())
                .build().parseSignedClaims(token);
    }

}
