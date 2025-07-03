package com.naja7online.api.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendContactFormEmail(String fromEmail, String name, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail); // Optional, but good practice
        mailMessage.setTo("your-support-address@example.com"); // Your actual support email
        mailMessage.setSubject("New Contact Form Submission: " + subject);
        mailMessage.setText(
                "You have received a new message from your website contact form.\n\n" +
                        "From: " + name + "\n" +
                        "Email: " + fromEmail + "\n\n" +
                        "Message:\n" + message
        );
        mailSender.send(mailMessage);
    }
}