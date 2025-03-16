# libb
# Library Management System

## 📌 Overview
This **Library Management System** is a Java-based application that enables users to manage books, DVDs, newspapers, customers, librarians, and transactions efficiently. The system follows **Object-Oriented Programming (OOP)** principles and provides different roles with specific functionalities.

## 🚀 Features
- **User Roles:**
  - **Customer**: Can borrow/purchase items, check balance, view transaction history.
  - **Librarian**: Manages library items, adds customers, updates stock and pricing.
  - **Manager**: Can create/delete librarians, modify salaries, and view financial records.
- **Item Management:** Books, DVDs, and Newspapers can be added, updated, or removed.
- **Transaction System:** Customers can purchase or borrow items based on balance availability.
- **Encapsulation & Abstraction:** Secure handling of user data and item details.
- **Exception Handling:** `LibraryException` ensures smooth error reporting.

## 🛠️ Classes and Responsibilities
### **Users and Roles**
- **`User` (Abstract Class)**: Base class for all users with username, password, and phone validation.
- **`Customer` (Extends `User`)**: Manages customer balances and transaction history.
- **`Librarian` (Extends `User`)**: Adds/modifies items, handles customers, and transactions.
- **`Manager` (Extends `Librarian`)**: Creates/deletes librarians, modifies salaries, and views reports.

### **Library Items**
- **`Item` (Base Class)**: Represents any library item with ID, name, price, and stock.
- **`Book`, `DVD`, `Newspaper` (Extend `Item`)**: Specific implementations for different media types.

### **Library System & Transactions**
- **`LibrarySystem`**: Manages users and items, processes transactions, and maintains records.
- **`Library`**: Represents the library's details such as name, phone number, and opening hours.
- **`LibraryException`**: Custom exception handling for error cases.

## 📖 How to Use
### **1. Running the System**
1. **Compile** all Java files:
   ```sh
   javac *.java
   ```
2. **Run** the main program:
   ```sh
   java Main
   ```

### **2. Basic Operations**
- **Librarians** can add/edit/delete items, modify prices, add customers, and manage transactions.
- **Customers** can borrow or purchase items if they have sufficient balance.
- **Managers** can oversee all operations, including staff salaries and reports.

## 🔧 Future Improvements
- Implement **Graphical User Interface (GUI)** for better user experience.
- Add **database integration** for persistent data storage.
- Improve **AI-based book recommendations** for customers.

## 📜 License
This project is open-source. Feel free to modify and enhance it!

