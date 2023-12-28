package com.programing.movie.user.controller;

import com.programing.movie.user.dto.AuthenticationRequest;
import com.programing.movie.user.dto.AuthenticationResponse;
import com.programing.movie.user.dto.PasswordRequestDto;
import com.programing.movie.user.dto.UserRequest;
import com.programing.movie.user.impl.UserServiceImpl;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserRequest request) throws MessagingException, TemplateException, IOException {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<Boolean> verifyToken(@RequestParam("token") String token) {
        Boolean isValid = service.verifyToken(token);
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authentication(request));
    }


    @PostMapping("/send-email-codes")
    public ResponseEntity<String> sendEmailCodes(@RequestParam("userId") UUID userId) throws MessagingException, TemplateException, IOException {
        return ResponseEntity.ok(service.sendEmailCodesNumber(userId));
    }

    @GetMapping("/validator-code")
    public ResponseEntity<Boolean> verifyCodesNumber(@RequestParam("userId") UUID userId, @RequestParam("codes")  String numberCode) {
        Boolean isValid = service.verifyVerificationCode(userId, numberCode);
        return ResponseEntity.ok(isValid);
    }

    @PutMapping("/change-password/{adminId}/{verifiedId}")
    public ResponseEntity<String> updatePassword(@PathVariable("adminId") UUID adminId, @PathVariable("verifiedId") UUID verifiedId, @RequestBody PasswordRequestDto newPassword) {
        return ResponseEntity.ok(service.updatePassword(adminId, verifiedId, newPassword));
    }

    @PutMapping("/{adminId}/change-email")
    public ResponseEntity<String> updateEmail(@PathVariable UUID adminId, @RequestBody String newEmail) {
        return ResponseEntity.ok(service.changeEmail(adminId, newEmail));
    }

}
