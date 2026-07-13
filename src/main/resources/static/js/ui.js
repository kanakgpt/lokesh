/**
 * UI Rendering and Section Management
 */
import { formatDate } from './utils.js';

/**
 * Show specific section and hide others
 * @param {string} sectionId - ID of section to show
 */
export function showSection(sectionId) {
    // Hide all sections (guard for missing elements)
    const homeEl = document.getElementById('home');
    const registerEl = document.getElementById('registerForm');
    const listEl = document.getElementById('studentList');

    if (homeEl) homeEl.classList.add('hidden');
    if (registerEl) registerEl.classList.add('hidden');
    if (listEl) listEl.classList.add('hidden');

    // Show requested section (only if element exists)
    if (sectionId === 'home' && homeEl) {
        homeEl.classList.remove('hidden');
        updateNavLink('home');
    } else if (sectionId === 'registerForm' && registerEl) {
        registerEl.classList.remove('hidden');
        updateNavLink('register');
    } else if (sectionId === 'studentList' && listEl) {
        listEl.classList.remove('hidden');
        updateNavLink('students');
    }

    // Scroll to top
    try { window.scrollTo(0, 0); } catch (e) { /* ignore */ }
}

/**
 * Update active nav link
 * @param {string} activeLink - The link to set as active
 */
function updateNavLink(activeLink) {
    const navLinks = document.querySelectorAll('.nav-link');
    if (!navLinks || navLinks.length === 0) return;

    navLinks.forEach(link => link.classList.remove('active'));

    const linkMap = {
        'home': 0,
        'register': 1,
        'students': 2
    };

    const idx = linkMap[activeLink];
    if (idx !== undefined && navLinks[idx]) navLinks[idx].classList.add('active');
}

/**
 * Render students table
 * @param {Array} studentsList - List of students to render
 */
export function renderStudentsTable(studentsList) {
    const tableBody = document.getElementById('studentTableBody');

    if (!tableBody) return; // nothing to render into

    if (!studentsList || studentsList.length === 0) {
        showNoStudentsMessage();
        tableBody.innerHTML = '';
        return;
    }

    hideNoStudentsMessage();

    tableBody.innerHTML = studentsList.map(student => `
        <tr>
            <td>#${student.id}</td>
            <td>
                <strong>${student.firstName} ${student.lastName}</strong>
            </td>
            <td ${student.email ? '' : 'style="color: #999;"'}>
                ${student.email || 'N/A'}
            </td>
            <td>${student.phoneNumber}</td>
            <td>${formatDate(student.dateOfBirth) || 'N/A'}</td>
            <td>
                <div class="action-buttons">
                    <button class="btn btn-warning btn-sm action-edit" data-id="${student.id}">
                        ✎ Edit
                    </button>
                    <button class="btn btn-danger btn-sm action-delete" data-id="${student.id}">
                        🗑 Delete
                    </button>
                    <button class="btn btn-primary btn-sm action-view" data-id="${student.id}">
                        👁 View
                    </button>
                </div>
            </td>
        </tr>
    `).join('');
}

/**
 * Update statistics
 * @param {number} count - Total count
 */
export function updateStatistics(count) {
    const totalStudents = document.getElementById('totalStudents');
    const activeStudents = document.getElementById('activeStudents');

    if (totalStudents) totalStudents.textContent = count;
    if (activeStudents) activeStudents.textContent = count;
}

/**
 * Show no students message
 */
export function showNoStudentsMessage() {
    const noMsg = document.getElementById('noStudentsMessage');
    if (noMsg) noMsg.classList.remove('hidden');
    const tableContainer = document.querySelector('.table-responsive');
    if (tableContainer) tableContainer.style.display = 'none';
}

/**
 * Hide no students message
 */
export function hideNoStudentsMessage() {
    const noMsg = document.getElementById('noStudentsMessage');
    if (noMsg) noMsg.classList.add('hidden');
    const tableContainer = document.querySelector('.table-responsive');
    if (tableContainer) tableContainer.style.display = 'block';
}
