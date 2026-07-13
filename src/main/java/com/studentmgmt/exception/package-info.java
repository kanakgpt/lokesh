/**
 * Student Management System - Exception Handling Package
 *
 * This package contains custom exceptions and global exception handling.
 * Provides centralized error handling and consistent error responses.
 *
 * Package Contents:
 * - StudentNotFoundException: Custom exception for not found scenarios
 * - ErrorResponse: Standard error response DTO
 * - GlobalExceptionHandler: Global exception handler for all controllers
 *
 * Responsibilities:
 * - Define custom exceptions for domain-specific errors
 * - Handle exceptions at application level
 * - Format error responses consistently
 * - Log errors for debugging
 * - Provide appropriate HTTP status codes
 *
 * Design Pattern: Exception Handling Pattern, Decorator Pattern
 *
 * Exception Types Handled:
 *
 * 1. StudentNotFoundException (404 Not Found)
 *    - Thrown when student with given ID/email not found
 *    - Custom exception extends RuntimeException
 *
 * 2. MethodArgumentNotValidException (400 Bad Request)
 *    - Validation errors from @Valid annotation
 *    - Shows field-level validation errors
 *
 * 3. IllegalArgumentException (400 Bad Request)
 *    - Email already exists
 *    - Business rule violations
 *
 * 4. Generic Exception (500 Internal Server Error)
 *    - Unexpected errors
 *    - Fallback handler for uncaught exceptions
 *
 * HTTP Status Codes Used:
 * - 200 OK: Successful request
 * - 201 Created: Resource created successfully
 * - 204 No Content: Successful deletion
 * - 400 Bad Request: Invalid input or business rule violation
 * - 404 Not Found: Resource not found
 * - 500 Internal Server Error: Unexpected server error
 *
 * GlobalExceptionHandler Features:
 * - @RestControllerAdvice: Centralized exception handling
 * - @ExceptionHandler: Method-level handlers
 * - Logging of all exceptions
 * - Consistent error response format
 * - Field-level validation error details
 *
 * ErrorResponse Format:
 * {
 *   "timestamp": "2024-01-15T10:30:00",
 *   "status": 400,
 *   "error": "Bad Request",
 *   "message": "Detailed error message"
 * }
 *
 * Best Practices:
 * - Always throw appropriate exceptions
 * - Include meaningful error messages
 * - Log errors for debugging
 * - Don't expose sensitive information in error messages
 * - Use HTTP status codes correctly
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
package com.studentmgmt.exception;

