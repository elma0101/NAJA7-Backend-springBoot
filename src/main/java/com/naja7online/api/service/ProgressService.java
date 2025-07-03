// CORRECTED ProgressService.java
package com.naja7online.api.service;

import com.naja7online.api.model.*;
import com.naja7online.api.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ProgressService {

    private final UserLessonProgressRepository progressRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository; // <-- ADD THIS DEPENDENCY

    // Update the constructor to include UserRepository
    public ProgressService(UserLessonProgressRepository progressRepository, LessonRepository lessonRepository, UserRepository userRepository) {
        this.progressRepository = progressRepository;
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
    }

    // Update the method signature to accept the user's email (String) instead of the User object
    public void markLessonAsComplete(String userEmail, Long lessonId) {
        // 1. Find the user by their email
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + userEmail));

        // 2. Find the lesson by its ID
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + lessonId));

        // 3. Create and save the progress record
        UserLessonProgress progress = new UserLessonProgress();
        progress.setUser(user);
        progress.setLesson(lesson);
        progress.setCompletedAt(LocalDateTime.now());

        progressRepository.save(progress);
    }
}