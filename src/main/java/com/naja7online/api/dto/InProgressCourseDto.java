package com.naja7online.api.dto;

// A record to hold summary of a course in progress
public record InProgressCourseDto(Long id, String title, String imageUrl, int progress) {}