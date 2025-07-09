package com.naja7online.api.dto;

import java.util.List;

public record CourseDetailDto(Long id, String title, String description, String imageUrl, List<ModuleDto> modules, boolean isEnrolled) {



}
