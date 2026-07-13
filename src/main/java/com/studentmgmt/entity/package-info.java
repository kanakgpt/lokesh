/**
 * Student Management System - Entity/Domain Model Package
 *
 * This package contains the JPA entity classes representing database models.
 * Entities map directly to database tables.
 *
 * Package Contents:
 * - Student: JPA entity representing a student in the database
 *
 * Responsibilities:
 * - Define entity structure and database mapping
 * - Provide field-level validation through annotations
 * - Implement lifecycle callbacks (@PrePersist, @PreUpdate)
 * - Define relationships with other entities (future)
 * - Automatic timestamp management
 *
 * Design Pattern: Entity Pattern (Domain-Driven Design)
 *
 * Conventions:
 * - Use @Entity annotation
 * - Use @Table annotation with proper naming
 * - Use @Id and @GeneratedValue for primary key
 * - Use @Column for column configuration
 * - Use @PrePersist and @PreUpdate for lifecycle management
 * - Add validation annotations (@NotBlank, @Email, etc.)
 *
 * Features of Student Entity:
 * - Auto-generated ID (IDENTITY strategy)
 * - Email uniqueness constraint
 * - Validation annotations for database constraints
 * - Automatic timestamp management
 * - Indexed columns for performance
 *
 * Validation Annotations:
 * - @NotBlank: Field cannot be null or empty
 * - @Email: Field must be valid email format
 * - @Column: Define column constraints (unique, nullable, etc.)
 *
 * Lifecycle Callbacks:
 * - @PrePersist: Set timestamps before insert
 * - @PreUpdate: Update timestamps before update
 *
 * Index Configuration:
 * - Email: Unique index for fast lookups
 * - FirstName: Indexed for name searches
 * - LastName: Indexed for name searches
 *
 * Important Notes:
 * - Entities should NOT contain business logic
 * - Use Lombok (@Data, @NoArgsConstructor, @AllArgsConstructor)
 * - Keep entities simple and focused on data
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
package com.studentmgmt.entity;

