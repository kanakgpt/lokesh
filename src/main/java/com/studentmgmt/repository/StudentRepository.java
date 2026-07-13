package com.studentmgmt.repository;

import com.studentmgmt.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

/**
 * StudentRepository - Data Access Layer for Student entity
 *
 * This interface extends JpaRepository to provide CRUD operations
 * and custom query methods for Student entity.
 *
 * Design Pattern: Repository Pattern - Abstracts data access logic
 * Principle: Single Responsibility - Only handles database operations
 * Principle: Dependency Inversion - Service depends on abstraction, not concrete DB implementation
 *
 * Methods:
 * - CRUD operations inherited from JpaRepository
 * - Custom queries for searching by email, firstName, lastName
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Find student by email (case-insensitive)
     *
     * @param email the student email
     * @return Optional containing student if found
     */
    Optional<Student> findByEmail(String email);

    /**
     * Search students by firstName (case-insensitive, partial match)
     *
     * @param firstName the first name to search
     * @return List of matching students
     */
    List<Student> findByFirstNameContainingIgnoreCase(String firstName);

    /**
     * Search students by lastName (case-insensitive, partial match)
     *
     * @param lastName the last name to search
     * @return List of matching students
     */
    List<Student> findByLastNameContainingIgnoreCase(String lastName);
}

