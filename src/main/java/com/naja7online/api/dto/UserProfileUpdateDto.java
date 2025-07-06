package com.naja7online.api.dto;

// Only include fields the user is allowed to change
public record UserProfileUpdateDto(String name, String level) {}