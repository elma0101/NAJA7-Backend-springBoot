package com.naja7online.api.controller;

import com.naja7online.api.dto.RegisterRequestDto;
import com.naja7online.api.model.User;
import com.naja7online.api.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.naja7online.api.dto.LoginRequestDto;
import com.naja7online.api.dto.LoginResponseDto;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        String token = authService.login(request);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequestDto request) {
        User registeredUser = authService.register(request);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}