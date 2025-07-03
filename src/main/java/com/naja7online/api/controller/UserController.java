package com.naja7online.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.naja7online.api.dto.*;
import com.naja7online.api.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    // Constructor injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me/profile")
    public ResponseEntity<UserProfileDto> getMyProfile(@AuthenticationPrincipal org.springframework.security.core.userdetails.User principal) {
        // The principal's name is the email we stored in the JWT
        UserProfileDto profile = userService.getUserProfile(principal.getUsername());
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/me/profile")
    public ResponseEntity<UserProfileDto> updateMyProfile(@AuthenticationPrincipal org.springframework.security.core.userdetails.User principal, @RequestBody UserProfileUpdateDto updateDto) {
        UserProfileDto updatedProfile = userService.updateUserProfile(principal.getUsername(), updateDto);
        return ResponseEntity.ok(updatedProfile);
    }

    @GetMapping("/me/dashboard")
    public ResponseEntity<DashboardDto> getMyDashboard(@AuthenticationPrincipal org.springframework.security.core.userdetails.User principal) {
        // For now, return some mock data. We can build the full service logic later.
        UserStatsDto stats = new UserStatsDto(5, 250, 12);
        List<InProgressCourseDto> courses = List.of(
                new InProgressCourseDto(1L, "Algèbre avancée", "", 60)
        );
        DashboardDto dashboardData = new DashboardDto("Bonjour, " + principal.getUsername(), courses, stats);
        return ResponseEntity.ok(dashboardData);
    }
}