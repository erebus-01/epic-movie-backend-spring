package com.programing.movie.admin.config;

import com.programing.movie.admin.model.Admin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {

    public static final String SECRET_KEY = "464CC3302A0EF7CDBB1509B5ABA5C29488CD1B8A8B4BEFE2070B81CBA9C8F089";

    public String generatorToken(UserDetails userDetails) {
        Map<String, Object> additionalClaims = new HashMap<>();

        additionalClaims.put("userId", ((Admin) userDetails).getId());
        additionalClaims.put("emailVerified", ((Admin) userDetails).isVerification());

        Set<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        additionalClaims.put("roles", roles);

        additionalClaims.put("firstname", ((Admin) userDetails).getFirstname());
        additionalClaims.put("lastname", ((Admin) userDetails).getLastname());
        additionalClaims.put("age", ((Admin) userDetails).getAge());

        return generatorToken(additionalClaims, userDetails);
    }

    public String generatorToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey())
                .compact();
    }

    public Key getSignInKey() {
        byte[] ketBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(ketBytes);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return (Claims) Jwts
                .parser()
                .verifyWith((SecretKey) getSignInKey())
                .build()
                .parseSignedClaims(token);
    }

    public void validateToken(final String token) {
        Jwts.parser().verifyWith((SecretKey) getSignInKey())
                .build().parseSignedClaims(token);
    }

}
