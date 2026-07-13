package com.studentmgmt.service;

import com.studentmgmt.dto.StudentDTO;
import com.studentmgmt.entity.Student;
import com.studentmgmt.exception.StudentNotFoundException;
import com.studentmgmt.repository.StudentRepository;
import com.studentmgmt.util.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * StudentServiceImpl - Implementation of StudentService
 *
 * This class contains the business logic for all student-related operations.
 * It handles validation, transformation between DTOs and entities, and delegates
 * data access to the repository layer.
 *
 * Design Patterns Used:
 * - Strategy Pattern: Validation strategies for different business rules
 * - Repository Pattern: Delegates data access to StudentRepository
 * - Mapper Pattern: Uses StudentMapper for DTO/Entity conversion
 *
 * SOLID Principles:
 * - Single Responsibility: Only business logic, no HTTP or DB-specific code
 * - Open/Closed: Open for extension through interface implementation
 * - Liskov: Implements StudentService contract properly
 * - Interface Segregation: Depends on focused interface only
 * - Dependency Inversion: Depends on abstractions (repository, mapper), not concrete classes
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    /**
     * Create a new student with validation
     * Strategy Pattern: Email duplication validation strategy
     */
    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        logger.info("Creating new student with email: {}", studentDTO.getEmail());

        // Validation Strategy: Check if email already exists
        if (studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
            logger.warn("Attempt to create student with existing email: {}", studentDTO.getEmail());
            throw new IllegalArgumentException("Email already exists in the system");
        }

        Student student = studentMapper.dtoToEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        logger.info("Student created successfully with ID: {}", savedStudent.getId());
        return studentMapper.entityToDto(savedStudent);
    }

    /**
     * Retrieve student by ID
     */
    @Override
    @Transactional(readOnly = true)
    public StudentDTO getStudentById(Long id) {
        logger.info("Fetching student with ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Student not found with ID: {}", id);
                    return new StudentNotFoundException("Student not found with ID: " + id);
                });
        return studentMapper.entityToDto(student);
    }

    /**
     * Retrieve all students
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getAllStudents() {
        logger.info("Fetching all students");
        List<Student> students = studentRepository.findAll();
        logger.info("Total students found: {}", students.size());
        return students.stream()
                .map(studentMapper::entityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Update existing student with email duplication validation
     * Strategy Pattern: Email validation with update scenario
     */
    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        logger.info("Updating student with ID: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Student not found with ID: {}", id);
                    return new StudentNotFoundException("Student not found with ID: " + id);
                });

        // Validation Strategy: Check if new email is already taken by another student
        if (!student.getEmail().equals(studentDTO.getEmail()) &&
            studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
            logger.warn("Email already exists: {}", studentDTO.getEmail());
            throw new IllegalArgumentException("Email already exists in the system");
        }

        studentMapper.updateEntity(studentDTO, student);
        Student updatedStudent = studentRepository.save(student);
        logger.info("Student updated successfully with ID: {}", id);
        return studentMapper.entityToDto(updatedStudent);
    }

    /**
     * Delete student by ID
     */
    @Override
    public void deleteStudent(Long id) {
        logger.info("Deleting student with ID: {}", id);

        if (!studentRepository.existsById(id)) {
            logger.error("Student not found with ID: {}", id);
            throw new StudentNotFoundException("Student not found with ID: " + id);
        }

        studentRepository.deleteById(id);
        logger.info("Student deleted successfully with ID: {}", id);
    }

    /**
     * Retrieve student by email
     */
    @Override
    @Transactional(readOnly = true)
    public StudentDTO getStudentByEmail(String email) {
        logger.info("Fetching student with email: {}", email);
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("Student not found with email: {}", email);
                    return new StudentNotFoundException("Student not found with email: " + email);
                });
        return studentMapper.entityToDto(student);
    }

    /**
     * Search students by first name
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> searchByFirstName(String firstName) {
        logger.info("Searching students by first name: {}", firstName);
        List<Student> students = studentRepository.findByFirstNameContainingIgnoreCase(firstName);
        logger.info("Found {} students with first name: {}", students.size(), firstName);
        return students.stream()
                .map(studentMapper::entityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Search students by last name
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> searchByLastName(String lastName) {
        logger.info("Searching students by last name: {}", lastName);
        List<Student> students = studentRepository.findByLastNameContainingIgnoreCase(lastName);
        logger.info("Found {} students with last name: {}", students.size(), lastName);
        return students.stream()
                .map(studentMapper::entityToDto)
                .collect(Collectors.toList());
    }
}

