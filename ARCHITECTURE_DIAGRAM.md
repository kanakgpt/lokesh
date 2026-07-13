# 🏗️ Application Architecture & Data Flow

## System Overview

```
┌─────────────────────────────────────────────────────────────────┐
│                    STUDENT MANAGEMENT SYSTEM                     │
│                     (Architecture Diagram)                        │
└─────────────────────────────────────────────────────────────────┘

        ┌──────────────────────────────────────────────────┐
        │        WEB BROWSER (Client-Side)                 │
        │  ┌──────────────────────────────────────────┐    │
        │  │  HTML/CSS/JavaScript                     │    │
        │  │  ┌────────────────────────────────────┐  │    │
        │  │  │ index.html                         │  │    │
        │  │  │ • Placeholder Functions            │  │    │
        │  │  │ • Module Loader                    │  │    │
        │  │  │ • Component Injector               │  │    │
        │  │  └────────────────────────────────────┘  │    │
        │  │                                          │    │
        │  │  ┌────────────────────────────────────┐  │    │
        │  │  │ Components (Dynamic)               │  │    │
        │  │  │ • navbar.html                      │  │    │
        │  │  │ • register-form.html               │  │    │
        │  │  │ • student-list.html                │  │    │
        │  │  └────────────────────────────────────┘  │    │
        │  │                                          │    │
        │  │  ┌────────────────────────────────────┐  │    │
        │  │  │ JavaScript Modules (ES6)           │  │    │
        │  │  │ ┌──────────────────────────────┐   │  │    │
        │  │  │ │ main.js                      │   │  │    │
        │  │  │ │ • Application Logic          │   │  │    │
        │  │  │ │ • Event Handlers             │   │  │    │
        │  │  │ │ • State Management           │   │  │    │
        │  │  │ └──────────────────────────────┘   │  │    │
        │  │  │ ┌──────────────────────────────┐   │  │    │
        │  │  │ │ api.js                       │   │  │    │
        │  │  │ │ • API Calls                  │   │  │    │
        │  │  │ │ • HTTP Methods               │   │  │    │
        │  │  │ │ • Error Handling             │   │  │    │
        │  │  │ └──────────────────────────────┘   │  │    │
        │  │  │ ┌──────────────────────────────┐   │  │    │
        │  │  │ │ ui.js                        │   │  │    │
        │  │  │ │ • Rendering Functions        │   │  │    │
        │  │  │ │ • Section Navigation         │   │  │    │
        │  │  │ │ • DOM Manipulation           │   │  │    │
        │  │  │ └──────────────────────────────┘   │  │    │
        │  │  │ ┌──────────────────────────────┐   │  │    │
        │  │  │ │ form.js                      │   │  │    │
        │  │  │ │ • Form Validation            │   │  │    │
        │  │  │ │ • Data Collection            │   │  │    │
        │  │  │ │ • Error Display              │   │  │    │
        │  │  │ └──────────────────────────────┘   │  │    │
        │  │  │ ┌──────────────────────────────┐   │  │    │
        │  │  │ │ utils.js                     │   │  │    │
        │  │  │ │ • Date Formatting            │   │  │    │
        │  │  │ │ • Notifications              │   │  │    │
        │  │  │ │ • Logging                    │   │  │    │
        │  │  │ └──────────────────────────────┘   │  │    │
        │  │  └────────────────────────────────────┘  │    │
        │  │                                          │    │
        │  │  ┌────────────────────────────────────┐  │    │
        │  │  │ CSS Styles (Modular)               │  │    │
        │  │  │ • base.css, navbar.css, etc.       │  │    │
        │  │  │ • Responsive Design                │  │    │
        │  │  └────────────────────────────────────┘  │    │
        │  └──────────────────────────────────────────┘    │
        └──────────────────────────────────────────────────┘
                              ↕
                    [HTTP Request/Response]
                              ↕
        ┌──────────────────────────────────────────────────┐
        │      SPRING BOOT SERVER (Back-End)               │
        │  ┌──────────────────────────────────────────┐    │
        │  │ StudentManagementApplication             │    │
        │  │  ┌────────────────────────────────────┐  │    │
        │  │  │ CORS Configuration                │  │    │
        │  │  │ • Allows all origins              │  │    │
        │  │  │ • /api/** endpoints               │  │    │
        │  │  └────────────────────────────────────┘  │    │
        │  │  ┌────────────────────────────────────┐  │    │
        │  │  │ Controllers                        │  │    │
        │  │  │ • Handle HTTP Requests            │  │    │
        │  │  │ • Route to Services               │  │    │
        │  │  └────────────────────────────────────┘  │    │
        │  │  ┌────────────────────────────────────┐  │    │
        │  │  │ Services                           │  │    │
        │  │  │ • Business Logic                  │  │    │
        │  │  │ • Validation                      │  │    │
        │  │  │ • CRUD Operations                 │  │    │
        │  │  └────────────────────────────────────┘  │    │
        │  │  ┌────────────────────────────────────┐  │    │
        │  │  │ Repository (JPA)                   │  │    │
        │  │  │ • Database Access                 │  │    │
        │  │  │ • Query Building                  │  │    │
        │  │  └────────────────────────────────────┘  │    │
        │  └──────────────────────────────────────────┘    │
        └──────────────────────────────────────────────────┘
                              ↕
                   [SQL Query/Response]
                              ↕
        ┌──────────────────────────────────────────────────┐
        │     POSTGRESQL DATABASE (Persistence)            │
        │  ┌──────────────────────────────────────────┐    │
        │  │ Student Table                            │    │
        │  │ ┌────────────────────────────────────┐   │    │
        │  │ │ id             (PK)                │   │    │
        │  │ │ firstName      (VARCHAR)           │   │    │
        │  │ │ lastName       (VARCHAR)           │   │    │
        │  │ │ email          (VARCHAR, UNIQUE)   │   │    │
        │  │ │ phoneNumber    (VARCHAR)           │   │    │
        │  │ │ dateOfBirth    (DATE)              │   │    │
        │  │ │ enrollmentDate (TIMESTAMP)         │   │    │
        │  │ └────────────────────────────────────┘   │    │
        │  │ • 4 Records Currently                    │    │
        │  │ • Auto-increment ID                      │    │
        │  │ • Indexed for Performance                │    │
        │  └──────────────────────────────────────────┘    │
        └──────────────────────────────────────────────────┘
```

