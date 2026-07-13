package com.studentmgmt.service;

import com.studentmgmt.dto.StudentDTO;
import com.studentmgmt.entity.Student;
import com.studentmgmt.exception.StudentNotFoundException;
import com.studentmgmt.repository.StudentRepository;
import com.studentmgmt.util.StudentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * StudentServiceImplTest - Unit tests for StudentServiceImpl
 *
 * This class demonstrates how to test the service layer using:
 * - JUnit 5 for testing framework
 * - Mockito for mocking dependencies
 * - Assertion helpers for test validation
 *
 * To run these tests:
 * mvn test -Dtest=StudentServiceImplTest
 *
 * Or run all tests:
 * mvn test
 *
 * Design Pattern Used: Arrange-Act-Assert (AAA)
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("StudentServiceImpl Tests")
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    private StudentDTO studentDTO;
    private Student student;

    /**
     * Setup method - runs before each test
     * Initializes test data
     */
    @BeforeEach
    void setUp() {
        // Initialize test DTOs
        studentDTO = new StudentDTO();
        studentDTO.setId(1L);
        studentDTO.setFirstName("John");
        studentDTO.setLastName("Doe");
        studentDTO.setEmail("john.doe@example.com");
        studentDTO.setPhoneNumber("1234567890");
        studentDTO.setDateOfBirth("2000-01-15");

        // Initialize test entities
        student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");
        student.setPhoneNumber("1234567890");
        student.setDateOfBirth("2000-01-15");
        student.setCreatedAt(LocalDateTime.now());
        student.setEnrollmentDate(LocalDateTime.now());
    }

    // ================== CREATE STUDENT TESTS ==================

    @Test
    @DisplayName("Should create student successfully with valid data")
    void testCreateStudentSuccess() {
        // Arrange
        when(studentRepository.findByEmail(studentDTO.getEmail()))
                .thenReturn(Optional.empty()); // Email doesn't exist
        when(studentMapper.dtoToEntity(studentDTO))
                .thenReturn(student);
        when(studentRepository.save(student))
                .thenReturn(student);
        when(studentMapper.entityToDto(student))
                .thenReturn(studentDTO);

        // Act
        StudentDTO result = studentService.createStudent(studentDTO);

        // Assert
        assertNotNull(result);
        assertEquals(studentDTO.getEmail(), result.getEmail());
        assertEquals(studentDTO.getFirstName(), result.getFirstName());

        // Verify interactions
        verify(studentRepository, times(1)).findByEmail(studentDTO.getEmail());
        verify(studentRepository, times(1)).save(any(Student.class));
        verify(studentMapper, times(1)).dtoToEntity(studentDTO);
        verify(studentMapper, times(1)).entityToDto(student);
    }

    @Test
    @DisplayName("Should throw exception when email already exists")
    void testCreateStudentWithDuplicateEmail() {
        // Arrange
        when(studentRepository.findByEmail(studentDTO.getEmail()))
                .thenReturn(Optional.of(student)); // Email already exists

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentService.createStudent(studentDTO)
        );
        assertEquals("Email already exists in the system", exception.getMessage());

        // Verify repository was called to check email
        verify(studentRepository, times(1)).findByEmail(studentDTO.getEmail());
        // Verify save was NOT called
        verify(studentRepository, never()).save(any(Student.class));
    }

    // ================== GET STUDENT TESTS ==================

    @Test
    @DisplayName("Should get student by ID successfully")
    void testGetStudentByIdSuccess() {
        // Arrange
        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));
        when(studentMapper.entityToDto(student))
                .thenReturn(studentDTO);

        // Act
        StudentDTO result = studentService.getStudentById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(studentDTO.getId(), result.getId());
        assertEquals(studentDTO.getEmail(), result.getEmail());

        // Verify
        verify(studentRepository, times(1)).findById(1L);
        verify(studentMapper, times(1)).entityToDto(student);
    }

    @Test
    @DisplayName("Should throw exception when student not found")
    void testGetStudentByIdNotFound() {
        // Arrange
        when(studentRepository.findById(999L))
                .thenReturn(Optional.empty());

        // Act & Assert
        StudentNotFoundException exception = assertThrows(
                StudentNotFoundException.class,
                () -> studentService.getStudentById(999L)
        );
        assertTrue(exception.getMessage().contains("Student not found with ID: 999"));

        // Verify
        verify(studentRepository, times(1)).findById(999L);
    }

    // ================== GET ALL STUDENTS TESTS ==================

    @Test
    @DisplayName("Should get all students successfully")
    void testGetAllStudentsSuccess() {
        // Arrange
        List<Student> students = Arrays.asList(student);
        List<StudentDTO> studentDTOs = Arrays.asList(studentDTO);

        when(studentRepository.findAll())
                .thenReturn(students);
        when(studentMapper.entityToDto(student))
                .thenReturn(studentDTO);

        // Act
        List<StudentDTO> result = studentService.getAllStudents();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(studentDTO.getEmail(), result.get(0).getEmail());

        // Verify
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return empty list when no students exist")
    void testGetAllStudentsEmpty() {
        // Arrange
        when(studentRepository.findAll())
                .thenReturn(Arrays.asList());

        // Act
        List<StudentDTO> result = studentService.getAllStudents();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());

        // Verify
        verify(studentRepository, times(1)).findAll();
    }

    // ================== UPDATE STUDENT TESTS ==================

    @Test
    @DisplayName("Should update student successfully with valid data")
    void testUpdateStudentSuccess() {
        // Arrange
        StudentDTO updateDTO = new StudentDTO();
        updateDTO.setFirstName("Jane");
        updateDTO.setLastName("Doe");
        updateDTO.setEmail("jane.doe@example.com");
        updateDTO.setPhoneNumber("9876543210");
        updateDTO.setDateOfBirth("2000-01-15");

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));
        when(studentRepository.findByEmail(updateDTO.getEmail()))
                .thenReturn(Optional.empty()); // New email doesn't exist
        doNothing().when(studentMapper).updateEntity(updateDTO, student);
        when(studentRepository.save(student))
                .thenReturn(student);
        when(studentMapper.entityToDto(student))
                .thenReturn(studentDTO);

        // Act
        StudentDTO result = studentService.updateStudent(1L, updateDTO);

        // Assert
        assertNotNull(result);
        verify(studentRepository, times(1)).findById(1L);
        verify(studentMapper, times(1)).updateEntity(updateDTO, student);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    @DisplayName("Should throw exception when updating to duplicate email")
    void testUpdateStudentWithDuplicateEmail() {
        // Arrange
        StudentDTO updateDTO = new StudentDTO();
        updateDTO.setEmail("another.email@example.com");
        Student anotherStudent = new Student();
        anotherStudent.setId(2L);
        anotherStudent.setEmail("another.email@example.com");

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));
        when(studentRepository.findByEmail("another.email@example.com"))
                .thenReturn(Optional.of(anotherStudent)); // Email already taken

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentService.updateStudent(1L, updateDTO)
        );
        assertEquals("Email already exists in the system", exception.getMessage());

        // Verify save was NOT called
        verify(studentRepository, never()).save(any(Student.class));
    }

    // ================== DELETE STUDENT TESTS ==================

    @Test
    @DisplayName("Should delete student successfully")
    void testDeleteStudentSuccess() {
        // Arrange
        when(studentRepository.existsById(1L))
                .thenReturn(true);
        doNothing().when(studentRepository).deleteById(1L);

        // Act
        studentService.deleteStudent(1L);

        // Assert - Verify delete was called
        verify(studentRepository, times(1)).existsById(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should throw exception when deleting non-existent student")
    void testDeleteStudentNotFound() {
        // Arrange
        when(studentRepository.existsById(999L))
                .thenReturn(false);

        // Act & Assert
        StudentNotFoundException exception = assertThrows(
                StudentNotFoundException.class,
                () -> studentService.deleteStudent(999L)
        );
        assertTrue(exception.getMessage().contains("Student not found with ID: 999"));

        // Verify delete was NOT called
        verify(studentRepository, never()).deleteById(any());
    }

    // ================== SEARCH TESTS ==================

    @Test
    @DisplayName("Should search students by first name successfully")
    void testSearchByFirstNameSuccess() {
        // Arrange
        List<Student> students = Arrays.asList(student);
        when(studentRepository.findByFirstNameContainingIgnoreCase("John"))
                .thenReturn(students);
        when(studentMapper.entityToDto(student))
                .thenReturn(studentDTO);

        // Act
        List<StudentDTO> result = studentService.searchByFirstName("John");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());

        // Verify
        verify(studentRepository, times(1))
                .findByFirstNameContainingIgnoreCase("John");
    }

    @Test
    @DisplayName("Should search students by last name successfully")
    void testSearchByLastNameSuccess() {
        // Arrange
        List<Student> students = Arrays.asList(student);
        when(studentRepository.findByLastNameContainingIgnoreCase("Doe"))
                .thenReturn(students);
        when(studentMapper.entityToDto(student))
                .thenReturn(studentDTO);

        // Act
        List<StudentDTO> result = studentService.searchByLastName("Doe");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Doe", result.get(0).getLastName());

        // Verify
        verify(studentRepository, times(1))
                .findByLastNameContainingIgnoreCase("Doe");
    }

    // ================== GET BY EMAIL TESTS ==================

    @Test
    @DisplayName("Should get student by email successfully")
    void testGetStudentByEmailSuccess() {
        // Arrange
        when(studentRepository.findByEmail("john.doe@example.com"))
                .thenReturn(Optional.of(student));
        when(studentMapper.entityToDto(student))
                .thenReturn(studentDTO);

        // Act
        StudentDTO result = studentService.getStudentByEmail("john.doe@example.com");

        // Assert
        assertNotNull(result);
        assertEquals("john.doe@example.com", result.getEmail());

        // Verify
        verify(studentRepository, times(1))
                .findByEmail("john.doe@example.com");
    }
}

