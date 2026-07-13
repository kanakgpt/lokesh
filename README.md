# Student Management System - Spring Boot Application

## 📋 Project Overview

A comprehensive **Student Management System** built with **Spring Boot 3.x** using **Java 21** and **PostgreSQL**. This application follows **SOLID principles** and implements **Strategy Design Pattern** for a clean, maintainable, and scalable CRUD application.

---

## 🏗️ Architecture & Design Patterns

### Layered Architecture

The application follows a **4-layer architecture pattern**:

```
┌─────────────────────────────────────────┐
│   Controller Layer (REST API)           │  Handles HTTP requests/responses
├─────────────────────────────────────────┤
│   Service Layer (Business Logic)        │  Core business operations
├─────────────────────────────────────────┤
│   Repository Layer (Data Access)        │  Database operations
├─────────────────────────────────────────┤
│   Entity Layer (Domain Models)          │  Database entities
└─────────────────────────────────────────┘
```

### Design Patterns Implemented

1. **Repository Pattern**
   - Abstracts data access logic
   - `StudentRepository` extends `JpaRepository`
   - Location: `com.studentmgmt.repository`

2. **DTO Pattern**
   - Separates API contracts from internal entities
   - `StudentDTO` for request/response
   - Location: `com.studentmgmt.dto`

3. **Mapper Pattern**
   - Converts between DTOs and entities
   - `StudentMapper` utility class
   - Location: `com.studentmgmt.util`

4. **Strategy Pattern**
   - Multiple validation strategies in service layer
   - Email duplication validation
   - Location: `com.studentmgmt.service`

5. **Decorator Pattern**
   - Global exception handler wraps exceptions
   - `GlobalExceptionHandler` formats error responses
   - Location: `com.studentmgmt.exception`

### SOLID Principles

- **S** (Single Responsibility): Each class has one reason to change
- **O** (Open/Closed): Classes open for extension, closed for modification
- **L** (Liskov): Service implements contract properly
- **I** (Interface Segregation): Focused, single-purpose interfaces
- **D** (Dependency Inversion): Depends on abstractions, not concrete classes

---

## 📁 Project Structure

```
student-management-system/
├── src/
│   ├── main/
│   │   ├── java/com/studentmgmt/
│   │   │   ├── StudentManagementApplication.java (Main entry point)
│   │   │   ├── config/
│   │   │   │   └── DatabaseConfig.java (Database configuration)
│   │   │   ├── controller/
│   │   │   │   └── StudentController.java (REST API endpoints)
│   │   │   ├── service/
│   │   │   │   ├── StudentService.java (Interface)
│   │   │   │   └── StudentServiceImpl.java (Implementation)
│   │   │   ├── repository/
│   │   │   │   └── StudentRepository.java (Data access)
│   │   │   ├── entity/
│   │   │   │   └── Student.java (JPA Entity)
│   │   │   ├── dto/
│   │   │   │   └── StudentDTO.java (Data Transfer Object)
│   │   │   ├── exception/
│   │   │   │   ├── StudentNotFoundException.java (Custom exception)
│   │   │   │   ├── ErrorResponse.java (Error DTO)
│   │   │   │   └── GlobalExceptionHandler.java (Global exception handler)
│   │   │   └── util/
│   │   │       └── StudentMapper.java (DTO/Entity mapper)
│   │   └── resources/
│   │       ├── application.properties (Configuration)
│   │       └── logback-spring.xml (Logging configuration)
│   └── test/ (Test classes)
├── pom.xml (Maven configuration)
└── README.md (This file)
```

---

## 🛠️ Technologies Used

- **Java**: 21
- **Spring Boot**: 3.3.0
- **Spring Data JPA**: ORM framework
- **PostgreSQL**: 42.7.1
- **Maven**: Build tool
- **Lombok**: Reduce boilerplate code
- **SLF4J + Logback**: Logging framework
- **Jakarta Validation**: Input validation

---

## 🗄️ Database Setup

### Prerequisites
- PostgreSQL installed and running
- Access to PostgreSQL with root credentials

### Database Configuration

```
Host: localhost
Port: 5432
Database: student_management_db
Username: root
Password: root
```

### Create Database Manually (Optional)

