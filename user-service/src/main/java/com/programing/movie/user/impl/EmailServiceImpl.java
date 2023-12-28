package com.programing.movie.user.impl;

import com.programing.movie.user.service.EmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    public static final String NEW_ADMIN_ACCOUNT_VERIFICATION = "NEW_ADMIN_ACCOUNT_VERIFICATION";
    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender mailSender;
    private final Configuration configuration;

    @Override
    @Async
    public void sendHtmlEmailService(String name, String to, String token) throws MessagingException, IOException, TemplateException {
        Map model = new HashMap();
        model.put("name", name);
        model.put("url", getVerificationUrl(host, token));

        MimeMessage message = getMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        Template template = configuration.getTemplate("email.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        helper.setSubject(NEW_ADMIN_ACCOUNT_VERIFICATION);
        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setText(html, true);
        mailSender.send(message);
        log.info("Email sent successfully to: {}", to);
    }

    @Override
    @Async
    public void sendHtmlEmailNumberService(String name, String to, String token) throws MessagingException, IOException, TemplateException {
        Map model = new HashMap();
        model.put("name", name);
        model.put("codes", token);

        MimeMessage message = getMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        Template template = configuration.getTemplate("emailnumber.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        helper.setSubject(NEW_ADMIN_ACCOUNT_VERIFICATION);
        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setText(html, true);
        mailSender.send(message);
        log.info("Email sent successfully to: {}", to);
    }

    private MimeMessage getMimeMessage() {
        return mailSender.createMimeMessage();
    }
    private static String getVerificationUrl(String host, String token) {
        return host + "/auth/user?token=" + token;
    }
}
