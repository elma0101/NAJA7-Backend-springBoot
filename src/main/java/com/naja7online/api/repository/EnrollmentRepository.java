// src/main/java/com/naja7online/api/repository/EnrollmentRepository.java
package com.naja7online.api.repository;

import com.naja7online.api.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // This method is crucial for checking if a user is already enrolled
    boolean existsByUser_IdAndCourse_Id(Long userId, Long courseId);

    List<Enrollment> findByUser_Id(Long userId);
}