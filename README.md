# Book Inventory Management

This is a book inventory management project that helps you keep track of your book collection. It provides features such as adding new books, updating book information, and searching for books based on various criteria.

## Features

- Add new books: Easily add new books to your inventory by providing the book title, author, publisher details, and other relevant details.
- Update book information: Modify existing book details such as author, publisher details.
- Search and filter: Search for books based on title, author, genre, or any other custom criteria. Filter and sort the books to quickly find what you're looking for.
- Track book status: Keep track of whether a book is borrowed, available, or sold.

## Technologies Used

- Backend: Java 17, Spring Boot
- Frontend: Angular 16
- Database: MySQL (integrated with Spring Data JPA)

## Prerequisites

Make sure you have the following software installed:

- Java 17 JDK: [Download](https://adoptium.net/)
- Spring Boot CLI: [Installation guide](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started.installing.cli)
- Node.js: [Download](https://nodejs.org/en/download/)
- Angular CLI: Install globally using npm: `npm install -g @angular/cli`
- MySQL Server: [Download](https://dev.mysql.com/downloads/)
- MySQL Connector/J: The JDBC driver for MySQL.

## Getting Started

### Backend

1. Clone the repository: `git clone https://github.com/AKSHAY-BP/Book-Inventory-Management.git`
2. Navigate to the backend directory: `cd Book-Inventory-Management/backend`
3. Build and run the Spring Boot application: `./mvnw spring-boot:run`

### Frontend

1. Navigate to the frontend directory: `cd Book-Inventory-Management/frontend`
2. Install the dependencies: `npm install`
3. Start the Angular development server: `ng serve`

## Configuration

In the `application.properties` file located in the `backend/src/main/resources` directory, configure the MySQL database connection properties. Modify the following properties to match your MySQL database configuration:

