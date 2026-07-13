/**
 * Student Management System - Repository Layer Package
 *
 * This package contains the data access layer of the application.
 * Repositories handle all database operations.
 *
 * Package Contents:
 * - StudentRepository: JPA Repository interface for Student entity
 *
 * Responsibilities:
 * - Abstract database operations from service layer
 * - Provide CRUD operations (inherited from JpaRepository)
 * - Custom query methods for specific searches
 * - Database transaction coordination
 *
 * Design Pattern: Repository Pattern, Data Access Object (DAO)
 *
 * Conventions:
 * - Extend JpaRepository<Entity, ID>
 * - Use @Repository annotation
 * - Return Optional for single entity lookups
 * - Return List for multiple entity lookups
 * - Use descriptive method names (findBy*, search*)
 * - Use Spring Data query methods instead of manual queries
 *
 * Key Methods (Provided by JpaRepository):
 * - save(Student): Persist or update student
 * - findById(Long): Find student by ID
 * - findAll(): Get all students
 * - deleteById(Long): Delete student by ID
 * - existsById(Long): Check if student exists
 *
 * Custom Methods:
 * - findByEmail(String): Find student by email
 * - findByFirstNameContainingIgnoreCase(String): Search by first name
 * - findByLastNameContainingIgnoreCase(String): Search by last name
 *
 * SOLID Principles:
 * - Single Responsibility: Only data access
 * - Dependency Inversion: Depend on abstraction (repository interface)
 *
 * Query Methods Explanation:
 * - findBy: Returns Optional/List based on return type
 * - Containing: Partial string match (LIKE in SQL)
 * - IgnoreCase: Case-insensitive comparison
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
package com.studentmgmt.repository;

