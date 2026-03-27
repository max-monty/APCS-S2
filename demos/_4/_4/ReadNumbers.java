package demos._4._4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadNumbers {
    public static void main(String[] args) throws IOException {
        Scanner nums = new Scanner(new File("demos/_4/_4/scores.txt"));
        int sum = 0, count = 0;
        while (nums.hasNextInt()) {
            sum += nums.nextInt();
            count++;
        }
        nums.close();
        System.out.println("Average score: " + (double) sum / count);
    }
}
