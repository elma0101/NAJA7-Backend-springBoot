package com.naja7online.api.dto;

import java.util.List;





// The main Dashboard DTO
public record DashboardDto(
        String welcomeMessage,
        List<InProgressCourseDto> inProgressCourses,
        UserStatsDto stats
        // We can add achievements and activity charts later
) {}