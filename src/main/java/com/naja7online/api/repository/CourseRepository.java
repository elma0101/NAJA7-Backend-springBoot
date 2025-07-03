// Example for CourseRepository.java
package com.naja7online.api.repository;

import com.naja7online.api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // You can add custom query methods here later if needed
}