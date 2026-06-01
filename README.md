# Study Planner API

A Spring Boot REST API backend for managing courses, assignments, and study sessions.  
This project was created for **Desktop Application Programming II - Backend Development**.

## Main Features

- User registration and login
- JWT authentication
- Role-based authorization: `USER`, `ADMIN`
- Course CRUD
- Assignment CRUD
- Study session CRUD
- DTO request/response structure
- Bean Validation
- Global exception handling
- Pagination and sorting
- Search and filtering
- Soft delete
- Scheduled task for overdue assignments
- Swagger/OpenAPI documentation
- H2 database for development

## Technology Stack

- Java 17
- Spring Boot 3.5.x
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- H2 Database
- PostgreSQL Driver
- Lombok
- Validation
- Swagger/OpenAPI
- JUnit 5

## How to Run in VS Code

1. Open the project folder in VS Code.
2. Install:
   - Extension Pack for Java
   - Spring Boot Extension Pack
3. Open terminal in the project root.
4. Run:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Application starts at:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

H2 Console:

```text
http://localhost:8080/h2-console
```

H2 JDBC URL:

```text
jdbc:h2:mem:studyplannerdb
```

## Sample Credentials

### Student User

```text
username: student
password: password123
```

### Admin User

```text
username: admin
password: admin1234
```

## Authentication Flow

### Login

```http
POST /api/auth/login
Content-Type: application/json
```

```json
{
  "username": "student",
  "password": "password123"
}
```

Copy the returned token and use:

```text
Authorization: Bearer YOUR_TOKEN
```

## Main API Endpoints

### Auth

- `POST /api/auth/register`
- `POST /api/auth/login`

### Courses

- `GET /api/courses`
- `GET /api/courses/{id}`
- `POST /api/courses`
- `PUT /api/courses/{id}`
- `DELETE /api/courses/{id}`

### Assignments

- `GET /api/assignments`
- `GET /api/assignments/{id}`
- `POST /api/assignments`
- `PUT /api/assignments/{id}`
- `PATCH /api/assignments/{id}/complete`
- `DELETE /api/assignments/{id}`

### Study Sessions

- `GET /api/study-sessions`
- `GET /api/study-sessions/{id}`
- `POST /api/study-sessions`
- `PUT /api/study-sessions/{id}`
- `PATCH /api/study-sessions/{id}/complete`
- `DELETE /api/study-sessions/{id}`

### Admin

- `GET /api/admin/stats`

## Example Create Course

```http
POST /api/courses
Authorization: Bearer YOUR_TOKEN
Content-Type: application/json
```

```json
{
  "title": "Mathematics",
  "description": "Calculus and algebra study plan",
  "color": "purple"
}
```

## Example Create Assignment

```json
{
  "title": "Finish Lab 5",
  "description": "Implement JWT security",
  "deadline": "2026-06-01T18:00:00",
  "courseId": 1
}
```

## Example Create Study Session

```json
{
  "topic": "Review Spring Security",
  "studyDate": "2026-06-02",
  "startTime": "18:00:00",
  "endTime": "19:30:00",
  "courseId": 1
}
```

## Run Tests

```bash
./mvnw test
```

Windows:

```powershell
.\mvnw.cmd test
```

## Development Status

- [x] Lab 1: Project setup and basic REST endpoints
- [x] Lab 2: Database integration, JPA entities, repositories
- [x] Lab 3: Service layer, business logic, exceptions
- [x] Lab 4: DTOs, validation, error handling
- [x] Lab 5: Security, JWT, authorization
- [x] Lab 6: Additional features, Swagger, testing, polishing
- [ ] Lab 7: Defense
