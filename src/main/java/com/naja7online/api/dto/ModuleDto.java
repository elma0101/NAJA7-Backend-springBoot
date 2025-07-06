package com.naja7online.api.dto;

import java.util.List;

public record ModuleDto(Long id, String title, List<LessonDto> lessons) {}