# API Documentation - Student Management System

## Table of Contents
1. [Overview](#overview)
2. [Base URL](#base-url)
3. [Authentication](#authentication)
4. [Request/Response Format](#requestresponse-format)
5. [Endpoints](#endpoints)
6. [Status Codes](#status-codes)
7. [Error Responses](#error-responses)

---

## Overview

The Student Management System API provides RESTful endpoints for managing student records. All endpoints return JSON responses.

---

## Base URL

```
http://localhost:8080/api/v1/students
```

---

## Authentication

Currently, there is no authentication required. Future versions may add JWT token-based authentication.

---

## Request/Response Format

### Request Headers

```
Content-Type: application/json
Accept: application/json
```

### Response Format

All responses are in JSON format. Successful responses include the requested data, while error responses follow a standardized error format.

---

## Endpoints

### 1. Create Student

**Endpoint:** `POST /api/v1/students`

**Description:** Create a new student record.

**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890",
  "dateOfBirth": "2000-01-15"
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890",
  "dateOfBirth": "2000-01-15"
}
```

**Validation Rules:**
- `firstName`: Required, non-empty string
- `lastName`: Required, non-empty string
- `email`: Required, valid email format, must be unique
- `phoneNumber`: Required, non-empty string
- `dateOfBirth`: Optional

**Error Responses:**
- `400 Bad Request`: Invalid input or email already exists
- `500 Internal Server Error`: Server error

---

### 2. Get All Students

**Endpoint:** `GET /api/v1/students`

**Description:** Retrieve all students in the system.

**Query Parameters:** None

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "1234567890",
    "dateOfBirth": "2000-01-15"
  },
  {
    "id": 2,
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane.smith@example.com",
    "phoneNumber": "9876543210",
    "dateOfBirth": "2001-05-20"
  }
]
```

**Error Responses:**
- `500 Internal Server Error`: Server error

---

### 3. Get Student by ID

**Endpoint:** `GET /api/v1/students/{id}`

**Description:** Retrieve a specific student by ID.

**Path Parameters:**
- `id` (Long): Student ID (required)

**Example Request:**
```
GET /api/v1/students/1
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890",
  "dateOfBirth": "2000-01-15"
}
```

**Error Responses:**
- `404 Not Found`: Student with given ID not found
- `500 Internal Server Error`: Server error

---

### 4. Get Student by Email

**Endpoint:** `GET /api/v1/students/email/{email}`

**Description:** Retrieve a specific student by email address.

**Path Parameters:**
- `email` (String): Student email (required)

**Example Request:**
```
GET /api/v1/students/email/john.doe@example.com
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890",
  "dateOfBirth": "2000-01-15"
}
```

**Error Responses:**
- `404 Not Found`: Student with given email not found
- `500 Internal Server Error`: Server error

---

### 5. Update Student

**Endpoint:** `PUT /api/v1/students/{id}`

**Description:** Update an existing student record by ID.

**Path Parameters:**
- `id` (Long): Student ID (required)

**Request Body:**
```json
{
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane.doe@example.com",
  "phoneNumber": "9876543210",
  "dateOfBirth": "2000-01-15"
}
```

**Example Request:**
```
PUT /api/v1/students/1
Content-Type: application/json

{
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane.doe@example.com",
  "phoneNumber": "9876543210",
  "dateOfBirth": "2000-01-15"
}
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane.doe@example.com",
  "phoneNumber": "9876543210",
  "dateOfBirth": "2000-01-15"
}
```

**Validation Rules:**
- Same as Create Student
- Email must be either the same or unique

**Error Responses:**
- `400 Bad Request`: Invalid input or email already exists
- `404 Not Found`: Student with given ID not found
- `500 Internal Server Error`: Server error

---

### 6. Delete Student

**Endpoint:** `DELETE /api/v1/students/{id}`

**Description:** Delete a student record by ID.

**Path Parameters:**
- `id` (Long): Student ID (required)

**Example Request:**
```
DELETE /api/v1/students/1
```

**Response:** `204 No Content`

**Error Responses:**
- `404 Not Found`: Student with given ID not found
- `500 Internal Server Error`: Server error

---

### 7. Search by First Name

**Endpoint:** `GET /api/v1/students/search/firstName`

**Description:** Search students by first name (case-insensitive, partial match).

**Query Parameters:**
- `firstName` (String): First name to search (required)

**Example Request:**
```
GET /api/v1/students/search/firstName?firstName=John
```

**Response:** `200 OK`
```json
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

**Notes:**
- Search is case-insensitive
- Partial matches are supported (e.g., "Jo" will match "John", "Jonathan")
- Returns empty array if no matches found

**Error Responses:**
- `500 Internal Server Error`: Server error

---

### 8. Search by Last Name

**Endpoint:** `GET /api/v1/students/search/lastName`

**Description:** Search students by last name (case-insensitive, partial match).

**Query Parameters:**
- `lastName` (String): Last name to search (required)

**Example Request:**
```
GET /api/v1/students/search/lastName?lastName=Doe
```

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "1234567890",
    "dateOfBirth": "2000-01-15"
  },
  {
    "id": 2,
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane.doe@example.com",
    "phoneNumber": "9876543210",
    "dateOfBirth": "2000-01-15"
  }
]
```

**Notes:**
- Search is case-insensitive
- Partial matches are supported
- Returns empty array if no matches found

**Error Responses:**
- `500 Internal Server Error`: Server error

---

## Status Codes

The API uses standard HTTP status codes:

| Code | Status | Description |
|------|--------|-------------|
| 200 | OK | Request successful, data returned |
| 201 | Created | Resource successfully created |
| 204 | No Content | Request successful, no data returned |
| 400 | Bad Request | Invalid request parameters or body |
| 404 | Not Found | Requested resource not found |
| 500 | Internal Server Error | Server-side error occurred |

---

## Error Responses

### Error Response Format

All error responses follow this format:

```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Email already exists in the system"
}
```

### Error Types

#### 1. Student Not Found (404)
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Student not found with ID: 999"
}
```

#### 2. Validation Error (400)
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Validation Error",
  "message": "{firstName=First name is required, email=Email should be valid}"
}
```

#### 3. Email Already Exists (400)
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Email already exists in the system"
}
```

#### 4. Internal Server Error (500)
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "An unexpected error occurred. Please contact support."
}
```

---

## Examples Using cURL

### Create Student
```bash
curl -X POST http://localhost:8080/api/v1/students \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "1234567890",
    "dateOfBirth": "2000-01-15"
  }'
```

### Get All Students
```bash
curl -X GET http://localhost:8080/api/v1/students
```

### Get Student by ID
```bash
curl -X GET http://localhost:8080/api/v1/students/1
```

### Update Student
```bash
curl -X PUT http://localhost:8080/api/v1/students/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane.doe@example.com",
    "phoneNumber": "9876543210",
    "dateOfBirth": "2000-01-15"
  }'
```

### Delete Student
```bash
curl -X DELETE http://localhost:8080/api/v1/students/1
```

### Search by First Name
```bash
curl -X GET "http://localhost:8080/api/v1/students/search/firstName?firstName=John"
```

### Search by Last Name
```bash
curl -X GET "http://localhost:8080/api/v1/students/search/lastName?lastName=Doe"
```

---

## Examples Using Postman

1. Import the provided requests into Postman
2. Update the `base_url` variable to `http://localhost:8080`
3. Use the collection to test all endpoints
4. Results are automatically formatted and displayed

---

**For more information, see the main README.md file.**

