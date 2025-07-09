package com.naja7online.api.controller;

import com.naja7online.api.model.User;
import com.naja7online.api.service.ProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressService progressService;
    private final com.naja7online.api.repository.UserRepository userRepository;

    public ProgressController(ProgressService progressService, com.naja7online.api.repository.UserRepository userRepository) {
        this.progressService = progressService;
        this.userRepository = userRepository;
    }

    @PostMapping("/lessons/{lessonId}/complete")
    public ResponseEntity<Void> completeLesson(@PathVariable Long lessonId, @AuthenticationPrincipal org.springframework.security.core.userdetails.User principal) {
        // Get the full User entity from the principal's email
        User currentUser = userRepository.findByEmail(principal.getUsername()).orElseThrow();
        progressService.markLessonAsComplete(String.valueOf(currentUser), lessonId);
        return ResponseEntity.ok().build();
    }
}