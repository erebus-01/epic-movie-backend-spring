package com.programing.movie.admin.controller;

import com.programing.movie.admin.dto.AuthenticationRequest;
import com.programing.movie.admin.dto.AuthenticationResponse;
import com.programing.movie.admin.config.JwtService;
import com.programing.movie.admin.dto.RegisterRequest;
import com.programing.movie.admin.dto.RoleRequest;
import com.programing.movie.admin.service.AuthenticationService;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws MessagingException, TemplateException, IOException {
        return ResponseEntity.ok(service.register(request));
    }

    @GetMapping
    public ResponseEntity<Boolean> verifyToken(@RequestParam("token") String token) {
        Boolean isVerified = service.verifyToken(token);
        return ResponseEntity.ok(isVerified);
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok((service.authentication(request)));
    }

    @GetMapping("/validate")
    public ResponseEntity<String> isValidToken(@RequestParam("token") String token) {
        jwtService.validateToken(token);
        return ResponseEntity.ok("Token is validate.");
    }

    @PutMapping("/{adminId}/update-roles")
    public ResponseEntity<String> updateRoles(@PathVariable UUID adminId, @RequestBody RoleRequest newRoles) {
        return ResponseEntity.ok(service.updateRole(adminId, newRoles));
    }

}
