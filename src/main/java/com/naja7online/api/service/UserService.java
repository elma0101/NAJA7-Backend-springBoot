package com.naja7online.api.service;

import com.naja7online.api.dto.*;
import com.naja7online.api.model.User;
import com.naja7online.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfileDto getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserProfileDto(user.getId(), user.getName(), user.getEmail(), user.getLevel(), user.getRole(), null); // Avatar URL is null for now
    }

    public UserProfileDto updateUserProfile(String email, UserProfileUpdateDto updateDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(updateDto.name());
        user.setLevel(updateDto.level());

        User updatedUser = userRepository.save(user);
        return new UserProfileDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(), updatedUser.getLevel(), updatedUser.getRole(), null);
    }
}