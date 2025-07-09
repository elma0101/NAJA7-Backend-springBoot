// src/main/java/com/naja7online/api/service/LessonService.java
package com.naja7online.api.service;

import com.naja7online.api.dto.LessonDto;
import com.naja7online.api.model.Lesson;
import com.naja7online.api.model.User;
import com.naja7online.api.repository.LessonRepository;
import com.naja7online.api.repository.EnrollmentRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final EnrollmentRepository enrollmentRepository; // For security check

    public LessonService(LessonRepository lessonRepository, EnrollmentRepository enrollmentRepository) {
        this.lessonRepository = lessonRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public LessonDto getLessonById(Long lessonId, User user) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found with id: " + lessonId));

        // --- SECURITY CHECK ---
        // Verify that the user is enrolled in the course this lesson belongs to.
        Long courseId = lesson.getModule().getCourse().getId();
        boolean isEnrolled = enrollmentRepository.existsByUser_IdAndCourse_Id(user.getId(), courseId);

        if (!isEnrolled) {
            throw new AccessDeniedException("User is not enrolled in the course for this lesson.");
        }

        // If authorized, map the entity to a DTO and return it.
        return mapToLessonDto(lesson);
    }

    private LessonDto mapToLessonDto(Lesson lesson) {
        return new LessonDto(
            lesson.getId(),
            lesson.getTitle(),
            lesson.getVideoUrl()
        );
    }
}