```sql
-- Create database
CREATE DATABASE student_management_db;

-- Connect to database
\c student_management_db;

-- Create students table with indexes
CREATE TABLE IF NOT EXISTS students (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    date_of_birth VARCHAR(20),
    enrollment_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

-- Create indexes for performance
CREATE INDEX idx_email ON students(email);
CREATE INDEX idx_first_name ON students(first_name);
CREATE INDEX idx_last_name ON students(last_name);
```

**Note**: Spring Boot will automatically create the table if `spring.jpa.hibernate.ddl-auto=update` is set.

---

## 🚀 Getting Started

### Prerequisites
- JDK 21 or higher
- Maven 3.6.0 or higher
- PostgreSQL 12 or higher

### Installation Steps

1. **Clone/Extract the project**
   ```bash
   cd "D:\Real time project\student managment system"
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Ensure PostgreSQL is running**
   ```bash
   # On Windows, PostgreSQL should be running as a service
   # Verify connection:
   psql -U root -d postgres
   ```

4. **Create the database (if not auto-created)**
   ```sql
   CREATE DATABASE student_management_db;
   ```

5. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   **or**

   ```bash
   java -jar target/student-management-system-1.0.0.jar
   ```

6. **Verify the application started**
   - Check console logs for: `Student Management System Started Successfully`
   - Access API: `http://localhost:8080/api/v1/students`

---

## 🔌 REST API Endpoints

### Base URL
```
http://localhost:8080/api/v1/students
```

### Endpoints

#### 1. Create Student
```http
POST /api/v1/students
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890",
  "dateOfBirth": "2000-01-15"
}

Response: 201 Created
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890",
  "dateOfBirth": "2000-01-15"
}
```

#### 2. Get All Students
```http
GET /api/v1/students

Response: 200 OK
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "1234567890",
    "dateOfBirth": "2000-01-15"
  }
]
```

#### 3. Get Student by ID
```http
GET /api/v1/students/1

Response: 200 OK
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890",
  "dateOfBirth": "2000-01-15"
}
```

#### 4. Get Student by Email
```http
GET /api/v1/students/email/john.doe@example.com

Response: 200 OK
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890",
  "dateOfBirth": "2000-01-15"
}
```

#### 5. Update Student
```http
PUT /api/v1/students/1
Content-Type: application/json

{
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane.doe@example.com",
  "phoneNumber": "9876543210",
  "dateOfBirth": "2000-01-15"
}

Response: 200 OK
{
  "id": 1,
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane.doe@example.com",
  "phoneNumber": "9876543210",
  "dateOfBirth": "2000-01-15"
}
```

#### 6. Delete Student
```http
DELETE /api/v1/students/1

Response: 204 No Content
```

#### 7. Search by First Name
```http
GET /api/v1/students/search/firstName?firstName=John

Response: 200 OK
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "1234567890",
    "dateOfBirth": "2000-01-15"
  }
]
```

#### 8. Search by Last Name
```http
GET /api/v1/students/search/lastName?lastName=Doe

Response: 200 OK
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "1234567890",
    "dateOfBirth": "2000-01-15"
  }
]
```

---

## ⚠️ Error Handling

The API uses standardized error response format for all error scenarios.

