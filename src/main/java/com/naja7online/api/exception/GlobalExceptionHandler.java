// src/main/java/com/naja7online/api/exception/GlobalExceptionHandler.java
package com.naja7online.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

// This annotation allows this class to handle exceptions globally across all controllers
@ControllerAdvice
public class GlobalExceptionHandler {

    // This method will specifically handle exceptions thrown during authentication failure
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthenticationException(AuthenticationException ex) {
        // We return a clear, structured JSON error with a 401 Unauthorized status
        Map<String, String> errorResponse = Map.of(
                "message", "Invalid email or password."
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}