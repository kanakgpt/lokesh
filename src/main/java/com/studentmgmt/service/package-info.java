/**
 * Student Management System - Service Layer Package
 *
 * This package contains the business logic layer of the application.
 * Services implement core business rules and operations.
 *
 * Package Contents:
 * - StudentService: Interface defining student operations contract
 * - StudentServiceImpl: Implementation of StudentService with business logic
 *
 * Responsibilities:
 * - Implement business logic for student management
 * - Validate business rules (email uniqueness, etc.)
 * - Coordinate between controllers and repositories
 * - Manage transactions (@Transactional)
 * - Logging of business operations
 * - Exception handling and throwing appropriate exceptions
 *
 * Design Pattern: Service Layer, Strategy Pattern (validation strategies)
 *
 * Conventions:
 * - Use @Service annotation for implementations
 * - Use @Transactional for transaction management
 * - Use @RequiredArgsConstructor for dependency injection
 * - Return DTOs instead of entities
 * - Throw appropriate exceptions for error scenarios
 *
 * Key Methods:
 * - createStudent(StudentDTO): Create new student
 * - getStudentById(Long): Retrieve student by ID
 * - getAllStudents(): Retrieve all students
 * - updateStudent(Long, StudentDTO): Update student
 * - deleteStudent(Long): Delete student
 * - searchByFirstName(String): Search by first name
 * - searchByLastName(String): Search by last name
 *
 * SOLID Principles:
 * - Single Responsibility: Only business logic
 * - Open/Closed: Interface-based design
 * - Liskov: Proper service implementation
 * - Interface Segregation: Focused service interface
 * - Dependency Inversion: Depends on abstractions
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
package com.studentmgmt.service;

