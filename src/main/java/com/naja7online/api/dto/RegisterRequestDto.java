package com.naja7online.api.dto;

// Using a record for a simple, immutable DTO
public record RegisterRequestDto(String name, String email, String password) {}