# Quick Start Guide

## ⚡ Get Started in 5 Minutes

### 1. Prerequisites Check
```bash
java -version          # Should show Java 21
mvn -version          # Should show Maven 3.6+
psql --version        # Should show PostgreSQL 12+
```

### 2. Create Database
```bash
psql -U root -h localhost
CREATE DATABASE student_management_db;
\q
```

### 3. Build Project
```bash
cd "D:\Real time project\student managment system"
mvn clean install
```

### 4. Start Application
```bash
mvn spring-boot:run
```

### 5. Test API
Open browser: `http://localhost:8080/api/v1/students`

---

## 📝 Common Tasks

### Create a Student
```bash
curl -X POST http://localhost:8080/api/v1/students \
  -H "Content-Type: application/json" \
  -d '{"firstName":"John","lastName":"Doe","email":"john@example.com","phoneNumber":"1234567890"}'
```

### Get All Students
```bash
curl http://localhost:8080/api/v1/students
```

### Get Single Student
```bash
curl http://localhost:8080/api/v1/students/1
```

### Update Student
```bash
curl -X PUT http://localhost:8080/api/v1/students/1 \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Jane","lastName":"Doe","email":"jane@example.com","phoneNumber":"9876543210"}'
```

### Delete Student
```bash
curl -X DELETE http://localhost:8080/api/v1/students/1
```

### Search by First Name
```bash
curl "http://localhost:8080/api/v1/students/search/firstName?firstName=John"
```

### Search by Last Name
```bash
curl "http://localhost:8080/api/v1/students/search/lastName?lastName=Doe"
```

---

## 🔧 Useful Commands

### Run Tests
```bash
mvn test
```

### Run Specific Test
```bash
mvn test -Dtest=StudentServiceImplTest
```

### Build JAR
```bash
mvn package
```

### Run JAR
```bash
java -jar target/student-management-system-1.0.0.jar
```

### View Logs
```bash
tail -f logs/application.log
```

### Clean Build Files
```bash
mvn clean
```

---

## 📂 Project Structure
```
├── src/main/java/com/studentmgmt/
│   ├── StudentManagementApplication.java (Start here!)
│   ├── controller/        (REST API)
│   ├── service/          (Business Logic)
│   ├── repository/       (Database)
│   ├── entity/           (Models)
│   ├── dto/              (API Data)
│   ├── exception/        (Error Handling)
│   ├── config/           (Configuration)
│   └── util/             (Helpers)
├── src/main/resources/
│   ├── application.properties  (Config)
│   └── logback-spring.xml      (Logging)
└── pom.xml (Dependencies)
```

---

## 🐛 Troubleshooting

| Problem | Solution |
|---------|----------|
| Port 8080 in use | Change in `application.properties`: `server.port=8081` |
| DB Connection error | Ensure PostgreSQL running: `psql -U root` |
| Build fails | Run: `mvn clean install -U` |
| Email exists error | Use unique email for testing |

---

## 📚 Documentation Files

1. **README.md** - Full project documentation
2. **API_DOCUMENTATION.md** - All API endpoints with examples
3. **SETUP_GUIDE.md** - Detailed installation guide
4. **ARCHITECTURE.md** - Design patterns and architecture
5. **QUICK_START.md** - This file!

---

## 🚀 Next Steps

1. Read **API_DOCUMENTATION.md** for all endpoints
2. Check **ARCHITECTURE.md** for design patterns
3. Review code in `controller/`, `service/`, `repository/` packages
4. Run unit tests: `mvn test`
5. Extend with new features!

---

## 📞 Support

- Check logs: `logs/application.log`
- See console output for errors
- Review documentation files
- Check API_DOCUMENTATION.md for examples

---

**Happy coding! 🎉**

