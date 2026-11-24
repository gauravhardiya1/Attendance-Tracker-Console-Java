# Attendance Tracker - Java DSA Project

Console-based Java application matching the university project description. This is **not a website**.

## Files
- `Main.java` - complete single-file Java application
- `attendance.txt` - created automatically when records are saved
- `.gitignore` - ignores compiled `.class` files

## Run in VS Code
1. Extract the ZIP.
2. Open the extracted folder containing `Main.java` in VS Code.
3. Open Terminal > New Terminal.
4. Run:
   ```bash
   javac Main.java
   java Main
   ```

## Menu
1. Add Student
2. Mark Attendance
3. Display All Students
4. View Attendance Percentage
5. Sort Students by Percentage
6. Save and Exit

## DSA concepts
- 2D Array: rows = students, columns = days; 1 = Present, 0 = Absent, -1 = Not recorded.
- HashMap: maps roll number to row index.
- Searching: HashMap lookup by roll number.
- Sorting: Bubble Sort by attendance percentage, highest first.
- Methods: separate methods for each operation.
- File Handling: load and save `attendance.txt`.

Percentage = (Present days / Recorded days) * 100.

## Demo matching assignment test cases
1. Add Karan -> R101.
2. Mark R101, Day 1, P.
3. View percentage for R101 -> Present: 1/1 | 100.0%.
4. Mark attendance for R555 -> Roll number R555 not found.
5. Add Meera -> R102.
6. Mark R101, Day 2, A.
7. Mark R102, Day 2, P.
8. Sort -> R102 100.0%, then R101 50.0%.
9. Save and exit, then run again to verify records load.

## Complexity
Let N = number of students and D = 365 days.
- HashMap search: O(1) average, O(N) worst case.
- Add student: O(1) average.
- Count/percentage: O(D).
- Bubble Sort: O(N^2) comparisons; with percentage recalculation O(N^2 * D).
- File handling: proportional to saved records.

## Push to GitHub
Create an empty GitHub repository, then run these commands from this folder
(replace the URL with your repository URL):

```bash
git init
git add .
git commit -m "Add Attendance Tracker DSA project"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/Attendance-Tracker.git
git push -u origin main
```
