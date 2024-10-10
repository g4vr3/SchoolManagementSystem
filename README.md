# School Management System

## Overview

This project is a console-based school management system written in Java. The system allows for the management of **students**, **courses**, and **enrollments**. It uses a layered architecture with Data Transfer Objects (DTOs) for data encapsulation and Data Access Objects (DAOs) for database interaction with a **MariaDB** instance.

The program is designed to maintain consistency and security while performing CRUD (Create, Read, Update, Delete) operations and aims to avoid SQL injection attacks through the use of **prepared statements**.

### **Note**: This project serves as a **base** system. Additional input validation, testing, and functionality are required to enhance the program and make it production-ready.

## Features

1. **Student Management**
   - Create, read, update, delete students.
   - Sort students by ID or name.

2. **Course Management**
   - Create, read, update, delete courses.
   - Sort courses by ID or name.

3. **Enrollment Management**
   - Enroll students in courses.
   - Validate that students and courses exist before enrollment.
   - Prevent duplicate enrollments for the same student in a course.

## Project Structure

- **DTOs**: Data Transfer Objects that encapsulate the entities such as `DTOAlumno` for students, `DTOAsignatura` for courses, and `DTOMatricula` for enrollments.
- **DAOs**: Data Access Objects that handle database operations using JDBC with prepared statements, ensuring secure database access and preventing SQL injection.
- **Management Classes**: Classes like `GestionAlumno`, `GestionAsignatura`, and `GestionMatricula` provide the logic for handling students, courses, and enrollments respectively.

## Database

The project uses **MariaDB** as the database. JDBC is employed for secure communication between the Java application and the database. A connection pool with a singleton pattern is used to manage database connections.

### Key Design Considerations:
- **SQL Injection Prevention**: All database queries are executed using **prepared statements**, which ensure user inputs are properly escaped.
- **Data Consistency**: CRUD operations synchronize data between in-memory lists (e.g., `ArrayList`) and the database, ensuring consistency between the two.

## Future Enhancements

This is a **base project** and requires further work to achieve a more robust system:

1. **Input Validation**: Current input handling is basic. More sophisticated validation rules should be added to ensure data integrity (e.g., valid phone numbers, date ranges, etc.).
2. **Testing**: Unit and integration tests should be implemented to validate the system's functionality and ensure reliable operation under different scenarios.
3. **Additional Features**:
   - Viewing enrollments by student or by course.
   - Enhanced reporting and data filtering.
   - User authentication and role-based access control.