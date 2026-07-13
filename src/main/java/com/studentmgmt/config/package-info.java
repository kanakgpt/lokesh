/**
 * Student Management System - Configuration Package
 *
 * This package contains application configuration classes.
 * Configurations provide beans and settings for the entire application.
 *
 * Package Contents:
 * - DatabaseConfig: Database and transaction management configuration
 *
 * Responsibilities:
 * - Configure database connection pooling
 * - Enable transaction management
 * - Set up JPA/Hibernate settings
 * - Provide application-wide configuration beans
 * - Override default Spring Boot configuration if needed
 *
 * Design Pattern: Configuration Pattern, Bean Factory Pattern
 *
 * Current Configurations:
 *
 * 1. DatabaseConfig:
 *    - @Configuration: Marks class as Spring configuration
 *    - @EnableTransactionManagement: Enables @Transactional annotation
 *    - Database settings in application.properties
 *
 * Database Configuration Properties (application.properties):
 * - spring.datasource.url: PostgreSQL connection URL
 * - spring.datasource.username: Database user (root)
 * - spring.datasource.password: Database password (root)
 * - spring.datasource.driver-class-name: PostgreSQL driver
 * - spring.jpa.database-platform: Hibernate dialect
 * - spring.jpa.hibernate.ddl-auto: Schema update strategy
 *
 * Transaction Management:
 * - @EnableTransactionManagement: Activates @Transactional support
 * - Used in service layer for ACID compliance
 * - Read-only transactions for queries
 * - Read-write transactions for modifications
 *
 * JPA/Hibernate Configuration:
 * - DDL Auto: update (auto-create/modify tables)
 * - Show SQL: false (for production, true for debug)
 * - Format SQL: true (for better log readability)
 *
 * Conventions:
 * - Use @Configuration annotation
 * - Use descriptive class names
 * - Group related configurations
 * - Include Javadoc comments
 * - Consider security in database settings
 *
 * Common Configuration Classes to Add:
 *
 * 1. CorsConfig:
 *    - Configure CORS (Cross-Origin Resource Sharing)
 *    - Allow specific origins
 *    - Allow specific HTTP methods
 *
 * 2. SecurityConfig:
 *    - Configure Spring Security
 *    - Authentication and authorization
 *    - JWT token configuration
 *
 * 3. ValidationConfig:
 *    - Custom validation configurations
 *    - Validation message source
 *
 * 4. CachingConfig:
 *    - Configure caching strategy
 *    - Redis or local caching
 *
 * Database Connection Properties Order of Resolution:
 * 1. Environment variables (DATASOURCE_URL, etc.)
 * 2. application-{profile}.properties
 * 3. application.properties
 * 4. Default Spring Boot values
 * 5. application.properties defaults
 *
 * Profile-Specific Configurations:
 * - application-dev.properties: Development settings
 * - application-prod.properties: Production settings
 * - application-test.properties: Test settings
 *
 * Example Profile Activation:
 * Command line: java -jar app.jar --spring.profiles.active=prod
 * IDE: Set active profile in run configuration
 * application.properties: spring.profiles.active=dev
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
package com.studentmgmt.config;

