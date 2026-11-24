import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_STUDENTS = 100, MAX_DAYS = 365;
    static final String FILE = "attendance.txt";
    static String[] names = new String[MAX_STUDENTS], rolls = new String[MAX_STUDENTS];
    // 1 = Present, 0 = Absent, -1 = Not recorded
    static int[][] attendance = new int[MAX_STUDENTS][MAX_DAYS];
    // Maps roll number to row index in the 2D array
    static HashMap<String, Integer> rollMap = new HashMap<>();
    static int studentCount = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        for (int[] row : attendance) Arrays.fill(row, -1);
        loadData();
        System.out.println("\\n===== ATTENDANCE TRACKER =====");
        System.out.println("Students loaded: " + studentCount);
        boolean running = true;
        while (running) {
            System.out.println("\\n1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. Display All Students");
            System.out.println("4. View Attendance Percentage");
            System.out.println("5. Sort Students by Percentage");
            System.out.println("6. Save and Exit");
            int choice = readInt("Enter option: ");
            switch (choice) {
                case 1: addStudent(); break;
                case 2: markAttendance(); break;
                case 3: displayAll(); break;
                case 4: viewPercentage(); break;
                case 5: sortByPercentage(); break;
                case 6:
                    saveData();
                    System.out.println("Data saved. Goodbye!");
                    running = false;
                    break;
                default: System.out.println("Invalid option. Choose 1 to 6.");
            }
        }
    }

    static void addStudent() {
        if (studentCount == MAX_STUDENTS) {
            System.out.println("Maximum student limit reached."); return;
        }
        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) { System.out.println("Name cannot be empty."); return; }

        int next = 101;
        String roll;
        do { roll = "R" + next++; } while (rollMap.containsKey(roll));
        names[studentCount] = name;
        rolls[studentCount] = roll;
        rollMap.put(roll, studentCount++);
        saveData();
        System.out.println("Student added. Roll: " + roll + " | " + name);
    }

    static void markAttendance() {
        if (studentCount == 0) { System.out.println("Add a student first."); return; }
        int day = readInt("Enter day number (1-365): ");
        if (day < 1 || day > MAX_DAYS) { System.out.println("Invalid day."); return; }
        System.out.print("Enter roll number: ");
        String roll = sc.nextLine().trim().toUpperCase();
        Integer row = rollMap.get(roll); // HashMap search: average O(1)
        if (row == null) { System.out.println("Roll number " + roll + " not found."); return; }
        System.out.print("Enter P for Present or A for Absent: ");
        String status = sc.nextLine().trim().toUpperCase();
        if (!status.equals("P") && !status.equals("A")) {
            System.out.println("Invalid input. Only P or A is accepted."); return;
        }
        attendance[row][day - 1] = status.equals("P") ? 1 : 0;
        saveData();
        System.out.println("Attendance marked: " + rolls[row] + " | " + names[row]
                + " | Day " + day + " | " + status);
    }

    static void displayAll() {
        if (studentCount == 0) { System.out.println("No students available."); return; }
        System.out.printf("%-8s %-22s %-10s %-10s %-12s%n",
                "ROLL", "NAME", "PRESENT", "RECORDED", "PERCENTAGE");
        for (int i = 0; i < studentCount; i++)
            System.out.printf("%-8s %-22s %-10d %-10d %.1f%%%n",
                    rolls[i], names[i], presentCount(i), recordedCount(i), percentage(i));
    }

    static void viewPercentage() {
        System.out.print("Enter roll number: ");
        String roll = sc.nextLine().trim().toUpperCase();
        Integer row = rollMap.get(roll);
        if (row == null) { System.out.println("Roll number " + roll + " not found."); return; }
        System.out.printf("%s | %s | Present: %d/%d | %.1f%%%n",
                rolls[row], names[row], presentCount(row), recordedCount(row), percentage(row));
    }

    static void sortByPercentage() {
        if (studentCount == 0) { System.out.println("No students available."); return; }
        int[] order = new int[studentCount];
        for (int i = 0; i < studentCount; i++) order[i] = i;

        // Bubble Sort by percentage, descending. Original array rows are unchanged.
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                if (percentage(order[j]) < percentage(order[j + 1])) {
                    int temp = order[j]; order[j] = order[j + 1]; order[j + 1] = temp;
                }
            }
        }
        System.out.println("\\n--- Sorted by Attendance (Highest First) ---");
        for (int i : order)
            System.out.printf("%s | %s | %.1f%%%n", rolls[i], names[i], percentage(i));
    }

    static int presentCount(int row) {
        int total = 0;
        for (int value : attendance[row]) if (value == 1) total++;
        return total;
    }

    static int recordedCount(int row) {
        int total = 0;
        for (int value : attendance[row]) if (value != -1) total++;
        return total;
    }

    static double percentage(int row) {
        int recorded = recordedCount(row);
        return recorded == 0 ? 0 : presentCount(row) * 100.0 / recorded;
    }

    // File format: STUDENT|roll|name ; ATTENDANCE|roll|day|P-or-A
    static void saveData() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE))) {
            for (int i = 0; i < studentCount; i++)
                out.println("STUDENT|" + rolls[i] + "|" + names[i].replace("|", " "));
            for (int i = 0; i < studentCount; i++)
                for (int d = 0; d < MAX_DAYS; d++)
                    if (attendance[i][d] != -1)
                        out.println("ATTENDANCE|" + rolls[i] + "|" + (d + 1) + "|"
                                + (attendance[i][d] == 1 ? "P" : "A"));
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    static void loadData() {
        File file = new File(FILE);
        if (!file.exists()) return;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] p = line.split("\\\\|", 4);
                if (p.length >= 3 && p[0].equals("STUDENT") && studentCount < MAX_STUDENTS) {
                    if (!rollMap.containsKey(p[1])) {
                        rolls[studentCount] = p[1];
                        names[studentCount] = p[2];
                        rollMap.put(p[1], studentCount++);
                    }
                } else if (p.length == 4 && p[0].equals("ATTENDANCE")) {
                    Integer row = rollMap.get(p[1]);
                    try {
                        int day = Integer.parseInt(p[2]);
                        if (row != null && day >= 1 && day <= MAX_DAYS
                                && (p[3].equals("P") || p[3].equals("A")))
                            attendance[row][day - 1] = p[3].equals("P") ? 1 : 0;
                    } catch (NumberFormatException ignored) { }
                }
            }
        } catch (IOException e) {
            System.out.println("Load error: " + e.getMessage());
        }
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Enter a valid number."); }
        }
    }
}
