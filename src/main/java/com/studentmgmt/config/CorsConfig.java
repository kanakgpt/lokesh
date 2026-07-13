package com.studentmgmt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS Configuration - Allow frontend to call backend APIs
 *
 * This configuration enables Cross-Origin Resource Sharing (CORS)
 * to allow the HTML/JavaScript frontend to make requests to the
 * REST API endpoints from different origins (localhost:8080).
 *
 * CORS is needed because the frontend (HTML/JS/CSS) is served
 * from the same origin as the API, but browsers enforce CORS
 * for security reasons.
 *
 * Configuration Details:
 * - Allow all origins (for development; restrict in production)
 * - Allow all HTTP methods (GET, POST, PUT, DELETE)
 * - Allow common headers
 * - Allow credentials
 * - Max age: 3600 seconds (1 hour)
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configure CORS mappings
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")  // Allow all origins (change to specific domains in production)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow all methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(false)  // Allow credentials (cookies, auth headers)
                .maxAge(3600);  // Cache preflight response for 1 hour
    }
}

