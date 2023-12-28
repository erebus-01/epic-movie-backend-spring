package com.programing.movie.admin.impl;

import com.programing.movie.admin.dto.AuthenticationRequest;
import com.programing.movie.admin.dto.AuthenticationResponse;
import com.programing.movie.admin.dto.RegisterRequest;
import com.programing.movie.admin.dto.RoleRequest;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.UUID;

public interface AdminImpl {

    AuthenticationResponse register(RegisterRequest request) throws MessagingException, TemplateException, IOException;
    AuthenticationResponse authentication(AuthenticationRequest request);
    Boolean verifyToken(String token);
    String updateRole(UUID adminId, RoleRequest newRoles);
}
