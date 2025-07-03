package com.naja7online.api.repository;

import com.naja7online.api.model.Course;
import com.naja7online.api.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson,Long> {
}
