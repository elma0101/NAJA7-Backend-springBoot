// src/main/java/com/naja7online/api/dto/RegisterRequestDto.java
package com.naja7online.api.dto;

public class RegisterRequestDto {
    private String name;
    private String email;
    private String password;
    private String level;

    // Getters and Setters are required for Jackson (the JSON library) to work
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
}