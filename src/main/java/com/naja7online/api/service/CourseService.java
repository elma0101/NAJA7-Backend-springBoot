package com.naja7online.api.service;

import com.naja7online.api.dto.*;
import com.naja7online.api.model.Course;
import com.naja7online.api.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseSummaryDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToSummaryDto)
                .collect(Collectors.toList());
    }

    public CourseDetailDto getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(this::convertToDetailDto)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    // --- MAPPING HELPER METHODS ---

    private CourseSummaryDto convertToSummaryDto(Course course) {
        return new CourseSummaryDto(course.getId(), course.getTitle(), course.getDescription(), course.getImageUrl());
    }

    private CourseDetailDto convertToDetailDto(Course course) {
        return new CourseDetailDto(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getImageUrl(),
                course.getModules().stream().map(this::convertToModuleDto).collect(Collectors.toList())
        );
    }

    private ModuleDto convertToModuleDto(com.naja7online.api.model.Module module) {
        return new ModuleDto(
                module.getId(),
                module.getTitle(),
                module.getLessons().stream().map(this::convertToLessonDto).collect(Collectors.toList())
        );
    }

    private LessonDto convertToLessonDto(com.naja7online.api.model.Lesson lesson) {
        return new LessonDto(lesson.getId(), lesson.getTitle(), lesson.getVideoUrl());
    }
}