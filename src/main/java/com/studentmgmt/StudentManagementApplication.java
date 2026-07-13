package com.studentmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main Application class for Student Management System
 *
 * This is the entry point for the Spring Boot application.
 * Architecture: Follows SOLID principles with separate packages for:
 * - Entity: Database models
 * - DTO: Data Transfer Objects
 * - Repository: Data Access Layer
 * - Service: Business Logic Layer
 * - Controller: REST API Layer
 * - Exception: Custom exceptions and global exception handling
 * - Config: Application configurations
 * - Util: Utility classes for mapping and conversions
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
@SpringBootApplication
public class StudentManagementApplication {

    private static final Logger logger = LoggerFactory.getLogger(StudentManagementApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
        logger.info("========================================");
        logger.info("Student Management System Started Successfully");
        logger.info("Server is running on http://localhost:8080");
        logger.info("REST API Base URL: http://localhost:8080/api/v1/students");
        logger.info("========================================");
    }
}

