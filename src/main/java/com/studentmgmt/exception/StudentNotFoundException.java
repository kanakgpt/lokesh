package com.studentmgmt.exception;

/**
 * StudentNotFoundException - Custom exception for student not found scenarios
 *
 * This exception is thrown when a student with the requested ID or email
 * is not found in the database.
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
public class StudentNotFoundException extends RuntimeException {

    /**
     * Constructor with message
     *
     * @param message the error message
     */
    public StudentNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause
     *
     * @param message the error message
     * @param cause the cause of the exception
     */
    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

