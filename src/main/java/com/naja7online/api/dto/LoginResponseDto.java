
package com.naja7online.api.dto;

public class LoginResponseDto {
    private String token;

    public LoginResponseDto(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}