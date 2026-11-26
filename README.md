 Student Management System (Java)

Project Overview
The Student Management System is a console-based Java application that allows users to manage student records efficiently. It supports adding, viewing, searching, updating, and deleting student details.

The application uses Java Object Serialization to store student records in a file, so the data remains available even after closing and reopening the application.
Features
- Add new student records
- View all student records
- Search students using Student ID
- Update existing student details
- Delete student records
- Prevent duplicate Student IDs
- Validate student information
- Save and retrieve records using Java Serialization
- Handle file operations and saving errors

Technologies Used
- Java
- Object-Oriented Programming (OOP)
- ArrayList and Collection Framework
- Serialization and Deserialization
- File Handling
- Exception Handling

 Project Structure

StudentManagementSystem/
│
├── Student.java
├── StudentManager.java
├── StudentFileRepository.java
├── Main.java
├── students.dat
└── README.md


 Class Responsibilities

 1. Student.java
- Represents a student.
- Stores student ID, name, age, department, and email.
- Uses encapsulation with private fields.
- Includes validation for student details.
- Implements the Serializable interface.

 2. StudentManager.java
- Manages student records.
- Implements Add, View, Search, Update, and Delete operations.
- Prevents duplicate student IDs.
- Handles changes to student records and saving failures.

3. StudentFileRepository.java
- Handles file storage operations.
- Saves student records using ObjectOutputStream.
- Retrieves records using ObjectInputStream.
- Handles file-related exceptions.

 4. Main.java
- Provides the console-based user interface.
- Displays the menu.
- Accepts user input.
- Calls the appropriate methods from StudentManager.

 How to Run the Project

 Step 1: Compile the Java Files
```bash
javac Student.java StudentFileRepository.java StudentManager.java Main.java
```

 Step 2: Run the Application
```bash
java Main
```

 Data Persistence
Student records are stored in `students.dat` using Java Serialization.

When the application starts, previously saved records are loaded automatically. Any successful changes are saved to the file.

 Concepts Covered
- Classes and Objects
- Encapsulation
- Constructors
- Methods
- ArrayList
- Collection Framework
- Interfaces
- Serialization and Deserialization
- File Handling
- Exception Handling
- Separation of Responsibilities
