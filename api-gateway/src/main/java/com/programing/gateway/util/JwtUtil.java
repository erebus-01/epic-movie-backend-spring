package com.programing.gateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;

@Component
public class JwtUtil {

    public static final String SECRET_KEY = "464CC3302A0EF7CDBB1509B5ABA5C29488CD1B8A8B4BEFE2070B81CBA9C8F089";

    public Key getSignInKey() {
        byte[] ketBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(ketBytes);
    }

    public void validateToken(final String token) {
        Jwts.parser().verifyWith((SecretKey) getSignInKey())
                .build().parseSignedClaims(token);
    }

}
