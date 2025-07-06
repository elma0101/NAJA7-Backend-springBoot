package com.naja7online.api.repository;

import com.naja7online.api.model.LiveSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface LiveSessionRepository extends JpaRepository<LiveSession, Long> {

    /**
     * Finds all sessions scheduled to occur after the given time.
     * Spring Data JPA automatically creates the query for this method.
     * The 'OrderBySessionTimeAsc' part ensures the results are sorted from soonest to latest.
     */
    List<LiveSession> findBySessionTimeAfterOrderBySessionTimeAsc(LocalDateTime time);

    /**
     * Finds all sessions that occurred before the given time.
     * The 'OrderBySessionTimeDesc' part ensures the results are sorted from most recent to oldest.
     */
    List<LiveSession> findBySessionTimeBeforeOrderBySessionTimeDesc(LocalDateTime time);

}