package com.naja7online.api.controller;

import com.naja7online.api.dto.LiveSessionDto;
import com.naja7online.api.service.LiveSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/live-sessions")
@CrossOrigin(origins = "http://localhost:5173") // Allows our frontend to call these endpoints
public class LiveSessionController {

    private final LiveSessionService liveSessionService;

    public LiveSessionController(LiveSessionService liveSessionService) {
        this.liveSessionService = liveSessionService;
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<LiveSessionDto>> getUpcomingSessions() {
        List<LiveSessionDto> upcomingSessions = liveSessionService.getUpcomingSessions();
        return ResponseEntity.ok(upcomingSessions);
    }

    @GetMapping("/past")
    public ResponseEntity<List<LiveSessionDto>> getPastSessions() {
        List<LiveSessionDto> pastSessions = liveSessionService.getPastSessions();
        return ResponseEntity.ok(pastSessions);
    }
}