package com.programing.crew.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtConfigurationFilter extends OncePerRequestFilter {

    private final TokenValidator validator;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String jwt = authHeader.substring(7);
            log.info("Bearer token: {}", jwt);

            Claims claims = Jwts.parser().verifyWith((SecretKey) validator.getSignInKey())
                    .build().parseSignedClaims(jwt).getPayload();

            String userEmail = claims.getSubject();
            log.info("Claims token: {}", claims);

            if (userEmail != null) {
                List<String> authorities = (List<String>) claims.get("roles");

                if (authorities != null && !authorities.isEmpty()) {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(userEmail, null,
                                    authorities.stream()
                                            .map(SimpleGrantedAuthority::new)
                                            .collect(Collectors.toList()));
                    log.info("Authorization token: {}", auth);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            }
        } catch(JwtException e) {
            log.error("JWT validation error: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
