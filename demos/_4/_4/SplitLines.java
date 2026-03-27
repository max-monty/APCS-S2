package demos._4._4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SplitLines {
    public static void main(String[] args) throws IOException {
        System.out.println();

        Scanner csv = new Scanner(new File("demos/_4/_4/students.txt"));
        double total = 0; int count = 0;
        while (csv.hasNextLine()) {
            String[] parts = csv.nextLine().split(" ");
            String name = parts[0];
            int grade = Integer.parseInt(parts[1]);
            System.out.println(name + " scored " + grade);
            total += grade;
            count++;
        }
        csv.close();

        double avg = total / count;
        System.out.println();
        System.out.println("Class Average: " + avg);
        System.out.println();
    }
}
