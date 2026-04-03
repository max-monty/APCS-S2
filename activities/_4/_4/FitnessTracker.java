package activities._4._4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FitnessTracker {
    
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("activities/_4/_4/fitness.txt"));

        int n = reader.nextInt();
        reader.nextLine(); // consume the rest of the line after nextInt()
        
        int[] steps = new int[n];
        int[] activeMinutes = new int[n];
        int[] calories = new int[n];

        while(reader.hasNextLine()) {
            String[] row = reader.nextLine().split(" ");
            int day = Integer.parseInt(row[0]) - 1;
            steps[day] = Integer.parseInt(row[1]);
            activeMinutes[day] = Integer.parseInt(row[2]);
            calories[day] = Integer.parseInt(row[3]);
        }

        // TODO: read data into arrays

        reader.close();

        // TODO: Part B analyses
    }
}
