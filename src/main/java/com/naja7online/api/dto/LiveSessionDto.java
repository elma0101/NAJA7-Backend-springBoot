package com.naja7online.api.dto;

import java.time.LocalDateTime;

public record LiveSessionDto(
        Long id,
        String title,
        String description,
        LocalDateTime sessionTime,
        String instructor,
        String recordingUrl
) {}