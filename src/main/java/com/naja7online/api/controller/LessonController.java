// src/main/java/com/naja7online/api/controller/LessonController.java
package com.naja7online.api.controller;

import com.naja7online.api.dto.LessonDto;
import com.naja7online.api.model.User;
import com.naja7online.api.repository.UserRepository;
import com.naja7online.api.service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;
    private final UserRepository userRepository;

    public LessonController(LessonService lessonService, UserRepository userRepository) {
        this.lessonService = lessonService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> getLessonById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // Get the User entity from UserDetails
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        LessonDto lessonDto = lessonService.getLessonById(id, user);
        return ResponseEntity.ok(lessonDto);
    }
}