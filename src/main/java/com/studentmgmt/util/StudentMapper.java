package com.studentmgmt.util;

import com.studentmgmt.dto.StudentDTO;
import com.studentmgmt.entity.Student;
import org.springframework.stereotype.Component;

/**
 * StudentMapper - Utility class for converting between Student entity and StudentDTO
 *
 * This mapper class handles the conversion logic between the JPA entity layer
 * and the DTO layer. It implements the Mapper Pattern for clean separation
 * between internal models and API contracts.
 *
 * Design Pattern: Mapper Pattern - Converts between different object types
 * Principle: Single Responsibility - Only handles entity/DTO conversions
 * Principle: DRY (Don't Repeat Yourself) - Centralized conversion logic
 *
 * Methods:
 * - entityToDto: Convert JPA entity to DTO (for API responses)
 * - dtoToEntity: Convert DTO to JPA entity (for database operations)
 * - updateEntity: Merge DTO data into existing entity (for updates)
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
@Component
public class StudentMapper {

    /**
     * Convert Student entity to StudentDTO
     * Used when sending student data in API responses
     *
     * @param student the JPA entity
     * @return StudentDTO with entity data, or null if entity is null
     */
    public StudentDTO entityToDto(Student student) {
        if (student == null) {
            return null;
        }

        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setPhoneNumber(student.getPhoneNumber());
        dto.setDateOfBirth(student.getDateOfBirth());
        return dto;
    }

    /**
     * Convert StudentDTO to Student entity
     * Used when creating new students from API requests
     *
     * Note: ID is not set as it will be auto-generated
     * Timestamps are set by entity's @PrePersist method
     *
     * @param dto the DTO from API request
     * @return Student entity ready for persistence, or null if DTO is null
     */
    public Student dtoToEntity(StudentDTO dto) {
        if (dto == null) {
            return null;
        }

        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setDateOfBirth(dto.getDateOfBirth());
        return student;
    }

    /**
     * Update existing Student entity with data from StudentDTO
     * Used for PATCH/PUT operations to merge changes into existing entity
     *
     * This method preserves ID, and allows the @PreUpdate method to handle
     * timestamp updates.
     *
     * @param dto the DTO containing updated data
     * @param student the existing entity to update
     */
    public void updateEntity(StudentDTO dto, Student student) {
        if (dto == null || student == null) {
            return;
        }

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setDateOfBirth(dto.getDateOfBirth());
    }
}

