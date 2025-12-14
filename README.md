# Student Management System

## Group Members
1. **Ubalde IBYIMANIKORA** -  24RP05770
2. **Docile IMBEREYEMASO** -  24RP05672 

## Project Title
Development of a Java Swing Desktop CRUD Application Using JDBC and OOP Principles

## Description
A comprehensive desktop application for managing student records with full CRUD (Create, Read, Update, Delete) operations. Built using Java Swing for the graphical user interface, JDBC for MySQL database connectivity, and implementing Object-Oriented Programming principles with RegEx data validation.

## Features
**Complete CRUD Operations** - Add, View, Update, Delete student records  
**Data Validation** - RegEx validation for all input fields  
**Database Integration** - MySQL with JDBC using PreparedStatement  
**User-Friendly GUI** - Java Swing interface with JTable display  
**OOP Principles** - Encapsulation, Abstraction, Inheritance, Polymorphism  
**Error Handling** - Comprehensive exception handling  
**Input Validation** - Prevents invalid data entry  

## Technologies Used
- Java 21
- Java Swing (GUI)
- JDBC (Database Connectivity)
- MySQL 8.0
- RegEx (Regular Expressions)
- XAMPP (Local Server)

## Prerequisites
1. **Java Development Kit (JDK)** 8 or higher
2. **Eclipse IDE** or any Java IDE
3. **XAMPP** with MySQL
4. **MySQL Connector/J** (JDBC Driver)

## Installation & Setup

### Step 1: Database Setup
1. Install and start **XAMPP**
2. Start **Apache** and **MySQL** services
3. Open phpMyAdmin: http://localhost/phpmyadmin
4. Click on **SQL** tab
5. Copy and paste the content from `database.sql`
6. Click **Go** to execute

### Step 2: Project Setup
1. **Clone this repository** or download as ZIP
2. Open **Eclipse IDE**
3. **File → Import → Existing Projects into Workspace**
4. Select the project folder
5. Add MySQL Connector/J to build path

### Step 3: Database Configuration
Update credentials in `DatabaseConnection.java` if needed:
```java
private static final String URL = "jdbc:mysql://localhost:3306/students_db";
private static final String USERNAME = "root";
private static final String PASSWORD = "";
