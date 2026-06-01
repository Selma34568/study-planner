# Study Planner API

A Spring Boot REST API backend for managing courses, assignments, and study sessions.

This project was created for **Desktop Application Programming II – Backend Development**.

---

# Main Features

* User registration and login
* JWT authentication
* Role-based authorization (USER, ADMIN)
* Course CRUD operations
* Assignment CRUD operations
* Study Session CRUD operations
* DTO request/response structure
* Bean Validation
* Global exception handling
* Pagination and sorting
* Search and filtering
* Soft delete
* Scheduled task for overdue assignments
* Swagger/OpenAPI documentation
* H2 database for development

---

# Technology Stack

* Java 17
* Spring Boot 3.5.x
* Spring Web
* Spring Data JPA
* Spring Security
* JWT
* H2 Database
* PostgreSQL Driver
* Lombok
* Validation
* Swagger/OpenAPI
* JUnit 5

---

# Architecture

### Controller Layer

Handles incoming HTTP requests and returns HTTP responses.

### Service Layer

Contains business logic, validation rules, and application workflows.

### Repository Layer

Provides database access using Spring Data JPA.

### Security Layer

Handles JWT authentication and role-based authorization.

### Database Layer

Uses H2 Database for development and testing purposes.

---

# Entity Relationships

```text
User
 ├── Courses
 ├── Assignments
 └── Study Sessions

Course
 ├── Assignments
 └── Study Sessions

Assignment
 └── Belongs to one Course

Study Session
 └── Belongs to one Course
```

---

# How to Run in VS Code

## Prerequisites

Install:

* Extension Pack for Java
* Spring Boot Extension Pack
* Java 17
* Maven

---

## Run Application

Open terminal in the project root and run:

```bash
./mvnw spring-boot:run
```

Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Application starts at:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

H2 Console:

```text
http://localhost:8080/h2-console
```

H2 JDBC URL:

```text
jdbc:h2:mem:studyplannerdb
```

---

# Sample Credentials

## Student User

```text
username: student
password: password123
```

## Admin User

```text
username: admin
password: admin1234
```

---

# Authentication Flow

## Login

```http
POST /api/auth/login
```

Request:

```json
{
  "username": "student",
  "password": "password123"
}
```

The API returns a JWT token.

Use the token in subsequent requests:

```http
Authorization: Bearer YOUR_TOKEN
```

---

# Main API Endpoints

## Authentication

```http
POST /api/auth/register
POST /api/auth/login
```

---

## Courses

```http
GET    /api/courses
GET    /api/courses/{id}
POST   /api/courses
PUT    /api/courses/{id}
DELETE /api/courses/{id}
```

---

## Assignments

```http
GET    /api/assignments
GET    /api/assignments/{id}
POST   /api/assignments
PUT    /api/assignments/{id}
PATCH  /api/assignments/{id}/complete
DELETE /api/assignments/{id}
```

---

## Study Sessions

```http
GET    /api/study-sessions
GET    /api/study-sessions/{id}
POST   /api/study-sessions
PUT    /api/study-sessions/{id}
PATCH  /api/study-sessions/{id}/complete
DELETE /api/study-sessions/{id}
```

---

## Admin

```http
GET /api/admin/stats
```

---

# Example Requests

## Create Course

```http
POST /api/courses
Authorization: Bearer YOUR_TOKEN
```

```json
{
  "title": "Mathematics",
  "description": "Calculus and algebra study plan",
  "color": "purple"
}
```

---

## Create Assignment

```json
{
  "title": "Finish Lab 5",
  "description": "Implement JWT security",
  "deadline": "2026-06-01T18:00:00",
  "courseId": 1
}
```

---

## Create Study Session

```json
{
  "topic": "Review Spring Security",
  "studyDate": "2026-06-02",
  "startTime": "18:00:00",
  "endTime": "19:30:00",
  "courseId": 1
}
```

---

# Testing

Run all tests:

```bash
./mvnw test
```

Windows:

```powershell
.\mvnw.cmd test
```

Test Result:

```text
Tests run: 2
Failures: 0
Errors: 0
BUILD SUCCESS
```

---

# Additional Features Implemented

* JWT Authentication
* Role-Based Authorization
* Pagination
* Sorting
* Search and Filtering
* Soft Delete
* Scheduled Tasks
* Swagger/OpenAPI Documentation
* Bean Validation
* Global Exception Handling

---

# Development Progress

### Lab 1

Project setup and REST endpoints

### Lab 2

Database integration, JPA entities, repositories

### Lab 3

Service layer, business logic, exceptions

### Lab 4

DTOs, validation, error handling

### Lab 5

Security, JWT authentication, authorization

### Lab 6

Additional features, Swagger, testing, polishing

### Lab 7

Project defense

---

# Author

**Ahsen Nimet Elmas**

Desktop Application Programming II
Computer Engineering