### Error Response Format
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Student not found with ID: 999"
}
```

### Common HTTP Status Codes

| Status | Scenario | Example |
|--------|----------|---------|
| 200 | Success | GET, PUT operations |
| 201 | Created | POST successful |
| 204 | No Content | DELETE successful |
| 400 | Bad Request | Invalid input, email exists |
| 404 | Not Found | Student not found |
| 500 | Server Error | Unexpected exception |

### Exception Handling

- **StudentNotFoundException**: Thrown when student ID/email not found → 404
- **MethodArgumentNotValidException**: Validation errors → 400
- **IllegalArgumentException**: Email already exists → 400
- **Exception**: Unexpected errors → 500

---

## 📝 Logging

### Logging Configuration

Logging is configured in two files:

1. **application.properties** - Basic logging levels
```properties
logging.level.com.studentmgmt=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.file.name=logs/application.log
```

2. **logback-spring.xml** - Advanced logging configuration
- Console output with timestamp
- Rolling file appender with daily rolls
- Maximum file size: 10MB
- Log retention: 10 days

### Log Locations

- **Console**: All logs printed to console
- **File**: `logs/application.log`
- **Rotated logs**: `logs/spring-YYYY-MM-DD.index.log.gz`

### Sample Log Output

```
2024-01-15 10:30:00 - com.studentmgmt.controller.StudentController - Creating new student with email: john@example.com
2024-01-15 10:30:01 - com.studentmgmt.service.StudentServiceImpl - Student created successfully with ID: 1
2024-01-15 10:30:05 - com.studentmgmt.controller.StudentController - GET request received for all students
2024-01-15 10:30:05 - com.studentmgmt.service.StudentServiceImpl - Total students found: 1
```

---

## 🔐 Validation Rules

### Student Entity Validation

| Field | Rules | Message |
|-------|-------|---------|
| firstName | Required, not blank | "First name is required" |
| lastName | Required, not blank | "Last name is required" |
| email | Required, valid email format, unique | "Email should be valid", "Email already exists" |
| phoneNumber | Required, not blank | "Phone number is required" |
| dateOfBirth | Optional | - |

---

## 🧪 Testing

### Sample Test Cases

1. **Create Student**
   - Valid request → 201 Created
   - Duplicate email → 400 Bad Request
   - Missing required fields → 400 Bad Request

2. **Get Student**
   - Valid ID → 200 OK with student data
   - Invalid ID → 404 Not Found

3. **Update Student**
   - Valid ID and data → 200 OK
   - Invalid ID → 404 Not Found
   - Duplicate email → 400 Bad Request

4. **Delete Student**
   - Valid ID → 204 No Content
   - Invalid ID → 404 Not Found

---

## 🔧 Configuration Files

### application.properties

Key configurations:

```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/student_management_db
spring.datasource.username=root
spring.datasource.password=root

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### pom.xml

Maven dependencies included:
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- postgresql
- lombok
- spring-boot-starter-validation
- spring-boot-starter-test

---

## 📚 Code Examples

### Creating a Student

```java
// Request
POST /api/v1/students
{
  "firstName": "Alice",
  "lastName": "Smith",
  "email": "alice@example.com",
  "phoneNumber": "5551234567",
  "dateOfBirth": "2001-05-20"
}

// Response (201 Created)
{
  "id": 1,
  "firstName": "Alice",
  "lastName": "Smith",
  "email": "alice@example.com",
  "phoneNumber": "5551234567",
  "dateOfBirth": "2001-05-20"
}
```

### Searching Students

```java
// Search by first name
GET /api/v1/students/search/firstName?firstName=Alice

// Search by last name
GET /api/v1/students/search/lastName?lastName=Smith
```

---

## 🐛 Troubleshooting

### Common Issues

1. **Database connection failed**
   - Ensure PostgreSQL is running
   - Verify credentials (username: root, password: root)
   - Check if database exists: `CREATE DATABASE student_management_db;`

2. **Port 8080 already in use**
   - Change port in `application.properties`: `server.port=8081`
   - Or kill process using port 8080

3. **Email already exists error**
   - Use unique email addresses for new students
   - Update existing student with different email

4. **Table not created automatically**
   - Check `spring.jpa.hibernate.ddl-auto=update` is set
   - Run manual SQL to create table and indexes

---

## 📖 Additional Features Included

1. **Input Validation**: All fields validated before persistence
2. **Error Handling**: Global exception handler with standardized responses
3. **Logging**: Comprehensive logging at all layers
4. **Database Timestamps**: Automatic creation and update timestamps
5. **Email Uniqueness**: Prevents duplicate email registrations
6. **Search Functionality**: Case-insensitive search by name
7. **Transaction Management**: Proper transaction handling for data consistency

---

## 🤝 Contributing Guidelines

When extending this application:

1. Follow the existing package structure
2. Implement interfaces for new services
3. Use `@Component`, `@Service`, `@Repository` annotations
4. Add logging to important operations
5. Add proper Javadoc comments
6. Follow SOLID principles
7. Maintain the layered architecture

---

## 📄 License

This project is created for educational purposes.

---

## ✍️ Author Information

- **Project**: Student Management System
- **Version**: 1.0.0
- **Spring Boot**: 3.3.0
- **Java**: 21
- **Created**: 2024

---

## 📞 Support & Documentation

For more information:
- Spring Boot Documentation: https://spring.io/projects/spring-boot
- Spring Data JPA: https://spring.io/projects/spring-data-jpa
- PostgreSQL: https://www.postgresql.org/docs/
- Lombok: https://projectlombok.org/

---

**Happy Coding! 🚀**

