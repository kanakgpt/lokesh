package com.studentmgmt.exception;

import java.time.LocalDateTime;

/**
 * ErrorResponse - Standard error response DTO for API errors
 *
 * This class is used to format error responses sent back to clients
 * when exceptions occur during API request processing.
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

    // Constructors
    public ErrorResponse() {}

    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    // Getters and Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


