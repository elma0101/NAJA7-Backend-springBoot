package com.naja7online.api.controller;

import com.naja7online.api.dto.CourseDetailDto;
import com.naja7online.api.dto.CourseSummaryDto;
import com.naja7online.api.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseSummaryDto>> getAllCourses() {
        List<CourseSummaryDto> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDetailDto> getCourseById(@PathVariable Long id) {
        CourseDetailDto course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }
}