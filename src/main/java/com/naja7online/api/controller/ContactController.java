package com.naja7online.api.controller;

import com.naja7online.api.dto.ContactFormDto;
import com.naja7online.api.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

    private final EmailService emailService;

    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<Void> submitContactForm(@RequestBody ContactFormDto contactFormDto) {
        emailService.sendContactFormEmail(
                contactFormDto.email(),
                contactFormDto.name(),
                contactFormDto.subject(),
                contactFormDto.message()
        );
        return ResponseEntity.ok().build();
    }
}