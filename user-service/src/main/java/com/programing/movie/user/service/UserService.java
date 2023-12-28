package com.programing.movie.user.service;

import com.programing.movie.user.dto.*;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.UUID;

public interface UserService {
    AuthenticationResponse save(UserRequest request) throws MessagingException, TemplateException, IOException;
    String changeEmail(UUID userId, String newEmail);
    String updatePassword(UUID userId, UUID verifiedID, PasswordRequestDto newPassword);
    String updateVerification(UUID userId, Boolean newVerification);
    Boolean verifyToken(String token);
    AuthenticationResponse authentication(AuthenticationRequest request);

    String sendEmailCodesNumber(UUID userId) throws MessagingException, TemplateException, IOException;
    boolean verifyVerificationCode(UUID userId, String enteredCode);

}
