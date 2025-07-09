// src/main/java/com/naja7online/api/controller/EnrollmentController.java
package com.naja7online.api.controller;

import com.naja7online.api.dto.EnrollmentRequestDto;
import com.naja7online.api.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<?> enrollInCourse(@RequestBody EnrollmentRequestDto request, Authentication authentication) {
        try {
            // The user's email is the 'name' in the Authentication principal
            String userEmail = authentication.getName();
            enrollmentService.enrollUser(userEmail, request.getCourseId());
            return ResponseEntity.ok(Map.of("message", "Successfully enrolled!"));
        } catch (IllegalStateException e) {
            // Handle cases where user is already enrolled
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}