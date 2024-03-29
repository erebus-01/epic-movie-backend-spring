package com.programing.movie.admin.service;

import com.programing.movie.admin.config.JwtService;
import com.programing.movie.admin.dto.AuthenticationRequest;
import com.programing.movie.admin.dto.AuthenticationResponse;
import com.programing.movie.admin.dto.RegisterRequest;
import com.programing.movie.admin.dto.RoleRequest;
import com.programing.movie.admin.impl.AdminImpl;
import com.programing.movie.admin.model.Admin;
import com.programing.movie.admin.model.Confirmation;
import com.programing.movie.admin.model.Role;
import com.programing.movie.admin.repository.AdminRepository;
import com.programing.movie.admin.repository.ConfirmationRepository;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService implements AdminImpl {

    private final AdminRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationRepository confirmationRepository;
    private final EmailService emailService;

    @Override
    public AuthenticationResponse register(RegisterRequest request) throws MessagingException, TemplateException, IOException {
        Set<Role> roles = Set.of(Role.ROLE_EMPLOYEE);
        Admin admin = Admin.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .dateOfBirth(request.getDateOfBirth())
                .age(request.getAge())
                .roles(roles)
                .verification(false)
                .build();

        repository.save(admin);

        Confirmation confirmation = new Confirmation(admin);
        confirmationRepository.save(confirmation);

        emailService.sendHtmlEmailService(admin.getFirstname(), admin.getEmail(), confirmation.getToken());

        var jwtToken = jwtService.generatorToken(admin);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authentication(AuthenticationRequest request) {
        log.info("Ready to login with email: {} and password: {}", request.getEmail(), request.getPassword());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));


            log.info("User {} logged in to the system.", user);

            var jwtToken = jwtService.generatorToken(user);

            return AuthenticationResponse.builder().token(jwtToken).build();
        } catch (AuthenticationException e) {
            log.error("Authentication failed: {}", e.getMessage());
            throw new RuntimeException("Authentication failed");
        }
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);

        if(confirmation == null) return Boolean.FALSE;

        LocalDateTime expirationTime = confirmation.getCreateDate().plusMinutes(5);
        LocalDateTime currentTime = LocalDateTime.now();

        if(currentTime.isAfter(expirationTime)) {
            confirmationRepository.delete(confirmation);
            return Boolean.FALSE;
        }

        Admin admin = repository.findByEmailIgnoreCase(confirmation.getAdmin().getEmail());
        admin.setVerification(true);
        repository.save(admin);
        confirmationRepository.delete(confirmation);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public String updateRole(UUID adminId, RoleRequest roles) {
        Admin admin = repository.findById(adminId)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found with ID: " + adminId));

        admin.setRoles(roles.getRoles());
        repository.save(admin);
        return "Updated admin "
                + adminId + " with roles "
                + roles + " successfully";
    }
}
