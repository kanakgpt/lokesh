package com.studentmgmt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Student Entity - JPA Entity class representing a student in the database
 *
 * This class maps to the 'students' table in PostgreSQL database.
 * It includes validation annotations for data integrity.
 *
 * Features:
 * - Auto timestamp management using @PrePersist and @PreUpdate
 * - Email uniqueness constraint
 * - Comprehensive field validation
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
@Entity
@Table(name = "students", indexes = {
    @Index(name = "idx_email", columnList = "email", unique = true),
    @Index(name = "idx_first_name", columnList = "first_name"),
    @Index(name = "idx_last_name", columnList = "last_name")
})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Column(nullable = false, length = 100)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(nullable = false, length = 100)
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false, length = 255)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Column(nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "date_of_birth", length = 20)
    private String dateOfBirth;

    @Column(name = "enrollment_date", nullable = false, updatable = false)
    private LocalDateTime enrollmentDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Student() {}

    public Student(Long id, String firstName, String lastName, String email, String phoneNumber, 
                   String dateOfBirth, LocalDateTime enrollmentDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.enrollmentDate = enrollmentDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * PrePersist method - Automatically set timestamps before entity is persisted
     */
    @PrePersist
    protected void onCreate() {
        this.enrollmentDate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * PreUpdate method - Automatically update 'updatedAt' timestamp before entity is updated
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}



