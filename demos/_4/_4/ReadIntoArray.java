package demos._4._4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadIntoArray {
    public static void main(String[] args) throws IOException {
        Scanner data = new Scanner(new File("demos/_4/_4/temps.txt"));
        int n = data.nextInt();
        double[] temps = new double[n];
        for (int i = 0; i < n; i++) {
            temps[i] = data.nextDouble();
        }
        data.close();

        // find the max (array algorithm review)
        double max = temps[0];
        for (double t : temps) {
            if (t > max) max = t;
        }
        System.out.println("Highest temp: " + max);
    }
}
