package demos._4._7;

public class TwoDArrayAlgorithms {
    public static void main(String[] args) {
        int[][] grades = {
            {88, 92, 75, 90},   // student 0
            {95, 85, 100, 78},  // student 1
            {70, 82, 88, 91}    // student 2
        };

        // Sum all elements
        int total = 0;
        for (int[] row : grades) {
            for (int g : row) {
                total += g;
            }
        }
        System.out.println("Total: " + total);

        // Average of a single row (student 1)
        int rowSum = 0;
        for (int c = 0; c < grades[0].length; c++) {
            rowSum += grades[1][c];
        }
        double rowAvg = (double) rowSum / grades[0].length;
        System.out.println("Student 1 avg: " + rowAvg);

        // Max in a column (assignment 2)
        int colMax = grades[0][2];
        for (int r = 1; r < grades.length; r++) {
            if (grades[r][2] > colMax) {
                colMax = grades[r][2];
            }
        }
        System.out.println("Max on assignment 2: " + colMax);

        // Count values above 90 in entire grid
        int count = 0;
        for (int[] row : grades) {
            for (int g : row) {
                if (g > 90) count++;
            }
        }
        System.out.println("Grades above 90: " + count);

        // Find position of maximum
        int maxR = 0, maxC = 0;
        for (int r = 0; r < grades.length; r++) {
            for (int c = 0; c < grades[0].length; c++) {
                if (grades[r][c] > grades[maxR][maxC]) {
                    maxR = r;
                    maxC = c;
                }
            }
        }
        System.out.println("Highest grade: " + grades[maxR][maxC]
            + " at [" + maxR + "][" + maxC + "]");
    }
}