---

## Module Loading Sequence

```
1. Browser loads index.html
   ↓
2. Define placeholder functions on window object
   ↓
3. Set window._mainModuleReady = false
   ↓
4. Script starts module initialization
   ↓
5. Load HTML components dynamically:
   • navbar.html
   • register-form.html  
   • student-list.html
   ↓
6. Import main.js module
   ↓
7. Extract real function implementations
   ↓
8. Replace placeholder functions with real implementations
   ↓
9. Set window._mainModuleReady = true
   ↓
10. Call initApp()
    ↓
11. Application Ready! ✓
```

---

## API Data Flow

```
┌─────────────────┐
│   HTML Form     │
│ (register-form) │
└────────┬────────┘
         ↓
    [User Input]
         ↓
┌─────────────────┐
│ form.js         │
│ Validation      │
└────────┬────────┘
         ↓ ✓ Valid
┌──────────────────────┐
│ main.js              │
│ handleFormSubmit()   │
└────────┬─────────────┘
         ↓
┌──────────────────────┐
│ api.js               │
│ POST /api/v1/students
└────────┬─────────────┘
         ↓
    [HTTP Request]
         ↓
┌─────────────────────────────────┐
│ Spring Boot Controller           │
│ @PostMapping("/")               │
└────────┬────────────────────────┘
         ↓
┌─────────────────────────────────┐
│ StudentService                  │
│ createStudent()                 │
│ • Validate Input                │
│ • Check Duplicate Email         │
│ • Save to Database              │
└────────┬────────────────────────┘
         ↓
┌─────────────────────────────────┐
│ StudentRepository               │
│ save(student)                   │
└────────┬────────────────────────┘
         ↓
┌──────────────────────────┐
│ PostgreSQL Database      │
│ INSERT INTO students ... │
└────────┬─────────────────┘
         ↓
    [HTTP Response 201]
         ↓
┌──────────────────┐
│ api.js           │
│ Parse Response   │
└────────┬─────────┘
         ↓
┌──────────────────────┐
│ main.js              │
│ refreshStudents()    │
│ showToast("Success") │
└────────┬─────────────┘
         ↓
┌──────────────────┐
│ ui.js            │
│ renderTable()    │
└────────┬─────────┘
         ↓
    ✓ [UI Updated]
```

---

## Component Interaction

```
index.html (Entry Point)
    ├─ Placeholder Functions
    └─ Module Loader
        ├─ loadComponent() → navbar.html
        ├─ loadComponent() → register-form.html
        ├─ loadComponent() → student-list.html
        └─ import('./js/main.js')
            ├─ Imports:
            │   ├─ api.js
            │   ├─ ui.js
            │   ├─ form.js
            │   └─ utils.js
            └─ Exports:
                ├─ initApp()
                ├─ showSection()
                ├─ resetForm()
                ├─ handleFormSubmit()
                ├─ confirmDelete()
                ├─ closeDeleteModal()
                └─ handleSearch()
```

