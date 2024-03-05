//package SRP;

import java.io.*;
import java.util.*;

class Student {
    String name;
    int rollNumber;
    int semester;
    int[] marks = new int[5];

    public Student(String name, int rollNumber, int semester, int[] marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.semester = semester;
        this.marks = marks;
    }
}

public class StudentResult {

    public static void main(String[] args) {
        HashMap<Integer, Student> students = new HashMap<>();

        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\jithe\\eclipse-workspace\\StudentResultProcessor\\src\\SRP\\input.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                int rollNumber = Integer.parseInt(data[1]);
                int semester = Integer.parseInt(data[2]);
                int[] marks = new int[5];
                for (int i = 0; i < 5; i++) {
                    marks[i] = Integer.parseInt(data[i + 3]);
                }
                students.put(rollNumber, new Student(name, rollNumber, semester, marks));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return; 
        }

        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\jithe\\eclipse-workspace\\StudentResultProcessor\\src\\SRP\\output.txt"));
            for (Map.Entry<Integer, Student> entry : students.entrySet()) {
                Student student = entry.getValue();
                boolean fail = false;
                String failedSubjects = "";
                for (int i = 0; i < 5; i++) {
                    if (student.marks[i] < 40) {
                        fail = true;
                        failedSubjects += "Subject " + (i + 1) + ", ";
                    }
                }
                if (fail) {
                    writer.write("Roll Number: " + student.rollNumber + " - Failed. Failed Subjects: " + failedSubjects + "\n");
                } else {
                    writer.write("Roll Number: " + student.rollNumber + " - Passed\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        if (args.length == 1) {
            int rollNumberToCheck = Integer.parseInt(args[0]);
            Student student = students.get(rollNumberToCheck);
            if (student != null) {
                boolean fail = false;
                for (int i = 0; i < 5; i++) {
                    if (student.marks[i] < 40) {
                        fail = true;
                        break;
                    }
                }
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
                    if (fail) {
                        writer.write("Roll Number: " + student.rollNumber + " - Failed\n");
                    } else {
                        writer.write("Roll Number: " + student.rollNumber + " - Passed\n");
                    }
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Student with given roll number not found.");
            }
        }
    }
}
