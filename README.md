# 📚 Library Management System

## 📌 Overview
The **Library Management System** is a software application designed to streamline and automate library operations. It provides efficient management of books, users, and transactions, enhancing the experience for both library staff and patrons.

---

## 🚀 Features

### 🔐 Administrative Module
- Create, update, delete, and search for librarian and patron accounts  
- Manage admin credentials securely  
- Add, update, and remove books from the system  
- Categorize books by:
  - Genre  
  - Author  
  - Publication year  
- Track book status:
  - Available  
  - Checked out  
  - Reserved  

---

### 📖 Librarian Module
- Check out books for patrons  
- Process book returns  
- Manage reservations  
- Notify patrons when reserved books become available  

---

### 👤 Patron Module
- Create and manage personal accounts  
- Search for books using filters:
  - Title  
  - Author  
  - Genre  
- View book details (summary, availability)  
- Track borrowing history and due dates  
- Renew borrowed books  
- Reserve unavailable books  

---

### 🌐 User Module (Common for All Roles)
- Login and logout functionality  
- Update personal/contact information  
- Manage user preferences  

---

## 🗂️ Project Structure
LibraryMS/
│── src/ # Source code
│── jre/ # Java Runtime Environment
│── dataFiles/ # Data storage files
│── Pics of app/ # Application screenshots
│── .classpath # Eclipse classpath configuration
│── .project # Eclipse project file
│── .gitignore # Git ignored files
│── LibraryMS.jar # Executable JAR file
│── LibraryMS.exe # Windows executable
│── README.md # Project documentation


---

## ⚙️ Technologies Used
- Java  
- Object-Oriented Programming (OOP)  
- File Handling / Data Storage  
- (Optional: mention IDE if needed, e.g., Eclipse)

---

## ▶️ How to Run

### Option 1: Run Executable
- Double-click `LibraryMS.exe`

### Option 2: Run JAR File
```bash
java -jar LibraryMS.jar

### Option 3: Run from Source
Open project in your IDE (e.g., Eclipse)
Navigate to src/
Run the main class
