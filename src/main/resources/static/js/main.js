/**
 * Student Management System - Main Entry Point
 */
import * as api from './api.js';
import * as ui from './ui.js';
import * as form from './form.js';
import { showToast, log, formatDateTime } from './utils.js';

// Application State
let students = [];
let filteredStudents = [];
let currentEditingId = null;
let currentDeleteId = null;

/**
 * Initialize application
 */
export function initApp() {
    log('Application initialized');
    ui.showSection('home');
    refreshStudents();
    setupEventListeners();
}


/**
 * Setup event listeners
 */
function setupEventListeners() {
    // Form submission
    const studentForm = document.getElementById('studentForm');
    if (studentForm) {
        studentForm.addEventListener('submit', handleFormSubmit);
    }

    // Search input
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('keyup', handleSearch);
    }

    // Global click listener for dynamic elements (Edit/Delete/View buttons)
    document.addEventListener('click', (e) => {
        const target = e.target;

        if (target.classList.contains('action-edit')) {
            const id = parseInt(target.getAttribute('data-id'));
            editStudent(id);
        } else if (target.classList.contains('action-delete')) {
            const id = parseInt(target.getAttribute('data-id'));
            openDeleteModal(id);
        } else if (target.classList.contains('action-view')) {
            const id = parseInt(target.getAttribute('data-id'));
            viewStudentDetail(id);
        }
    });

    // Expose necessary functions to window for HTML onclick handlers (already done at top level)
}

/**
 * Refresh student list from API
 */
async function refreshStudents() {
    try {
        students = await api.loadStudents();
        filteredStudents = [...students];
        ui.renderStudentsTable(filteredStudents);
        ui.updateStatistics(students.length);
    } catch (error) {
        log(`Load error: ${error.message}`);
        showToast('Failed to load students', 'error');
        ui.showNoStudentsMessage();
    }
}

/**
 * Handle form submission
 */
async function handleFormSubmit(event) {
    event.preventDefault();
    form.clearAllErrors();

    if (!form.validateForm()) {
        showToast('Please fix the validation errors', 'error');
        return;
    }

    const studentData = form.getFormData();

    try {
        if (currentEditingId) {
            showToast('Updating student...', 'info');
            await api.updateStudent(currentEditingId, studentData);
            showToast('✓ Student updated successfully!', 'success');
        } else {
            showToast('Creating student...', 'info');
            await api.createStudent(studentData);
            showToast('✓ Student registered successfully!', 'success');
        }
        resetForm();
        refreshStudents();
    } catch (error) {
        log(`Submit error: ${error.message}`);
        showToast(error.message || 'Action failed', 'error');
    }
}

/**
 * Reset form and state
 */
function resetForm() {
    form.resetFormFields();
    currentEditingId = null;
    ui.showSection('studentList');
}

/**
 * Handle search input
 */
function handleSearch() {
    const searchEl = document.getElementById('searchInput');
    const query = searchEl ? (searchEl.value || '').toLowerCase().trim() : '';

    if (!query) {
        filteredStudents = [...students];
    } else {
        filteredStudents = students.filter(student => {
            const fullName = `${student.firstName || ''} ${student.lastName || ''}`.toLowerCase();
            const email = (student.email || '').toLowerCase();
            return fullName.includes(query) || email.includes(query);
        });
    }

    ui.renderStudentsTable(filteredStudents);
}

/**
 * Prepare form for editing
 */
function editStudent(id) {
    const student = students.find(s => s.id === id);
    if (student) {
        form.setFormData(student);
        currentEditingId = id;
        ui.showSection('registerForm');
    } else {
        showToast('Student not found', 'error');
    }
}

/**
 * View student detail
 */
function viewStudentDetail(id) {
    const student = students.find(s => s.id === id);
    if (student) {
        const details = `
            <strong>Student ID:</strong> #${student.id}<br>
            <strong>Name:</strong> ${student.firstName} ${student.lastName}<br>
            <strong>Email:</strong> ${student.email}<br>
            <strong>Phone:</strong> ${student.phoneNumber}<br>
            <strong>DOB:</strong> ${student.dateOfBirth || 'N/A'}<br>
            <strong>Registered:</strong> ${formatDateTime(student.enrollmentDate) || 'N/A'}
        `;
        showToast(details, 'info');
    }
}

/**
 * Delete Confirmation
 */
function openDeleteModal(id) {
    const student = students.find(s => s.id === id);
    if (!student) return;
    currentDeleteId = id;
    const msgEl = document.getElementById('deleteMessage');
    if (msgEl) msgEl.textContent = `Are you sure you want to delete ${student.firstName || ''} ${student.lastName || ''}?`;
    const modalEl = document.getElementById('deleteModal');
    if (modalEl) modalEl.classList.remove('hidden');
}

function closeDeleteModal() {
    const modalEl = document.getElementById('deleteModal');
    if (modalEl) modalEl.classList.add('hidden');
    currentDeleteId = null;
}

async function confirmDelete() {
    if (currentDeleteId) {
        try {
            showToast('Deleting student...', 'info');
            await api.deleteStudent(currentDeleteId);
            showToast('✓ Student deleted successfully!', 'success');
            closeDeleteModal();
            refreshStudents();
        } catch (error) {
            showToast('Failed to delete student', 'error');
        }
    }
}

// Global error handlers
window.addEventListener('error', (event) => {
    log(`Global error: ${event.error}`);
    showToast('An unexpected error occurred.', 'error');
});

window.addEventListener('unhandledrejection', (event) => {
    log(`Unhandled rejection: ${event.reason}`);
    showToast('An unexpected error occurred.', 'error');
});

// Export ui module and all functions
export { ui };
export { resetForm };
export { handleFormSubmit };
export { confirmDelete };
export { closeDeleteModal };
export { handleSearch };

// Assign functions to window for HTML onclick handlers
window.showSection = ui.showSection;
window.resetForm = resetForm;
window.confirmDelete = confirmDelete;
window.closeDeleteModal = closeDeleteModal;
window.handleSearch = handleSearch;
window.handleFormSubmit = handleFormSubmit;
window.initApp = initApp;

