package com.naja7online.api.service;

import com.naja7online.api.dto.*;
import com.naja7online.api.model.User;
import com.naja7online.api.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.naja7online.api.dto.CourseSummaryDto;
import com.naja7online.api.model.Enrollment;
import com.naja7online.api.repository.EnrollmentRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;

    public UserService(UserRepository userRepository, EnrollmentRepository enrollmentRepository) {
        this.userRepository = userRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public UserProfileDto getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserProfileDto( user.getId(), user.getName(), user.getEmail(), user.getLevel(), user.getRole(), null); // Avatar URL is null for now
    }

    public UserProfileDto updateUserProfile(String email, UserProfileUpdateDto updateDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(updateDto.name());
        user.setLevel(updateDto.level());

        User updatedUser = userRepository.save(user);
        return new UserProfileDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(), updatedUser.getLevel(), updatedUser.getRole(), null);
    }

    public List<CourseSummaryDto> getEnrolledCourses(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        // Find all enrollments for this user
        List<Enrollment> enrollments = enrollmentRepository.findByUser_Id(user.getId());

        // From the enrollments, get the courses and map them to DTOs
        return enrollments.stream()
                .map(enrollment -> {
                    var course = enrollment.getCourse();
                    // Make sure your CourseSummaryDto has a constructor or you use a builder
                    return new CourseSummaryDto(
                            course.getId(),
                            course.getTitle(),
                            course.getDescription(),
                            course.getImageUrl()
                    );
                })
                .collect(Collectors.toList());
    }

}