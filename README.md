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


# Getting Started - Challenge Binar Back-End Java Batch 7
Ini adalah sekumpulan Challenge Backend Java SpringBoot, dari belajar dasar" java, hingga membuat sebuah restAPI untuk sebuah aplikasi Back-End dengan dokumentasi menggunakan swagger

[![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?logo=intellij-idea&logoColor=white)](#)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=fff)](#)


## Challenge 1 
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
- ## How to Run

1. **Clone the Repository:**
   ```sh
   git clone <repository-url>
   cd <repository-directory>
## Challenge 2 - Object-Oriented Approach

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

## How to Run

1. **Clone the Repository:**
   ```sh
   git clone <repository-url>
   cd <repository-directory>
   ```
## Features

- Import a HTML file and watch it magically convert to Markdown
- Drag and drop images (requires your Dropbox account be linked)
- Import and save files from GitHub, Dropbox, Google Drive and One Drive
- Drag and drop markdown and HTML files into Dillinger
- Export documents as Markdown, HTML and PDF

# Challenge 3
In this challenge contains a PostgreSQL database dump for a project focusing on DDL (Data Definition Language) operations, database integration, and instructions on how to run it. Below is an overview of the database structure and how to execute it.

## Database Structure
The database dump includes the following tables:
1. **merchant**: Stores information about merchants, including their name, location, and open status.
2. **orderdetail**: Contains details of orders such as order ID, product ID, and quantity.
3. **orderentity**: Manages order entities, including order time, destination address, user ID, and completion status.
4. **product**: Stores product information like product name, price, and associated merchant ID.
5. **users**: Contains user details such as username, email address, and password.

## Instructions
To set up and run this database, follow these steps:
1. **Install PostgreSQL**: Ensure PostgreSQL is installed on your system. You can download it from the [official website](https://www.postgresql.org/download/).
2. **Create a Database**: After installing PostgreSQL, create a new database. You can do this via the PostgreSQL command line or a graphical tool like pgAdmin.
3. **Restore the Dump**: Use the `pg_restore` command or a tool like pgAdmin to restore the database dump provided in this repository into the newly created database. For example:
   ```bash
   pg_restore -U <username> -d <database_name> dump_file.dump
 # Challenge 4
In this challenge create a JPA Aplication (DDl from ERD + CRUD) with entities, repositories, and service, before you clone this challenge, makesure you already set all the requirements JPA Spring Boot
### Installation JPA
Make sure you have installed:
- Java 11 or newer
- Maven
-IDE (IntelliJ IDEA, Eclipse, or equivalent)

### Step 1: Using Spring Initializer
1. Open [Spring Initializer](https://start.spring.io/).
2.Configure your project with the following details:
   - Project: Maven Project
   - Language: Java
   - Spring Boot: Latest stable version
   - Project Metadata:
     - Group: `com.Binar.(you can customize)`
     - Artifact: `(you can customize)`
     - Name: `(you can customize)`
     - Description: `Aplikasi JPA dengan Entity, Repository, dan Service`
     - Package Name: `com.Binar.(you can customize)`
     - Packaging: Jar
     - Java: 11
3. Add the following dependencies:
   - Spring Web
   - Spring Data JPA
   - H2 Database (or another database as needed)
4. Click "Generate" to download the generated project.
5. Extract the downloaded project and open it in your IDE of choice.

### Langkah 2: Konfigurasi JPA

1. Buka file `application.properties` yang ada di direktori `src/main/resources`.
2. Tambahkan konfigurasi berikut untuk mengatur koneksi database dan JPA:

   ```properties
   # Konfigurasi Database
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=
   spring.datasource.password=
   
   # Konfigurasi JPA
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
Markdown is a lightweight markup language based on the formatting conventions
that people naturally use in email.
As [John Gruber] writes on the [Markdown site][df1]

   ```properties
   <dependencies>
  <dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-jpa</artifactId>
  </dependency>
<dependencies>
   ```