---

## Event Flow Diagram

```
User Action              Handler                Result
───────────────────────────────────────────────────────

Click Button         → onclick="showSection()"
                     → ui.showSection()
                     → Toggle Section Visibility

Submit Form          → onsubmit="handleFormSubmit(event)"
                     → form.validateForm()
                     → api.createStudent()
                     → ui.renderStudentsTable()
                     → Show Toast Notification

Type in Search       → onkeyup="handleSearch()"
                     → Filter students array
                     → ui.renderStudentsTable()
                     → Show filtered results

Click Edit Button    → data-id="1" → action-edit
                     → main.editStudent(id)
                     → form.setFormData()
                     → ui.showSection('registerForm')

Click Delete Button  → data-id="1" → action-delete
                     → main.openDeleteModal()
                     → Show Modal Confirmation

Click Confirm Delete → onclick="confirmDelete()"
                     → api.deleteStudent(id)
                     → main.closeDeleteModal()
                     → main.refreshStudents()
                     → ui.renderStudentsTable()
```

---

## Error Handling Flow

```
Error Occurs
    ↓
Try-Catch Block
    ↓
Error Type?
    ├─ Validation Error
    │   ├─ form.showError()
    │   └─ Toast: "Please fix validation errors"
    │
    ├─ API Error
    │   ├─ Response.json()
    │   ├─ Extract error message
    │   └─ Toast: "API Error Message"
    │
    ├─ Network Error
    │   └─ Toast: "Failed to connect to server"
    │
    └─ Unexpected Error
        └─ Toast: "An unexpected error occurred"

Console
    ↓
log("Error: " + message)
```

---

## State Management

```
Application State (in main.js)
├─ students: [] 
│   └─ Holds all student records from API
├─ filteredStudents: []
│   └─ Holds search/filter results
├─ currentEditingId: null
│   └─ ID of student being edited
└─ currentDeleteId: null
    └─ ID of student to be deleted

State Updates
├─ refreshStudents()
│   ├─ API call
│   ├─ Update students array
│   ├─ Reset filteredStudents
│   └─ Re-render table
│
├─ handleSearch()
│   ├─ Filter students array
│   └─ Update filteredStudents
│
├─ editStudent(id)
│   ├─ Find student in array
│   ├─ Set currentEditingId
│   └─ Populate form
│
└─ handleFormSubmit()
    ├─ Create or Update
    └─ Refresh students
```

---

## Form Validation Pipeline

```
User Input
    ↓
validateForm()
    ├─ Check firstName
    │   ├─ Not empty?
    │   ├─ Min 2 chars?
    │   └─ showError() if invalid
    │
    ├─ Check lastName
    │   ├─ Not empty?
    │   ├─ Min 2 chars?
    │   └─ showError() if invalid
    │
    ├─ Check email
    │   ├─ Not empty?
    │   ├─ Valid format? (regex)
    │   └─ showError() if invalid
    │
    ├─ Check phoneNumber
    │   ├─ Not empty?
    │   ├─ Min 10 digits?
    │   └─ showError() if invalid
    │
    └─ Return true/false

If Valid ✓
    └─ handleFormSubmit() → API call

If Invalid ✗
    └─ showToast("Please fix validation errors")
```

---

## Performance Optimizations

```
Frontend
├─ CSS: Modular, minified in production
├─ JS: ES6 modules, tree-shaking capable
├─ Images: Optimized emoji/SVG
├─ Caching: Browser cache for static files
└─ Lazy Loading: Components loaded on demand

Backend
├─ Database: Indexed columns for fast queries
├─ Connection Pooling: HikariCP for performance
├─ Caching: Application-level caching possible
└─ JSON: Efficient serialization

Network
├─ Compression: GZIP enabled
├─ Batch Operations: Single API calls where possible
├─ Error Retry: Configurable retry logic
└─ Timeout: Proper request timeouts set
```

---

## Security Architecture

```
Frontend Security
├─ Input Validation: Client-side validation
├─ XSS Prevention: No innerHTML for user data
├─ CSRF: Token in forms (if needed)
└─ Storage: No sensitive data in localStorage

Backend Security
├─ Input Validation: Server-side validation
├─ SQL Injection: JPA parameterized queries
├─ Authentication: Spring Security (if added)
└─ Authorization: Role-based access control

Data Security
├─ Encryption: HTTPS in production
├─ Database: Connection over secure channel
└─ Credentials: Environment variables for secrets
```

---

This architecture ensures:
✅ Clean separation of concerns
✅ Scalability
✅ Maintainability  
✅ Performance
✅ Security
✅ User Experience


