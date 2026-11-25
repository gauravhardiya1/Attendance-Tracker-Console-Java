# 🎓 Attendance Tracker – DSA Project

A Java-based Attendance Tracker designed to manage student attendance efficiently using Data Structures and Algorithms.

## 📌 Project Overview

The Attendance Tracker is a menu-driven Java application that helps maintain student records, mark daily attendance, search students, calculate attendance percentages, and sort students based on their attendance performance.

The project demonstrates the practical implementation of DSA concepts in a real-world student attendance management system.

## 🚀 Features

- **Add Student:** Register students with automatically generated roll numbers.
- **Mark Attendance:** Mark students as Present (P) or Absent (A).
- **Search Student:** Find student records using roll numbers.
- **Attendance Percentage:** Calculate attendance percentage automatically.
- **Sort Students:** Arrange students according to attendance percentage.
- **File Handling:** Save and load attendance records using `attendance.txt`.
- **Data Validation:** Handle invalid roll numbers and attendance inputs.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core programming language |
| VS Code | Development environment |
| 2D Array | Store attendance records |
| HashMap | Fast student lookup |
| File Handling | Permanent data storage |

## 🧠 DSA Concepts Implemented

1. **Two-Dimensional Array:** Stores student attendance records in rows and columns.
2. **HashMap:** Maps student roll numbers to their corresponding array indexes.
3. **Searching:** Finds student records using roll numbers.
4. **Sorting:** Arranges students by attendance percentage.
5. **Loops and Methods:** Manage repetitive operations and modular program logic.
6. **File Handling:** Maintains records between program executions.

## ⏱️ Time Complexity

| Operation | Time Complexity |
|---|---|
| Student Lookup (HashMap) | O(1) Average |
| Attendance Update | O(1) Average |
| Percentage Calculation | O(D) per student |
| Sorting | O(N log N), as specified in project report |
| Data Loading | O(N × D) |

Where:
- N = Number of students
- D = Number of recorded days

## 💻 How to Run the Project

### Prerequisites
- Java JDK installed
- Visual Studio Code (or any Java IDE)

### Steps

1. Clone the repository:

   ```bash
   git clone YOUR_GITHUB_REPOSITORY_URL
   ```

2. Open the project folder in VS Code.

3. Navigate to the Java source file directory.

4. Compile the program:

   ```bash
   javac Main.java
   ```

5. Run the application:

   ```bash
   java Main
   ```

## 📊 Attendance Calculation

The attendance percentage is calculated using:

**Attendance Percentage = (Present Days / Recorded Days) × 100**

Example:

- Present Days = 8
- Recorded Days = 10
- Attendance Percentage = 80%

## 📁 Project Structure

```text
Attendance-Tracker/
│
├── Main.java
├── attendance.txt
├── README.md
│
└── docs/
    ├── Attendance_Tracker_Project_Report.pdf
    └── Attendance_Tracker_Project_Presentation.pptx
```

*Note: Keep the report and presentation inside the docs folder using the filenames shown above, or update the links below to match your actual filenames.*

## 📄 Project Documentation

- 📘 [View Project Report](docs/Attendance_Tracker_Project_Report.pdf)
- 📊 [View Project Presentation (PPT)](docs/Attendance_Tracker_Project_Presentation.pptx)

## 🎯 Learning Outcomes

- Understanding practical applications of Data Structures and Algorithms.
- Implementing 2D Arrays and HashMaps in Java.
- Understanding searching and sorting techniques.
- Working with Java file handling.
- Improving problem-solving and programming skills.

## 👨‍💻 Author

**Gaurav Hardiya**

B.Tech – Computer Science & Engineering (AI & ML)

Rungta International Skills University

## ⭐ Acknowledgement

This project was developed as part of the Data Structures and Algorithms (DSA) coursework.

If you find this project useful, consider giving it a ⭐ on GitHub!
