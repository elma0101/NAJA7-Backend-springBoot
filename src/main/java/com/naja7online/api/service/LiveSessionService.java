package com.naja7online.api.service;

import com.naja7online.api.dto.LiveSessionDto;
import com.naja7online.api.model.LiveSession;
import com.naja7online.api.repository.LiveSessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LiveSessionService {

    private final LiveSessionRepository liveSessionRepository;

    public LiveSessionService(LiveSessionRepository liveSessionRepository) {
        this.liveSessionRepository = liveSessionRepository;
    }

    /**
     * Gets a list of all upcoming live sessions.
     * @return A list of LiveSessionDto objects.
     */
    public List<LiveSessionDto> getUpcomingSessions() {
        // Use the repository method to find all sessions scheduled after the current time
        return liveSessionRepository.findBySessionTimeAfterOrderBySessionTimeAsc(LocalDateTime.now())
                .stream()
                .map(this::convertToDto) // Convert each session entity to a DTO
                .collect(Collectors.toList());
    }

    /**
     * Gets a list of all past live sessions.
     * @return A list of LiveSessionDto objects.
     */
    public List<LiveSessionDto> getPastSessions() {
        // Use the repository method to find all sessions that happened before now
        return liveSessionRepository.findBySessionTimeBeforeOrderBySessionTimeDesc(LocalDateTime.now())
                .stream()
                .map(this::convertToDto) // Convert each session entity to a DTO
                .collect(Collectors.toList());
    }

    /**
     * A private helper method to map a LiveSession entity to a LiveSessionDto.
     * This avoids code duplication.
     * @param session The LiveSession entity from the database.
     * @return The corresponding LiveSessionDto.
     */
    private LiveSessionDto convertToDto(LiveSession session) {
        return new LiveSessionDto(
                session.getId(),
                session.getTitle(),
                session.getDescription(),
                session.getSessionTime(),
                session.getInstructor(),
                session.getRecordingUrl()
        );
    }
}