// src/main/java/com/naja7online/api/config/WebConfig.java
package com.naja7online.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Apply CORS to all API endpoints
                        .allowedOrigins("http://localhost:5173") // Allow requests from your React frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow these HTTP methods
                        .allowedHeaders("*") // Allow all headers (including Authorization)
                        .allowCredentials(true); // Allow cookies and credentials
            }
        };
    }
}