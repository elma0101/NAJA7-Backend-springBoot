package com.naja7online.api.service;

import com.naja7online.api.dto.*;
import com.naja7online.api.model.Course;
import com.naja7online.api.model.User;
import com.naja7online.api.repository.CourseRepository;
import com.naja7online.api.repository.EnrollmentRepository;
import com.naja7online.api.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
    }


    public List<CourseSummaryDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToSummaryDto)
                .collect(Collectors.toList());
    }

    public CourseDetailDto getCourseById(Long id) {
        // The course itself is public information. Anyone can see it.
        var course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));

        // --- THE NEW LOGIC ---
        // 1. Check if a user is currently logged in.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isEnrolled = false;

        // The principal will be "anonymousUser" if not logged in.
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // 2. If they are logged in, check if they are enrolled.
            isEnrolled = enrollmentRepository.existsByUser_IdAndCourse_Id(user.getId(), course.getId());
        }

        // 3. Map the entity to the DTO, passing the enrollment status.
        return mapToCourseDetailDto(course, isEnrolled);
    }

    private CourseDetailDto mapToCourseDetailDto(Course course, boolean isEnrolled) {
        // Map modules and lessons
        List<ModuleDto> moduleDtos = course.getModules().stream().map(module -> {
            // --- THE KEY SECURITY LOGIC ---
            // Only include lesson details if the user is enrolled.
            List<LessonDto> lessonDtos;
            if (isEnrolled) {
                lessonDtos = module.getLessons().stream().map(lesson -> 
                    new LessonDto(lesson.getId(), lesson.getTitle(), lesson.getVideoUrl())
                ).collect(Collectors.toList());
            } else {
                // For non-enrolled users, send titles without video URLs
                lessonDtos = module.getLessons().stream().map(lesson -> 
                    new LessonDto(lesson.getId(), lesson.getTitle(), null)
                ).collect(Collectors.toList());
            }
            
            return new ModuleDto(module.getId(), module.getTitle(), lessonDtos);
        }).collect(Collectors.toList());

        // Create the DTO with all information including enrollment status
        return new CourseDetailDto(
            course.getId(),
            course.getTitle(),
            course.getDescription(),
            course.getImageUrl(),
            moduleDtos,
            isEnrolled
        );
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
                course.getModules().stream().map(this::convertToModuleDto).collect(Collectors.toList()),
                true // Assuming this method is used when user is enrolled
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