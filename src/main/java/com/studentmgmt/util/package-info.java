/**
 * Student Management System - Utility Package
 *
 * This package contains utility classes and helper methods.
 * Utilities provide common functionality used across layers.
 *
 * Package Contents:
 * - StudentMapper: Converts between StudentDTO and Student entity
 *
 * Responsibilities:
 * - Convert DTOs to entities (for persistence)
 * - Convert entities to DTOs (for API responses)
 * - Update entities with DTO data (for updates)
 * - Encapsulate conversion logic
 *
 * Design Pattern: Mapper Pattern, Converter Pattern
 *
 * Key Methods:
 *
 * 1. entityToDto(Student):
 *    - Converts JPA entity to DTO
 *    - Used in API response creation
 *    - Filters out internal fields
 *    - Returns null if entity is null
 *
 * 2. dtoToEntity(StudentDTO):
 *    - Converts DTO to JPA entity
 *    - Used when creating new student
 *    - Does NOT set system fields (ID, timestamps)
 *    - Returns null if DTO is null
 *
 * 3. updateEntity(StudentDTO, Student):
 *    - Merges DTO changes into existing entity
 *    - Used in update operations
 *    - Preserves ID and other system fields
 *    - Called before save in update operation
 *
 * Conventions:
 * - Use @Component annotation
 * - Return null if input is null (safe conversion)
 * - Include Javadoc for all methods
 * - Use descriptive method names
 *
 * Why Mapper Pattern?
 * ✓ DRY: Centralized conversion logic
 * ✓ Maintainability: Easy to modify conversions
 * ✓ Testability: Mapper can be tested independently
 * ✓ Flexibility: Different conversion strategies possible
 * ✓ Reusability: Mapper used across service layer
 *
 * Example Usage:
 *
 * In Service Layer:
 * - Student entity = studentMapper.dtoToEntity(dto)
 * - StudentDTO dto = studentMapper.entityToDto(entity)
 * - studentMapper.updateEntity(updateDto, existingEntity)
 *
 * Conversion Rules:
 * - All fields with same name are copied
 * - Field type mismatches handled explicitly
 * - System fields (ID, timestamps) handled appropriately
 * - Null values preserved from source
 *
 * Future Enhancements:
 * - Use ModelMapper library for automatic mapping
 * - Add field transformation rules
 * - Support nested object mapping
 *
 * @author Student Management System Team
 * @version 1.0.0
 */
package com.studentmgmt.util;

