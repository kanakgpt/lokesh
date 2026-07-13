# Setup and Installation Guide

## Student Management System - Complete Setup

This guide provides step-by-step instructions to set up and run the Student Management System application.

---

## Prerequisites

Before starting, ensure you have the following installed:

### 1. Java Development Kit (JDK)
- **Version**: Java 21 or higher
- **Download**: https://www.oracle.com/java/technologies/downloads/
- **Verify Installation**:
  ```bash
  java -version
  ```
  Should output: `java version "21.x.x"`

### 2. Maven
- **Version**: 3.6.0 or higher
- **Download**: https://maven.apache.org/download.cgi
- **Verify Installation**:
  ```bash
  mvn -version
  ```

### 3. PostgreSQL Database
- **Version**: 12 or higher
- **Download**: https://www.postgresql.org/download/
- **Verify Installation**: PgAdmin or Command Line
  ```bash
  psql --version
  ```

### 4. IDE (Optional but Recommended)
- IntelliJ IDEA (recommended)
- Eclipse
- Visual Studio Code with extensions

---

## Step 1: Configure PostgreSQL Database

### Option A: Using pgAdmin (GUI)

1. **Open PgAdmin**
   - Launch pgAdmin (usually accessible at `http://localhost:5050`)
   - Login with your PostgreSQL credentials

2. **Create New Database**
   - Right-click on "Databases" → "Create" → "Database"
   - Name: `student_management_db`
   - Click "Save"

3. **Verify Connection**
   - Navigate to Databases → student_management_db
   - You should see the database listed

### Option B: Using Command Line

1. **Open Command Prompt/PowerShell**
   ```bash
   psql -U root -h localhost
   ```

2. **Create Database**
   ```sql
   CREATE DATABASE student_management_db;
   ```

3. **Verify Creation**
   ```sql
   \l
   ```
   You should see `student_management_db` in the list

4. **Exit psql**
   ```sql
   \q
   ```

### Database Credentials (as configured)
```
Host: localhost
Port: 5432
Username: root
Password: root
Database: student_management_db
```

---

## Step 2: Download/Extract Project

1. **Navigate to Project Directory**
   ```bash
   cd "D:\Real time project\student managment system"
   ```

2. **Verify Project Structure**
   - Check for presence of:
     - `pom.xml`
     - `src/` folder
     - `README.md`

---

## Step 3: Build the Project

1. **Clean Previous Builds**
   ```bash
   mvn clean
   ```

2. **Download Dependencies and Build**
   ```bash
   mvn install
   ```

   **Expected Output:**
   ```
   [INFO] BUILD SUCCESS
   [INFO] Total time: XX.XXXs
   ```

3. **If Build Fails**
   - Check internet connection
   - Verify Maven is correctly installed
   - Try: `mvn clean install -U` (forces dependency updates)
   - Check logs for specific errors

---

## Step 4: Configure Database Connection

The database configuration is already set in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/student_management_db
spring.datasource.username=root
spring.datasource.password=root
```

**If you need to change these:**

1. Open `application.properties`
2. Update the database URL, username, and password
3. Save the file
4. Rebuild: `mvn clean install`

---

## Step 5: Run the Application

### Option A: Using Maven

```bash
mvn spring-boot:run
```

### Option B: Using JAR File

```bash
java -jar target/student-management-system-1.0.0.jar
```

### Option C: Using IDE

**IntelliJ IDEA:**
1. Open the project in IntelliJ
2. Right-click on `StudentManagementApplication.java`
3. Click "Run StudentManagementApplication"

**Eclipse:**
1. Open the project in Eclipse
2. Right-click on project → "Run As" → "Spring Boot App"

---

## Step 6: Verify Application Started Successfully

### Check Console Output

Look for these messages:
```
========================================
Student Management System Started Successfully
Server is running on http://localhost:8080
REST API Base URL: http://localhost:8080/api/v1/students
========================================
```

### Test API Endpoint

Open your browser and navigate to:
```
http://localhost:8080/api/v1/students
```

You should see an empty JSON array:
```json
[]
```

---

## Step 7: Test API Endpoints

### Using Postman

1. **Install Postman**: https://www.postman.com/downloads/

2. **Create New Request**
   - Method: POST
   - URL: `http://localhost:8080/api/v1/students`
   - Body (JSON):
   ```json
   {
     "firstName": "John",
     "lastName": "Doe",
     "email": "john.doe@example.com",
     "phoneNumber": "1234567890",
     "dateOfBirth": "2000-01-15"
   }
   ```
   - Click "Send"

3. **Expected Response**: 201 Created with student data

### Using cURL

```bash
curl -X POST http://localhost:8080/api/v1/students \
  -H "Content-Type: application/json" \
  -d "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"phoneNumber\":\"1234567890\",\"dateOfBirth\":\"2000-01-15\"}"
```

