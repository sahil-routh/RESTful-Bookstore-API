# RESTful-Bookstore-API

# Book Management System

## Introduction

The Book Management System is a RESTful web application built using Spring Boot and Spring Data JPA. It allows users to manage books and authors efficiently, providing CRUD (Create, Read, Update, Delete) operations through a REST API. This system can be used as a backend for web or mobile applications.

## Features

* Add, update, delete, and view books
* Associate books with authors
* Filter books by title and price range
* Pagination support for book listings
* H2 in-memory database for testing and development

## Tools Used

* **Java 22**
* **Spring Boot 3.5.5**
* **Spring Data JPA**
* **H2 Database**
* **Maven** for dependency management
* **Postman** for API testing
* **IntelliJ IDEA** for development

## Project Structure

```
src
├── main
│   ├── java/com/example/demo
│   │   ├── controller      # REST controllers
│   │   ├── entities        # JPA entities (Book, Author)
│   │   ├── repositories    # JPA repositories
│   │   └── DemoApplication.java
│   └── resources
│       ├── application.properties
│       └── data.sql (optional)
```

## API Endpoints

| Method | Endpoint        | Description                                       |
| ------ | --------------- | ------------------------------------------------- |
| GET    | /api/books      | Get all books (supports pagination and filtering) |
| GET    | /api/books/{id} | Get a single book by ID                           |
| POST   | /api/books      | Create a new book                                 |
| PUT    | /api/books/{id} | Update an existing book                           |
| DELETE | /api/books/{id} | Delete a book by ID                               |

## Steps to Run the Project

1. Clone the repository:

   ```
   git clone <repository_url>
   ```
2. Open the project in **IntelliJ IDEA**.
3. Ensure **Java 22** and **Maven** are installed.
4. Build the project using Maven:

   ```
   mvn clean install
   ```
5. Run the application:

   ```
   DemoApplication.java (Run as Java Application)
   ```
6. Test APIs using **Postman**:

   * Base URL: `http://localhost:8080/api/books`

## Conclusion

This Book Management System demonstrates how to build a RESTful API with Spring Boot and JPA, including CRUD operations, pagination, filtering, and proper entity relationships. It provides a solid foundation for building larger library or inventory management applications.

