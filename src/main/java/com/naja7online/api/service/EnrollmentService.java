// src/main/java/com/naja7online/api/service/EnrollmentService.java
package com.naja7online.api.service;

import com.naja7online.api.model.Enrollment;
import com.naja7online.api.model.User;
import com.naja7online.api.repository.CourseRepository;
import com.naja7online.api.repository.EnrollmentRepository;
import com.naja7online.api.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public void enrollUser(String userEmail, Long courseId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Check if already enrolled to prevent duplicate entries
        if (enrollmentRepository.existsByUser_IdAndCourse_Id(user.getId(), courseId)) {
            throw new IllegalStateException("User is already enrolled in this course.");
        }

        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        Enrollment enrollment = new Enrollment(user, course);
        enrollmentRepository.save(enrollment);
    }
}