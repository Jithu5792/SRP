package SRP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StudentResultProcessor {

    public static void main(String[] args) {
        processStudentResults("/StudentResultProcessor/src/SRP/input.txt","./output.txt");
    }

    public static void processStudentResults(String inputFileName, String outputFileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                String name = extractValue(line, "Name:");
                String rollNumber = extractValue(line, "Rollnumber:");
                String totalMarksStr = extractValue(line, "Total:");

                boolean failed = false;
                String failedSubject = "";

                int totalMarks = 0;

                for (int i = 1; i <= 5; i++) {
                    String subjectMarksStr = extractValue(line, "S" + i + ":");
                    int subjectMarks = Integer.parseInt(subjectMarksStr);

                    totalMarks += subjectMarks;

                    if (subjectMarks < 40) {
                        failed = true;
                        failedSubject = "Subject " + i;
                        break;
                    }
                }

                String result;
                if (failed) {
                    result = "Fail (" + failedSubject + ")";
                } else {
                    result = "Pass";
                }

                String output = name + "," + rollNumber + "," + totalMarks + "," + result;
                bw.write(output);
                bw.newLine();
            }

            System.out.println("Processing completed. Results written to " + outputFileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String extractValue(String line, String prefix) {
        String str = line.substring(prefix.length(),line.length());
        return str.trim();

}

}
