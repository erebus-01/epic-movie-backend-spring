package com.programing.movie.usersecurity.impl;

import com.programing.movie.usersecurity.dto.AuthenticationRequest;
import com.programing.movie.usersecurity.dto.AuthenticationResponse;
import com.programing.movie.usersecurity.dto.RegisterRequest;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface AdminImpl {

    AuthenticationResponse register(RegisterRequest request) throws MessagingException, TemplateException, IOException;
    AuthenticationResponse authentication(AuthenticationRequest request);
    Boolean verifyToken(String token);

}
