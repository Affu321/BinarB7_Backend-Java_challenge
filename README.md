## Table of Contents
- [Getting Started](#getting-started)
- [Challenge 1](#challenge-1)
- [Challenge 2](#challenge-2)
- [Challenge 3](#challenge-3)

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

### How to Run

1. **Clone the Repository:**
   ```sh
   git clone <repository-url>
   cd <repository-directory>

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

### How to Run

1. **Clone the Repository:**
   ```sh
   git clone <repository-url>
   cd <repository-directory>

## Challenge 3
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

1. **Install PostgreSQL**: Ensure PostgreSQL is installed on your system. You can download it from the [official website](https://www.postgresql.org/download/).
2. **Create a Database**: After installing PostgreSQL, create a new database. You can do this via the PostgreSQL command line or a graphical tool like pgAdmin.
3. **Restore the Dump**: Use the `pg_restore` command or a tool like pgAdmin to restore the database dump provided in this repository into the newly created database. For example:
   ```bash
   pg_restore -U <username> -d <database_name> dump_file.dump
