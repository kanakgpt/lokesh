/**
 * API Constants and Base URLs
 */
export const API_BASE_URL = 'http://localhost:8080/api/v1/students';
export const DEFAULT_PROFILE_IMAGE = '👤';

/**
 * API CRUD Operations
 */

/**
 * Create new student
 * @param {Object} studentData - Student data
 */
export async function createStudent(studentData) {
    const response = await fetch(API_BASE_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(studentData)
    });

    if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Failed to create student');
    }

    return await response.json();
}

/**
 * Load all students
 */
export async function loadStudents() {
    const response = await fetch(API_BASE_URL);

    if (!response.ok) {
        throw new Error('Failed to load students');
    }

    return await response.json();
}

/**
 * Get single student by ID
 * @param {number} id - Student ID
 */
export async function getStudent(id) {
    const response = await fetch(`${API_BASE_URL}/${id}`);

    if (!response.ok) {
        throw new Error('Failed to load student');
    }

    return await response.json();
}

/**
 * Update student
 * @param {number} id - Student ID
 * @param {Object} studentData - Updated student data
 */
export async function updateStudent(id, studentData) {
    const response = await fetch(`${API_BASE_URL}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(studentData)
    });

    if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Failed to update student');
    }

    return await response.json();
}

/**
 * Delete student
 * @param {number} id - Student ID
 */
export async function deleteStudent(id) {
    const response = await fetch(`${API_BASE_URL}/${id}`, {
        method: 'DELETE'
    });

    if (!response.ok) {
        throw new Error('Failed to delete student');
    }

    return true;
}
