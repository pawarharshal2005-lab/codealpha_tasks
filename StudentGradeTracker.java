package com.codealpha.studentgradetracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CodeAlpha Internship Project: Student Grade Tracker
 * Console-based Java application to store student marks and display a report.
 */
public class StudentGradeTracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<Student>();

        System.out.println("====================================");
        System.out.println("       STUDENT GRADE TRACKER");
        System.out.println("====================================");

        int studentCount = readPositiveInteger(scanner, "Enter number of students: ");

        for (int i = 1; i <= studentCount; i++) {
            System.out.println("\nEnter details for student " + i + ":");

            System.out.print("Student name: ");
            String name = scanner.nextLine().trim();
            while (name.isEmpty()) {
                System.out.print("Name cannot be empty. Enter student name: ");
                name = scanner.nextLine().trim();
            }

            int marks = readMarks(scanner, "Marks (0-100): ");
            students.add(new Student(name, marks));
        }

        displayReport(students);
        scanner.close();
    }

    private static int readPositiveInteger(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Show the validation message below.
            }

            System.out.println("Please enter a whole number greater than 0.");
        }
    }

    private static int readMarks(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int marks = Integer.parseInt(input);
                if (marks >= 0 && marks <= 100) {
                    return marks;
                }
            } catch (NumberFormatException ignored) {
                // Show the validation message below.
            }

            System.out.println("Invalid marks. Enter a whole number from 0 to 100.");
        }
    }

    private static void displayReport(List<Student> students) {
        int total = 0;
        Student highest = students.get(0);
        Student lowest = students.get(0);

        System.out.println("\n\n================ STUDENT REPORT ================");
        System.out.printf("%-5s %-25s %-10s %-10s%n", "No.", "Student Name", "Marks", "Grade");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            total += student.getMarks();

            if (student.getMarks() > highest.getMarks()) {
                highest = student;
            }
            if (student.getMarks() < lowest.getMarks()) {
                lowest = student;
            }

            System.out.printf("%-5d %-25s %-10d %-10s%n",
                    i + 1, student.getName(), student.getMarks(), student.getGrade());
        }

        double average = (double) total / students.size();

        System.out.println("------------------------------------------------");
        System.out.printf("Average marks : %.2f / 100%n", average);
        System.out.println("Highest marks : " + highest.getMarks() + " (" + highest.getName() + ")");
        System.out.println("Lowest marks  : " + lowest.getMarks() + " (" + lowest.getName() + ")");
        System.out.println("Total students: " + students.size());
        System.out.println("================================================");
    }
}
