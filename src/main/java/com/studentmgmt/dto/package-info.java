/**
 * Student Management System - Data Transfer Object Package
 *
 * This package contains Data Transfer Objects (DTOs) for API communication.
 * DTOs are used to transfer data between layers without exposing internal entities.
 *
 * Package Contents:
 * - StudentDTO: Data transfer object for student API requests/responses
 *
 * Responsibilities:
 * - Define API request/response structure
 * - Provide API-level validation
 * - Separate API contract from database entities
 * - Include only necessary fields for API
 *
 * Design Pattern: DTO Pattern (Data Transfer Object)
 *
 * Conventions:
 * - Use plain Java classes (not entities)
 * - Include validation annotations (@NotBlank, @Email)
 * - Use Lombok annotations (@Data, @NoArgsConstructor, @AllArgsConstructor)
 * - Follow naming convention: {EntityName}DTO
 * - Include Javadoc comments
 *
 * Validation:
 * - DTOs define API-level validation
 * - Triggered before controller method execution
 * - Used with @Valid annotation in controller
 *
 * Benefits Over Using Entities Directly:
 * ✓ API contract independence - Entity changes don't affect API
 * ✓ Security - Hide sensitive internal fields
 * ✓ Performance - Include only necessary fields
 * ✓ Flexibility - API can evolve independently
 * ✓ Validation - API-specific validation rules
 *
 * DTO vs Entity:
 * Entity: Persisted to database, contains business logic
 * DTO: Transferred via API, for data transport only
 *
 * Mapper Responsibility:
 * - Convert Entity to DTO for API responses
 * - Convert DTO to Entity for API requests
 * - See StudentMapper in util package
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
package com.studentmgmt.dto;

