package com.studentmgmt.controller;

import com.studentmgmt.dto.StudentDTO;
import com.studentmgmt.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * StudentController - REST API controller for Student operations
 *
 * This class exposes REST endpoints for managing students.
 * It handles HTTP requests and delegates business logic to StudentService.
 *
 * Base URL: http://localhost:8080/api/v1/students
 *
 * Design Principles:
 * - Single Responsibility: Only handles HTTP concerns
 * - Dependency Inversion: Depends on StudentService abstraction
 * - Separation of Concerns: Controller layer separated from service layer
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * POST /api/v1/students - Create a new student
     *
     * @param studentDTO the student data
     * @return 201 Created with student data
     */
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        logger.info("POST request received to create student");
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    /**
     * GET /api/v1/students/{id} - Get student by ID
     *
     * @param id the student ID
     * @return 200 OK with student data
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id) {
        logger.info("GET request received for student ID: {}", id);
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    /**
     * GET /api/v1/students - Get all students
     *
     * @return 200 OK with list of all students
     */
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        logger.info("GET request received for all students");
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * PUT /api/v1/students/{id} - Update student
     *
     * @param id the student ID
     * @param studentDTO the updated student data
     * @return 200 OK with updated student data
     */
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentDTO studentDTO) {
        logger.info("PUT request received to update student ID: {}", id);
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    /**
     * DELETE /api/v1/students/{id} - Delete student
     *
     * @param id the student ID
     * @return 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        logger.info("DELETE request received for student ID: {}", id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/v1/students/email/{email} - Get student by email
     *
     * @param email the student email
     * @return 200 OK with student data
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<StudentDTO> getStudentByEmail(@PathVariable String email) {
        logger.info("GET request received for student email: {}", email);
        StudentDTO student = studentService.getStudentByEmail(email);
        return ResponseEntity.ok(student);
    }

    /**
     * GET /api/v1/students/search/firstName?firstName=value - Search by first name
     *
     * @param firstName the first name to search
     * @return 200 OK with list of matching students
     */
    @GetMapping("/search/firstName")
    public ResponseEntity<List<StudentDTO>> searchByFirstName(@RequestParam String firstName) {
        logger.info("GET request received to search by first name: {}", firstName);
        List<StudentDTO> students = studentService.searchByFirstName(firstName);
        return ResponseEntity.ok(students);
    }

    /**
     * GET /api/v1/students/search/lastName?lastName=value - Search by last name
     *
     * @param lastName the last name to search
     * @return 200 OK with list of matching students
     */
    @GetMapping("/search/lastName")
    public ResponseEntity<List<StudentDTO>> searchByLastName(@RequestParam String lastName) {
        logger.info("GET request received to search by last name: {}", lastName);
        List<StudentDTO> students = studentService.searchByLastName(lastName);
        return ResponseEntity.ok(students);
    }
}

