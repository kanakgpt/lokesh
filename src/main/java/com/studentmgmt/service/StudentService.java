package com.studentmgmt.service;

import com.studentmgmt.dto.StudentDTO;
import java.util.List;

/**
 * StudentService - Business logic contract for Student operations
 *
 * This interface defines the contract for all student-related business operations.
 * It provides CRUD operations and search functionality.
 *
 * Design Pattern: Strategy Pattern - Different implementation strategies can be used
 * Principle: Interface Segregation - Focused, single-purpose interface
 * Principle: Open/Closed Principle - Open for extension, closed for modification
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
public interface StudentService {

    /**
     * Create a new student
     *
     * @param studentDTO the student data
     * @return created student DTO with generated ID
     * @throws IllegalArgumentException if email already exists
     */
    StudentDTO createStudent(StudentDTO studentDTO);

    /**
     * Get student by ID
     *
     * @param id the student ID
     * @return student DTO
     * @throws StudentNotFoundException if student not found
     */
    StudentDTO getStudentById(Long id);

    /**
     * Get all students
     *
     * @return list of all students
     */
    List<StudentDTO> getAllStudents();

    /**
     * Update existing student
     *
     * @param id the student ID
     * @param studentDTO the updated student data
     * @return updated student DTO
     * @throws StudentNotFoundException if student not found
     * @throws IllegalArgumentException if email already exists
     */
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);

    /**
     * Delete student
     *
     * @param id the student ID
     * @throws StudentNotFoundException if student not found
     */
    void deleteStudent(Long id);

    /**
     * Get student by email
     *
     * @param email the student email
     * @return student DTO
     * @throws StudentNotFoundException if student not found
     */
    StudentDTO getStudentByEmail(String email);

    /**
     * Search students by first name
     *
     * @param firstName the first name to search
     * @return list of matching students
     */
    List<StudentDTO> searchByFirstName(String firstName);

    /**
     * Search students by last name
     *
     * @param lastName the last name to search
     * @return list of matching students
     */
    List<StudentDTO> searchByLastName(String lastName);
}

