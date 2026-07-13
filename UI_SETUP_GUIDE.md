# UI Setup & Customization Guide

## Student Management System - Frontend Configuration

This guide explains how to set up, configure, and customize the UI components.

---

## Table of Contents

1. [File Structure](#file-structure)
2. [Configuration](#configuration)
3. [Customization](#customization)
4. [Deployment](#deployment)
5. [Advanced Features](#advanced-features)

---

## File Structure

### Static Files Location

```
src/main/resources/static/
├── index.html        (Main HTML structure)
├── style.css        (All styling)
└── script.js        (All JavaScript functionality)
```

### How It Works

1. **Backend Serves Files**
   - Spring Boot serves static files from `src/main/resources/static/`
   - URL: `http://localhost:8080/`
   - Auto-reloads on application start

2. **File Loading Order**
   - Browser requests `http://localhost:8080/`
   - Spring Boot serves `index.html`
   - HTML loads `style.css` and `script.js`
   - JavaScript initializes on page load

---

## Configuration

### API Base URL

**File**: `script.js` (Line ~15)

```javascript
const API_BASE_URL = 'http://localhost:8080/api/v1/students';
```

**Change For:**
- Different server: Update the URL
- Different port: Update port number
- Production: Use production server URL

### Color Scheme

**File**: `style.css` (Lines ~13-31)

```css
:root {
    --primary-color: #0066cc;      /* Main blue */
    --secondary-color: #00cc99;    /* Teal accent */
    --danger-color: #ff4444;       /* Red for delete */
    --warning-color: #ffaa00;      /* Orange warning */
    --success-color: #00cc66;      /* Green success */
    --dark-color: #1a1a2e;         /* Dark background */
    --light-color: #f5f7fa;        /* Light background */
}
```

**To Change Colors:**
1. Edit the CSS variables
2. All colored elements update automatically
3. Example:
```css
--primary-color: #ff6600;  /* Change to orange */
```

### Typography

**Font**: Segoe UI, Tahoma, Geneva, Verdana (Line ~41)

**To Change:**
```css
body {
    font-family: 'Your Font Name', Arial, sans-serif;
}
```

### Animation Speed

**File**: `style.css`

```css
--transition: all 0.3s ease;  /* Change 0.3s to desired duration */
```

---

## Customization

### Modify Form Fields

**Add New Field:**

1. **HTML** (`index.html`)
```html
<div class="form-group">
    <label for="department">Department</label>
    <input 
        type="text" 
        id="department" 
        name="department"
        placeholder="Enter department"
    >
    <span class="error-message" id="departmentError"></span>
</div>
```

2. **JavaScript** (`script.js`)
```javascript
// In getFormData() function
function getFormData() {
    return {
        firstName: document.getElementById('firstName').value.trim(),
        // ... other fields ...
        department: document.getElementById('department').value.trim()
    };
}

// In setFormData() function
function setFormData(student) {
    // ... other fields ...
    document.getElementById('department').value = student.department || '';
}

// In validateForm() function
const department = document.getElementById('department').value.trim();
if (!department) {
    showError('departmentError', 'Department is required');
    isValid = false;
}
```

3. **CSS** (if needed)
```css
/* Add any custom styling for new field */
```

### Change Table Columns

**Modify renderStudentsTable()** in `script.js`

**Example: Add Department Column**

```javascript
function renderStudentsTable() {
    const tableBody = document.getElementById('studentTableBody');
    
    // ...
    
    tableBody.innerHTML = filteredStudents.map(student => `
        <tr>
            <td>#${student.id}</td>
            <td><strong>${student.firstName} ${student.lastName}</strong></td>
            <td>${student.email}</td>
            <td>${student.phoneNumber}</td>
            <td>${student.department}</td>  <!-- NEW COLUMN -->
            <td>${formatDate(student.dateOfBirth) || 'N/A'}</td>
            <td>
                <div class="action-buttons">
                    <!-- ... buttons ... -->
                </div>
            </td>
        </tr>
    `).join('');
}
```

### Update Table Headers

**In index.html**, find the table headers:

```html
<thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Department</th>  <!-- ADD THIS -->
        <th>Date of Birth</th>
        <th>Actions</th>
    </tr>
</thead>
```

### Change Toast Colors

**Add Custom Toast Type** in `script.js`:

```javascript
function showToast(message, type = 'info') {
    const toast = document.getElementById('toast');
    toast.className = 'toast';
    toast.classList.add(type);  // success, error, warning, info
    toast.innerHTML = message;
    toast.classList.add('show');
    
    setTimeout(() => {
        toast.classList.remove('show');
    }, 5000);
}
```

**Add CSS** in `style.css`:

```css
.toast.custom {
    background-color: #9933ff;  /* Custom purple */
}
```

**Usage:**
```javascript
showToast('Custom message', 'custom');
```

---

## Deployment

### Production Checklist

Before deploying to production:

1. **Update API URL**
   - Change `API_BASE_URL` to production server
   - Use HTTPS for security

2. **Enable CORS**
   - Ensure `CorsConfig.java` is configured
   - Restrict to production domain

3. **Minify CSS & JS**
   - Use minification tools
   - Reduce file size

4. **Update Configuration**
   - Change database configuration
   - Set proper logging levels
   - Configure security

5. **Test All Features**
   - Form submission
   - Data retrieval
   - Search functionality
   - Delete operations

### Deployment Steps

1. **Build the project**
```bash
mvn clean package
```

2. **Deploy JAR**
```bash
java -jar target/student-management-system-1.0.0.jar --server.port=80
```

3. **Access UI**
```
http://yourdomain.com/
```

### Docker Deployment (Optional)

Create `Dockerfile`:

```dockerfile
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/student-management-system-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
```

Build and run:

```bash
docker build -t student-mgmt .
docker run -p 8080:8080 student-mgmt
```

---

## Advanced Features

### Add Authentication

**Create login page:**

1. Create `login.html`
2. Add authentication JavaScript
3. Store JWT token in localStorage
4. Include token in API requests

**Example:**
```javascript
// Store token after login
localStorage.setItem('token', jwtToken);

// Use in API calls
const headers = {
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${localStorage.getItem('token')}`
};

const response = await fetch(API_BASE_URL, {
    method: 'POST',
    headers: headers,
    body: JSON.stringify(studentData)
});
```

### Add Pagination

**Implement Page Navigation:**

```javascript
const pageSize = 10;
let currentPage = 1;

function paginateStudents() {
    const start = (currentPage - 1) * pageSize;
    const end = start + pageSize;
    return filteredStudents.slice(start, end);
}

function renderPagination() {
    const totalPages = Math.ceil(filteredStudents.length / pageSize);
    let html = '<div class="pagination">';
    
    for (let i = 1; i <= totalPages; i++) {
        html += `<button onclick="goToPage(${i})">${i}</button>`;
    }
    
    html += '</div>';
    document.getElementById('pagination').innerHTML = html;
}
```

### Add Export to CSV

```javascript
function exportToCSV() {
    let csv = 'ID,First Name,Last Name,Email,Phone,DOB\n';
    
    students.forEach(student => {
        csv += `${student.id},${student.firstName},${student.lastName},${student.email},${student.phoneNumber},${student.dateOfBirth}\n`;
    });
    
    const blob = new Blob([csv], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'students.csv';
    a.click();
}
```

### Add Dark Mode

**Add Toggle Button:**
```html
<button onclick="toggleDarkMode()">🌙 Dark Mode</button>
```

**JavaScript:**
```javascript
function toggleDarkMode() {
    document.body.classList.toggle('dark-mode');
    localStorage.setItem('darkMode', 
        document.body.classList.contains('dark-mode'));
}

// On page load
if (localStorage.getItem('darkMode') === 'true') {
    document.body.classList.add('dark-mode');
}
```

**CSS:**
```css
body.dark-mode {
    background-color: #1a1a2e;
    color: #f5f7fa;
}

body.dark-mode .form-container {
    background-color: #2d2d44;
}
```

### Add Print Functionality

**CSS for Print:**
```css
@media print {
    .navbar, .footer, .search-bar, .form-section {
        display: none !important;
    }
}
```

**JavaScript:**
```javascript
function printStudentList() {
    window.print();
}
```

---

## Troubleshooting Customization

### Changes Not Taking Effect

1. **Clear Browser Cache**
   - Press `Ctrl+Shift+Delete`
   - Select "All time"
   - Clear cache

2. **Hard Refresh**
   - Press `Ctrl+F5` (or `Cmd+Shift+R` on Mac)

3. **Check Syntax**
   - Open console (F12)
   - Look for red error messages
   - Fix syntax errors

### API Not Responding

1. **Verify Backend Running**
   - Check terminal for Spring Boot messages
   - Navigate to `http://localhost:8080` in browser

2. **Check API URL**
   - Open browser console (F12)
   - Look for "API Call" logs
   - Verify URL is correct

3. **Enable CORS**
   - Ensure `CorsConfig.java` is deployed
   - Check application starts without errors

### Form Data Not Saving

1. **Check Field Names**
   - Must match between HTML id and API field names
   - Case-sensitive

2. **Verify Validation**
   - Check console for validation errors
   - Ensure all required fields filled

3. **Check Server Response**
   - F12 → Network tab
   - Look at API response
   - Check error message

---

## Best Practices

### Code Organization

1. **Keep files clean**
   - One file per concern
   - Proper indentation
   - Clear comments

2. **Use meaningful names**
   - Function: `getStudentById()`
   - Variable: `studentData`
   - ID: `studentTableBody`

3. **Comment complex logic**
   - Explain "why", not "what"
   - Keep comments updated

### Performance

1. **Minimize API calls**
   - Cache data when possible
   - Use search instead of fetching new data

2. **Optimize animations**
   - Keep CPU usage low
   - Use CSS transforms when possible

3. **Lazy load data**
   - Load on demand
   - Implement pagination for large lists

### Security

1. **Validate input**
   - Client-side and server-side
   - Sanitize user input

2. **Use HTTPS**
   - In production always
   - Protect data in transit

3. **Secure API calls**
   - Use authentication tokens
   - Never expose sensitive data in console

---

## Testing

### Manual Testing Checklist

- [ ] Form validation works
- [ ] Submit creates student
- [ ] List displays all students
- [ ] Search filters correctly
- [ ] Edit loads correct data
- [ ] Update saves changes
- [ ] Delete removes student
- [ ] Notifications appear
- [ ] Mobile responsive
- [ ] All links working

### Browser Testing

Test on:
- [ ] Chrome
- [ ] Firefox
- [ ] Safari
- [ ] Edge
- [ ] Mobile Safari
- [ ] Chrome Mobile

---

## Resources

### Documentation Files

- `UI_GUIDE.md` - User guide
- `API_DOCUMENTATION.md` - API reference
- `README.md` - General documentation

### Online Resources

- **MDN Web Docs**: https://developer.mozilla.org/
- **CSS-Tricks**: https://css-tricks.com/
- **JavaScript.info**: https://javascript.info/

### Tools

- **Visual Studio Code**: Code editor
- **Chrome DevTools**: Browser debugging (F12)
- **Postman**: API testing
- **CSS Generator**: https://www.cssoportal.com/

---

## Support

For help with customization:

1. Check existing code comments
2. Review online documentation
3. Test changes in developer tools first
4. Check browser console for errors
5. Review Git history for previous versions

---

**Happy Customizing! 🎨**

