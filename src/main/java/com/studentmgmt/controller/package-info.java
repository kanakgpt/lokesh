/**
 * Student Management System - REST API Package
 *
 * This package contains the controller layer of the application.
 * Controllers handle HTTP requests and responses.
 *
 * Package Contents:
 * - StudentController: REST endpoints for student management
 *
 * Responsibilities:
 * - Handle HTTP requests (GET, POST, PUT, DELETE)
 * - Validate request parameters
 * - Call service layer methods
 * - Return appropriate HTTP responses and status codes
 * - Error handling coordination
 *
 * Design Pattern: Presentation Layer (MVC Pattern)
 *
 * Conventions:
 * - Use @RestController annotation
 * - Use @RequestMapping for base URL
 * - Use proper HTTP methods (@GetMapping, @PostMapping, etc.)
 * - Use @Valid for request body validation
 * - Log method entry/exit for monitoring
 *
 * Base URL: /api/v1/students
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
package com.studentmgmt.controller;

