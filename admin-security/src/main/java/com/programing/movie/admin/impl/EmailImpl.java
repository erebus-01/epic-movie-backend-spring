package com.programing.movie.admin.impl;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailImpl {
    void sendEmailService(String name, String to, String token);
    void sendHtmlEmailService(String name, String to, String token) throws MessagingException, IOException, TemplateException;
}
