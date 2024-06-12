## Table of Contents
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Challenge 1 ](#Challenge-1)
- [Challenge 2](#Challenge-2)
- [Challenge 3](#Challenge-3)
- [Challenge 4](#Challenge-4)
- [Challenge 5](#Challenge-5)
- [Challenge 6](#Challenge-6)
- [Installation JPA](#Installation-JPA)
- [License](#license)


# Getting Started 
This is a collection of SpringBoot Java Backend Challenges, from learning the basics of Java, to creating a restAPI for a Back-End application with documentation using swagger

[![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?logo=intellij-idea&logoColor=white)](#)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=fff)](#)
![swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white)

## Installation
Makesure you already install :
- Integrated Development Environments (IDEs), you can install [Idea Intellij Community](https://www.jetbrains.com/idea/download/?section=windows)
- [Java Development Kit (JDK)](https://www.oracle.com/id/java/technologies/downloads/) (version 8 or higher)
- [PostgreSql](https://jdbc.postgresql.org/) as a Database
- **Maven**  as the build tool

# Setting Up JPA and Spring Security in IntelliJ IDEA
This guide will help you set up a Java project in IntelliJ IDEA with JPA (Java Persistence API) and Spring Security.
### 1. Creating a New Project
1. Open IntelliJ IDEA.
2. Select `New Project` from the welcome screen.
3. Choose `Spring Initializr` as the project type.
4. Configure your project:
    - Group: `com.example`
    - Artifact: `myproject`
    - Name: `myproject`
    - Dependencies: `Spring Web`, `Spring Data JPA`, `Spring Security`
    
### 2. Configuring the Build Tool

This project uses Maven as the build tool. The `pom.xml` file will be generated automatically if you use Spring Initializr. Ensure the following dependencies are in your `pom.xml` file:

#### Maven (`pom.xml`)

```xml
<dependencies>
    <!-- Spring Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Spring Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Database (e.g., H2 Database) -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```
## Configuration Apllication Properties :
```
# Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

# JPA Properties
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Spring Security Properties (optional)
spring.security.user.name=admin
spring.security.user.password=admin
```
## How to Run
  ```
   git clone <repository-url>
   cd <repository-directory>
   ```

# Challenge 1 
In this challenge, the restaurant ordering system is implemented using a procedural programming approach.

### Key Features:
- Display a menu with various food and drink options.
- Allow users to select items and specify quantities.
- Calculate the total price of the order.
- Confirm and generate a receipt saved to a file (`struk_pembelian.txt`).

### Code Overview:
- The application flow is controlled by a `while` loop that continues until the user decides to exit.
- Menu selection is handled using a `switch` statement.
- Orders and quantities are stored in `ArrayList` objects.
- The total price is calculated dynamically as items are added to the order.
- Upon order confirmation, a receipt is generated and saved to a file.
## Challenge 2
In this challenge, the restaurant ordering system is refactored to use Object-Oriented Programming principles.

### Key Features:
- Uses a `MenuItem` class to represent each menu item with a name and price.
- Uses a `Restaurant` class to handle the main application logic.
- The `Restaurant` class contains methods to display the menu, process orders, and generate receipts.
- Improved error handling using `try-catch` blocks.

### Code Overview:
- `MenuItem` class: Represents individual menu items.
- `Restaurant` class: Contains methods to start the application, process user input, and handle orders.
- `MainRestaurant` class: Contains the `main` method to launch the application.
- Menu items are stored in a static list initialized in the `Restaurant` class.
- Improved structure and readability by encapsulating related functionality within classes and methods.

### Features
- Import a HTML file and watch it magically convert to Markdown
- Drag and drop images (requires your Dropbox account be linked)
- Import and save files from GitHub, Dropbox, Google Drive and One Drive
- Drag and drop markdown and HTML files into Dillinger
- Export documents as Markdown, HTML and PDF

# Challenge 3
In this challenge contains a PostgreSQL database dump for a project focusing on DDL (Data Definition Language) operations, database integration, and instructions on how to run it. Below is an overview of the database structure and how to execute it.

### Database Structure
The database dump includes the following tables:
1. **merchant**: Stores information about merchants, including their name, location, and open status.
2. **orderdetail**: Contains details of orders such as order ID, product ID, and quantity.
3. **orderentity**: Manages order entities, including order time, destination address, user ID, and completion status.
4. **product**: Stores product information like product name, price, and associated merchant ID.
5. **users**: Contains user details such as username, email address, and password.

### Instructions
To set up and run this database, follow these steps:
1. **Install PostgreSQL**: Ensure PostgreSQL is installed on your system. You can download [PostgreSql](https://jdbc.postgresql.org/)
2. **Create a Database**: After installing PostgreSQL, create a new database. You can do this via the PostgreSQL command line or a graphical tool like pgAdmin.
3. **Restore the Dump**: Use the `pg_restore` command or a tool like pgAdmin to restore the database dump provided in this repository into the newly created database. For example:
   ```bash
   pg_restore -U <username> -d <database_name> dump_file.dump
   
# Challenge 4
In this challenge, a JPA-based system is implemented to manage entities related to a restaurant ordering system. The implementation follows the principles of object-oriented programming and leverages Spring Data JPA for database interactions.

### Key Features:
Abstract base entity class with common audit fields.
Entity classes representing merchants, orders, products, order details, and users.
Service layer for managing CRUD operations and business logic.
Custom response handling for consistent API responses.
Pagination support for retrieving data.

### Code Overview:
### Abstract Base Entity:
**AbstrackFood** is base class for common fields such as creation, deletion, and update timestamps.
### Entity Classes:
- **Merchant:** Represents a merchant with fields like id, merchant_name, merchant_location, and products.
- **Order:** Represents an order with fields like id, order_time, destination_address, completed, and associated user.
- **OrderDetail:** Represents order details with fields like id, quantity, total_price, and associated order and product.
- **Product:** Represents a product with fields like id, product_name, price, and associated merchant.
- **User:** Represents a user with fields like id, username, emailAddress, and password.

    Each service class (MerchantServiceImpl, OrderServiceImpl, ProductServiceImpl, UserServiceImpl) provides CRUD operations along with pagination support for their respective entities (Merchant, Order, Product, User).

# Challenge 5 
### Key Features:
- **Merchant Controller:**
  - Manages CRUD operations for merchants.
  - Provides endpoints for retrieving, adding, updating, and deleting merchants.
  - Supports fetching a single merchant by ID.

- **Order Controller:**
  - Handles CRUD operations for orders.
  - Offers functionalities to retrieve all orders, add new orders, update existing orders, delete orders, and fetch a single order by ID.

- **Product Controller:**
  - Manages products within the system.
  - Provides endpoints for fetching all products, adding new products, updating existing products, deleting products, and retrieving a single product by ID.

- **User Controller:**
  - Facilitates user management functionalities.
  - Offers endpoints for fetching all users, adding new users, updating existing users, deleting users, and retrieving a single user by ID.

## Code Review:

- Each controller follows RESTful conventions with clear and descriptive endpoint paths.
- Annotations such as `@RestController` and `@RequestMapping` are appropriately used to define controller components and base URL paths.
- The controllers utilize various HTTP methods (`GET`, `POST`, `PUT`, `DELETE`) to perform CRUD operations.
- Request bodies (`@RequestBody`) and path variables (`@PathVariable`) are effectively utilized for handling input data and dynamic parts of the URL.
- Responses are returned as `ResponseEntity` objects, allowing precise control over HTTP status codes and response bodies.
- Interaction with service classes ensures separation of concerns and modularity in the codebase.

### Explanation of InvoiceService Methods:
**InvoiceService** is responsible for generating invoices and reports for merchants:

- **generateInvoice:**
  - This method creates a PDF file containing transaction details of user orders, serving as an invoice for the purchases made.

- **generateReportingMerchant:**
  - This method generates reports detailing the merchant's income over a specific period.
