// src/main/java/com/naja7online/api/dto/LoginRequestDto.java
package com.naja7online.api.dto;

public class LoginRequestDto {
    private String email;
    private String password;

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}