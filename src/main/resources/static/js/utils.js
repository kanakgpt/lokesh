/**
 * Format date for display
 * @param {string} dateString - Date string
 * @returns {string} Formatted date
 */
export function formatDate(dateString) {
    if (!dateString) return null;

    const options = { year: 'numeric', month: 'short', day: 'numeric' };
    try {
        return new Date(dateString).toLocaleDateString('en-US', options);
    } catch (e) {
        return dateString;
    }
}

/**
 * Format datetime for display
 * @param {string} dateTimeString - DateTime string
 * @returns {string} Formatted datetime
 */
export function formatDateTime(dateTimeString) {
    if (!dateTimeString) return null;

    const options = {
        year: 'numeric',
        month: 'short',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    };

    try {
        return new Date(dateTimeString).toLocaleDateString('en-US', options);
    } catch (e) {
        return dateTimeString;
    }
}

/**
 * Show toast notification
 * @param {string} message - Notification message
 * @param {string} type - Notification type (success, error, warning, info)
 */
export function showToast(message, type = 'info') {
    const toast = document.getElementById('toast');
    if (!toast) {
        // Fallback: log to console when toast element is not available
        console.log(`[TOAST ${type.toUpperCase()}] ${message}`);
        return;
    }

    // Clear previous toast
    toast.className = 'toast';
    if (type) toast.classList.add(type);
    toast.innerHTML = message;
    toast.classList.add('show');

    // Auto hide after 5 seconds
    setTimeout(() => {
        try { toast.classList.remove('show'); } catch (e) { /* ignore */ }
    }, 5000);
}

/**
 * Log message to console with timestamp
 * @param {string} message - Message to log
 */
export function log(message) {
    console.log(`[${new Date().toLocaleTimeString()}] ${message}`);
}
