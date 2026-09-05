import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class StudentGradeSystem {

    static Scanner input = new Scanner(System.in);

    static String[] studentNames = new String[50];
    static String[] studentIDs = new String[50];
    static double[][] marks = new double[50][3];
    static double[] totals = new double[50];
    static double[] averages = new double[50];
    static char[] letterGrades = new char[50];
    static String[] status = new String[50];

    static int studentCount = 0;

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("===== Student Grade Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Display Student Report Card");
            System.out.println("5. Display Class Summary");
            System.out.println("6. Find Top Student");
            System.out.println("7. Save Records to File");
            System.out.println("8. Load Records from File");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            if(choice == 1) {
                addStudent();
            } else if(choice == 2) {
                displayAllStudents();
            } else if(choice == 3) {
                searchStudent();
            } else if(choice == 4) {
                displayReportCard();
            } else if(choice == 5) {
                displayClassSummary();
            } else if(choice == 6) {
                findTopStudent();
            } else if(choice == 7) {
                saveToFile();
            } else if(choice == 8) {
                loadFromFile();
            } else if(choice == 9) {
                System.out.println("Exiting program...");
            } else {
                System.out.println("Invalid choice");
            }

        } while(choice != 9);
    }

    public static void addStudent() {
        if(studentCount >= 50) {
            System.out.println("Student list is full");
            return;
        }

        input.nextLine();

        System.out.print("Enter student ID: ");
        studentIDs[studentCount] = input.nextLine();

        System.out.print("Enter student name: ");
        studentNames[studentCount] = input.nextLine();

        System.out.print("Enter Subject 1 mark: ");
        marks[studentCount][0] = input.nextDouble();

        System.out.print("Enter Subject 2 mark: ");
        marks[studentCount][1] = input.nextDouble();

        System.out.print("Enter Subject 3 mark: ");
        marks[studentCount][2] = input.nextDouble();

        totals[studentCount] = calculateTotal(studentCount);
        averages[studentCount] = calculateAverage(studentCount);
        letterGrades[studentCount] = assignGrade(averages[studentCount]);
        status[studentCount] = assignStatus(averages[studentCount]);

        studentCount++;

        System.out.println("Student added successfully");
    }

    public static void displayAllStudents() {
        if(studentCount == 0) {
            System.out.println("No students found");
            return;
        }

        System.out.println("===== All Students =====");

        for(int i = 0; i < studentCount; i++) {
            System.out.println("Student ID: " + studentIDs[i]);
            System.out.println("Student Name: " + studentNames[i]);
            System.out.println("Subject 1: " + marks[i][0]);
            System.out.println("Subject 2: " + marks[i][1]);
            System.out.println("Subject 3: " + marks[i][2]);
            System.out.println("Total: " + totals[i]);
            System.out.println("Average: " + averages[i]);
            System.out.println("Grade: " + letterGrades[i]);
            System.out.println("Status: " + status[i]);
            System.out.println("-------------------------");
        }
    }

    public static void searchStudent() {
        if(studentCount == 0) {
            System.out.println("No students found");
            return;
        }

        input.nextLine();

        System.out.print("Enter student ID or name to search: ");
        String search = input.nextLine();

        int found = -1;

        for(int i = 0; i < studentCount; i++) {
            if(studentIDs[i].equals(search) || studentNames[i].equalsIgnoreCase(search)) {
                found = i;
                break;
            }
        }

        if(found != -1) {
            System.out.println("Student found");
            System.out.println("Student ID: " + studentIDs[found]);
            System.out.println("Student Name: " + studentNames[found]);
            System.out.println("Subject 1: " + marks[found][0]);
            System.out.println("Subject 2: " + marks[found][1]);
            System.out.println("Subject 3: " + marks[found][2]);
            System.out.println("Total: " + totals[found]);
            System.out.println("Average: " + averages[found]);
            System.out.println("Grade: " + letterGrades[found]);
            System.out.println("Status: " + status[found]);
        } else {
            System.out.println("Student not found");
        }
    }

    public static void displayReportCard() {
        if(studentCount == 0) {
            System.out.println("No students found");
            return;
        }

        input.nextLine();

        System.out.print("Enter student ID to display report card: ");
        String id = input.nextLine();

        int found = -1;

        for(int i = 0; i < studentCount; i++) {
            if(studentIDs[i].equals(id)) {
                found = i;
                break;
            }
        }

        if(found != -1) {
            System.out.println("===== Student Report Card =====");
            System.out.println("Student ID: " + studentIDs[found]);
            System.out.println("Student Name: " + studentNames[found]);
            System.out.println("Subject 1: " + marks[found][0]);
            System.out.println("Subject 2: " + marks[found][1]);
            System.out.println("Subject 3: " + marks[found][2]);
            System.out.println("Total: " + totals[found]);
            System.out.println("Average: " + averages[found]);
            System.out.println("Grade: " + letterGrades[found]);
            System.out.println("Status: " + status[found]);
            System.out.println("===============================");
        } else {
            System.out.println("Student not found");
        }
    }

    public static void displayClassSummary() {
        if(studentCount == 0) {
            System.out.println("No students found");
            return;
        }

        double sumAverage = 0;
        double highestAverage = averages[0];
        double lowestAverage = averages[0];

        int passCount = 0;
        int failCount = 0;

        for(int i = 0; i < studentCount; i++) {
            sumAverage += averages[i];

            if(averages[i] > highestAverage) {
                highestAverage = averages[i];
            }

            if(averages[i] < lowestAverage) {
                lowestAverage = averages[i];
            }

            if(status[i].equals("Pass")) {
                passCount++;
            } else {
                failCount++;
            }
        }

        double classAverage = sumAverage / studentCount;

        System.out.println("===== Class Summary =====");
        System.out.println("Number of Students: " + studentCount);
        System.out.println("Class Average: " + classAverage);
        System.out.println("Highest Average: " + highestAverage);
        System.out.println("Lowest Average: " + lowestAverage);
        System.out.println("Passed Students: " + passCount);
        System.out.println("Failed Students: " + failCount);
        System.out.println("=========================");
    }

    public static void findTopStudent() {
        if(studentCount == 0) {
            System.out.println("No students found");
            return;
        }

        int topIndex = 0;

        for(int i = 1; i < studentCount; i++) {
            if(averages[i] > averages[topIndex]) {
                topIndex = i;
            }
        }

        System.out.println("===== Top Student =====");
        System.out.println("Student ID: " + studentIDs[topIndex]);
        System.out.println("Student Name: " + studentNames[topIndex]);
        System.out.println("Subject 1: " + marks[topIndex][0]);
        System.out.println("Subject 2: " + marks[topIndex][1]);
        System.out.println("Subject 3: " + marks[topIndex][2]);
        System.out.println("Total: " + totals[topIndex]);
        System.out.println("Average: " + averages[topIndex]);
        System.out.println("Grade: " + letterGrades[topIndex]);
        System.out.println("Status: " + status[topIndex]);
        System.out.println("=======================");
    }

    public static void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter(new File("students.txt"));

            writer.println(studentCount);

            for(int i = 0; i < studentCount; i++) {
                writer.println(studentIDs[i]);
                writer.println(studentNames[i]);
                writer.println(marks[i][0]);
                writer.println(marks[i][1]);
                writer.println(marks[i][2]);
                writer.println(totals[i]);
                writer.println(averages[i]);
                writer.println(letterGrades[i]);
                writer.println(status[i]);
            }

            writer.close();

            System.out.println("Records saved successfully");
        } catch(Exception e) {
            System.out.println("Error saving records");
        }
    }

    public static void loadFromFile() {
        try {
            Scanner fileInput = new Scanner(new File("students.txt"));

            studentCount = fileInput.nextInt();
            fileInput.nextLine();

            for(int i = 0; i < studentCount; i++) {
                studentIDs[i] = fileInput.nextLine();
                studentNames[i] = fileInput.nextLine();

                marks[i][0] = fileInput.nextDouble();
                marks[i][1] = fileInput.nextDouble();
                marks[i][2] = fileInput.nextDouble();

                totals[i] = fileInput.nextDouble();
                averages[i] = fileInput.nextDouble();

                letterGrades[i] = fileInput.next().charAt(0);
                fileInput.nextLine();

                status[i] = fileInput.nextLine();
            }

            fileInput.close();

            System.out.println("Records loaded successfully");
        } catch(Exception e) {
            System.out.println("Error loading records");
        }
    }

    public static double calculateTotal(int index) {
        double total = 0;

        for(int i = 0; i < 3; i++) {
            total += marks[index][i];
        }

        return total;
    }

    public static double calculateAverage(int index) {
        return totals[index] / 3;
    }

    public static char assignGrade(double average) {
        if(average >= 90) {
            return 'A';
        } else if(average >= 80) {
            return 'B';
        } else if(average >= 70) {
            return 'C';
        } else if(average >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static String assignStatus(double average) {
        if(average >= 60) {
            return "Pass";
        } else {
            return "Fail";
        }
    }
}