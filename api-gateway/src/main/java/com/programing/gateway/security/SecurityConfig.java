package com.programing.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange->exchange
                        .pathMatchers("/eureka/**").permitAll()
                        .pathMatchers("/api/admin/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/genre/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/crew/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/episode/**").permitAll()
                        .pathMatchers("/api/series/**").hasAnyRole("MANAGER")
                        .anyExchange().authenticated()
                )
                .build();
    }


}