### Using Browser (for GET requests)

```
http://localhost:8080/api/v1/students
```

---

## Step 8: Check Database Tables

### Using pgAdmin

1. Open pgAdmin
2. Navigate: Databases → student_management_db → Schemas → public → Tables
3. You should see `students` table

### Using Command Line

```bash
psql -U root -d student_management_db
\dt
\q
```

---

## Troubleshooting

### Issue 1: Database Connection Failed

**Error Message:**
```
org.postgresql.util.PSQLException: Connection to localhost:5432 refused
```

**Solutions:**
- Ensure PostgreSQL is running
- Check if database exists: 
  ```bash
  psql -U root -d student_management_db
  ```
- Verify credentials in `application.properties`
- Restart PostgreSQL service

### Issue 2: Port 8080 Already in Use

**Error Message:**
```
Application failed to start with error: Address already in use: bind
```

**Solutions:**
Option A: Change port in `application.properties`
```properties
server.port=8081
```

Option B: Kill process using port 8080
```bash
# Windows PowerShell
netstat -ano | findstr "8080"
taskkill /PID <PID> /F
```

### Issue 3: Maven Build Fails

**Solutions:**
- Clear Maven cache:
  ```bash
  mvn clean
  ```
- Force dependency update:
  ```bash
  mvn install -U
  ```
- Check Java version:
  ```bash
  java -version
  ```
- Check Maven installation:
  ```bash
  mvn -version
  ```

### Issue 4: IDE Not Recognizing Spring Boot Project

**Solutions:**
- IntelliJ: Right-click project → "Mark Directory as" → "Sources Root"
- Eclipse: Right-click project → "Maven" → "Update Project"
- VS Code: Install Spring Boot extension

### Issue 5: Email Already Exists Error

**Error Message:**
```json
{
  "status": 400,
  "message": "Email already exists in the system"
}
```

**Solution:**
- Use a unique email address for testing
- Clear database and restart application
- Delete student with that email and create again

---

## Development Workflow

### 1. Making Code Changes

1. Edit Java files in `src/main/java`
2. Save changes
3. Rebuild: `mvn install`
4. Restart application

### 2. Adding New Features

Follow the existing package structure:
- Add entity in `entity/` package
- Create DTO in `dto/` package
- Create repository in `repository/` package
- Create service interface and implementation
- Create controller in `controller/` package

### 3. Quick Restart

```bash
# Stop current application (Ctrl+C)
# Then run:
mvn spring-boot:run
```

---

## Expected Project Structure

After successful setup, your project should have:

```
student-management-system/
├── pom.xml
├── README.md
├── API_DOCUMENTATION.md
├── SETUP_GUIDE.md
├── src/
│   ├── main/
│   │   ├── java/com/studentmgmt/
│   │   │   ├── StudentManagementApplication.java
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── entity/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   └── util/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── logback-spring.xml
│   └── test/
├── target/ (created after build)
├── logs/ (created when running)
└── .idea/ (if using IntelliJ)
```

---

## Verification Checklist

- [ ] Java 21 installed and verified
- [ ] Maven installed and verified
- [ ] PostgreSQL installed and running
- [ ] Database `student_management_db` created
- [ ] Project cloned/extracted
- [ ] `mvn install` completed successfully
- [ ] Application started without errors
- [ ] API endpoint accessible at `http://localhost:8080/api/v1/students`
- [ ] Can create a student via POST request
- [ ] Can retrieve all students via GET request
- [ ] Database table `students` exists and contains data

---

## Quick Reference Commands

```bash
# Build project
mvn clean install

# Run application
mvn spring-boot:run

# Build JAR
mvn package

# Run JAR
java -jar target/student-management-system-1.0.0.jar

# Check logs
tail -f logs/application.log

# Test API
curl http://localhost:8080/api/v1/students
```

---

## Next Steps

1. **Read API Documentation** (`API_DOCUMENTATION.md`)
   - Understand all available endpoints
   - Learn request/response formats

2. **Explore the Code**
   - Understand the layered architecture
   - Study design patterns used

3. **Extend the Application**
   - Add authentication (JWT tokens)
   - Add pagination for student list
   - Add unit tests

4. **Deploy to Production**
   - Containerize with Docker
   - Deploy to cloud (AWS, Azure, GCP)

---

## Additional Resources

- **Spring Boot**: https://spring.io/projects/spring-boot
- **Spring Data JPA**: https://spring.io/projects/spring-data-jpa
- **PostgreSQL**: https://www.postgresql.org/docs/
- **Maven**: https://maven.apache.org/guides/
- **Postman**: https://learning.postman.com/

---

## Support

For issues or questions:
1. Check the troubleshooting section
2. Review logs in `logs/application.log`
3. Check console output for error messages
4. Verify all prerequisites are installed correctly

---

**Good luck with your Student Management System! 🚀**

