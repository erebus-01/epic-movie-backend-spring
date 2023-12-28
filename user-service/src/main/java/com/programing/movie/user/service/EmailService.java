package com.programing.movie.user.service;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {
    void sendHtmlEmailService(String name, String to, String token) throws MessagingException, IOException, TemplateException;
    void sendHtmlEmailNumberService(String name, String to, String token) throws MessagingException, IOException, TemplateException;

}
