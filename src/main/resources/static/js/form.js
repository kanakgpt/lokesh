/**
 * Form Handling and Validation
 */

/**
 * Get form data from the student form
 * @returns {Object} Form data object
 */
export function getFormData() {
    // Defensive: return safe defaults if elements are missing
    const firstNameEl = document.getElementById('firstName');
    const lastNameEl = document.getElementById('lastName');
    const emailEl = document.getElementById('email');
    const phoneEl = document.getElementById('phoneNumber');
    const dobEl = document.getElementById('dateOfBirth');

    return {
        firstName: firstNameEl ? firstNameEl.value.trim() : '',
        lastName: lastNameEl ? lastNameEl.value.trim() : '',
        email: emailEl ? emailEl.value.trim() : '',
        phoneNumber: phoneEl ? phoneEl.value.trim() : '',
        dateOfBirth: dobEl ? (dobEl.value || null) : null
    };
}

/**
 * Set form data for editing
 * @param {Object} student - Student object
 */
export function setFormData(student) {
    const idEl = document.getElementById('studentId');
    const firstEl = document.getElementById('firstName');
    const lastEl = document.getElementById('lastName');
    const emailEl = document.getElementById('email');
    const phoneEl = document.getElementById('phoneNumber');
    const dobEl = document.getElementById('dateOfBirth');
    const titleEl = document.getElementById('formTitle');
    const submitTextEl = document.getElementById('submitBtnText');

    if (idEl) idEl.value = student.id || '';
    if (firstEl) firstEl.value = student.firstName || '';
    if (lastEl) lastEl.value = student.lastName || '';
    if (emailEl) emailEl.value = student.email || '';
    if (phoneEl) phoneEl.value = student.phoneNumber || '';
    if (dobEl) dobEl.value = student.dateOfBirth || '';

    if (titleEl) titleEl.textContent = 'Edit Student Information';
    if (submitTextEl) submitTextEl.textContent = 'Update Student';
}

/**
 * Reset form to initial state
 */
export function resetFormFields() {
    const studentForm = document.getElementById('studentForm');
    if (studentForm) studentForm.reset();
    const idEl = document.getElementById('studentId');
    if (idEl) idEl.value = '';
    const titleEl = document.getElementById('formTitle');
    if (titleEl) titleEl.textContent = 'Register New Student';
    const submitTextEl = document.getElementById('submitBtnText');
    if (submitTextEl) submitTextEl.textContent = 'Register Student';
    clearAllErrors();
}

/**
 * Validate entire form
 * @returns {boolean} True if valid, false otherwise
 */
export function validateForm() {
    // If form elements are missing, skip validation to avoid blocking scripts
    const studentForm = document.getElementById('studentForm');
    if (!studentForm) return true;

    let isValid = true;

    const firstNameEl = document.getElementById('firstName');
    const lastNameEl = document.getElementById('lastName');
    const emailEl = document.getElementById('email');
    const phoneEl = document.getElementById('phoneNumber');

    const firstName = firstNameEl ? firstNameEl.value.trim() : '';
    const lastName = lastNameEl ? lastNameEl.value.trim() : '';
    const email = emailEl ? emailEl.value.trim() : '';
    const phoneNumber = phoneEl ? phoneEl.value.trim() : '';

    // Validate First Name
    if (!firstName) {
        showError('firstNameError', 'First name is required');
        isValid = false;
    } else if (firstName.length < 2) {
        showError('firstNameError', 'First name must be at least 2 characters');
        isValid = false;
    }

    // Validate Last Name
    if (!lastName) {
        showError('lastNameError', 'Last name is required');
        isValid = false;
    } else if (lastName.length < 2) {
        showError('lastNameError', 'Last name must be at least 2 characters');
        isValid = false;
    }

    // Validate Email
    if (!email) {
        showError('emailError', 'Email is required');
        isValid = false;
    } else if (!isValidEmail(email)) {
        showError('emailError', 'Please enter a valid email address');
        isValid = false;
    }

    // Validate Phone Number
    if (!phoneNumber) {
        showError('phoneNumberError', 'Phone number is required');
        isValid = false;
    } else if (phoneNumber.length < 10) {
        showError('phoneNumberError', 'Phone number must be at least 10 digits');
        isValid = false;
    }

    return isValid;
}

/**
 * Validate email format
 * @param {string} email - Email address
 * @returns {boolean} True if valid
 */
function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

/**
 * Show error message for field
 * @param {string} errorId - Error element ID
 * @param {string} message - Error message
 */
function showError(errorId, message) {
    const errorElement = document.getElementById(errorId);
    if (errorElement) {
        errorElement.textContent = message;
    }
}

/**
 * Clear all form errors
 */
export function clearAllErrors() {
    document.querySelectorAll('.error-message').forEach(error => {
        error.textContent = '';
    });
}
