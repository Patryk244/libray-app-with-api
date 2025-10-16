# Library Management System API

REST API application for managing a library system, built with Spring Boot and MySQL.

## Table of Contents
- [Overview](#overview)
- [Technologies](#technologies)
- [Features](#features)
- [API Endpoints](#api-endpoints)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Testing](#testing)

## Overview

This application provides a complete library management system with functionality for managing books, copies, readers, and loans. It implements RESTful architecture with proper error handling and data validation.

## Technologies

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **MySQL 8.0**
- **Gradle 8.14.3**
- **Lombok**
- **JUnit 5 & Mockito** (testing)
- **Jakarta Validation**

## Features

- **Title Management**: Add and retrieve book titles with author information
- **Copy Management**: Manage physical copies of books with status tracking
- **Reader Management**: Register and manage library members
- **Loan System**: Borrow and return books with automatic status updates
- **Status Tracking**: Monitor copy availability (IN_CIRCULATION, BORROWED, DAMAGED, LOST)
- **Validation**: Input validation for allDTO objects
- **Exception Handling**: Global error handling with custom exceptions


## API Endpoints

### Titles

```
GET    /v1/titles                          - Get all titles
GET    /v1/titles/find/titleId/{TITLE_ID}  - Find title by ID
POST   /v1/titles/create/title             - Create new title
```

### Copies

```
GET    /v1/copies                                              - Get all copies
GET    /v1/copies/count/title/{title}/available               - Count available copies
GET    /v1/copies/find/copy/title/{title}/author/{author}     - Find copies by title and author
POST   /v1/copies/add/title/{title}/author/{author}           - Add new copy
PUT    /v1/copies/change/status/copyId/{copyId}               - Update copy status
```

### Readers

```
GET    /v1/readers                    - Get all readers
GET    /v1/readers/find/readerId/{id} - Find reader by ID
POST   /v1/readers/create/reader      - Create new reader
```

### Loans

```
GET    /v1/loans                                                         - Get all loans
POST   /v1/loans/create/borrow/reader/id/{readerId}/title/{title}/author/{author}  - Borrow book
PUT    /v1/loans/reader/{readerId}/return/title/{title}/author/{author}            - Return book
```

## Installation

### Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Gradle (wrapper included)

### Steps

1. Clone the repository:
```bash
git clone <repository-url>
cd library-app-with-api
```

2. Create MySQL database:
```sql
CREATE DATABASE libray_app;
```

3. Update database credentials in `src/main/resources/application.properties`:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Configuration

### application.properties

```properties
spring.application.name=libray-app-with-api

spring.datasource.url=jdbc:mysql://localhost:3306/libray_app?serverTimezone=Europe/Warsaw&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.database=mysql
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

### Status Types

Copy status enum values:
- `IN_CIRCULATION` - Available for borrowing
- `BORROWED` - Currently borrowed
- `DAMAGED` - Damaged and unavailable
- `LOST` - Lost and unavailable

## Running the Application

### Using Gradle

```bash
./gradlew bootRun
```

### Using JAR

```bash
./gradlew build
java -jar build/libs/library-app-with-api-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## Testing

Run all tests:
```bash
./gradlew test
```

### Test Coverage

The project includes unit tests for:
- `CopyDbService`
- `LoanDbService`
- `ReaderDbService`
- `TitleDbService`

Tests use Mockito for mocking dependencies and JUnit 5 for assertions.

## Project Structure

```
src/
├── main/
│   ├── java/com/library_app_with_api/
│   │   ├── controller/          # REST controllers
│   │   ├── domain/              # Entity classes
│   │   │   ├── dto/            # Data Transfer Objects
│   │   │   └── enums/          # Enum definitions
│   │   ├── mapper/              # DTO-Entity mappers
│   │   ├── repository/          # JPA repositories
│   │   └── service/             # Business logic services
│   └── resources/
│       └── application.properties
└── test/                        # Unit tests
```

## Error Handling

The application includes global exception handling for:
- `TitleNotFound` - HTTP 404
- `ReaderNotFound` - HTTP 404
- `CopyNotFound` - HTTP 404
- `LoanNotFound` - HTTP 404
- `ReaderHasBorrowedThisTitle` - HTTP 400
- `TitleIsNotAccessToBorrow` - HTTP 500
- `DataIntegrityViolationException` - HTTP 400
- `MethodArgumentNotValidException` - HTTP 400

## API Usage Examples

### Create a new title
```bash
curl -X POST http://localhost:8080/v1/titles/create/title \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Rok 1984",
    "author": "George Orwell",
    "dateRelease": 1949
  }'
```

### Create a new reader
```bash
curl -X POST http://localhost:8080/v1/readers/create/reader \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jan",
    "lastName": "Kowalski"
  }'
```

### Borrow a book
```bash
curl -X POST http://localhost:8080/v1/loans/create/borrow/reader/id/1/title/Rok%201984/author/George%20Orwell
```

### Return a book
```bash
curl -X PUT http://localhost:8080/v1/loans/reader/1/return/title/Rok%201984/author/George%20Orwell
```

## Author

Patryk Rejent.
