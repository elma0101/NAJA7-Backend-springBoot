package com.naja7online.api.repository;

import com.naja7online.api.model.UserLessonProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLessonProgressRepository extends JpaRepository<UserLessonProgress, Long> {
    // We can add custom queries here later, e.g., count by user
}