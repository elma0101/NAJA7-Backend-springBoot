package com.naja7online.api.repository;

import com.naja7online.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA automatically creates the query for this method name
    Optional<User> findByEmail(String email);
}