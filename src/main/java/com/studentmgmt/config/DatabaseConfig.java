package com.studentmgmt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * DatabaseConfig - Application configuration for database and transaction management
 *
 * This class provides Spring configuration for:
 * - Transaction management
 * - Database connection pooling
 * - JPA/Hibernate settings
 *
 * PostgreSQL Configuration (in application.properties):
 * - URL: jdbc:postgresql://localhost:5432/student_management_db
 * - Username: root
 * - Password: root
 * - DDL Auto: update (automatic schema updates)
 *
 * Design Principle: Configuration Segregation - All configs in one place
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
    // Configuration managed through application.properties
    // Transaction management is auto-configured by Spring Boot
}

