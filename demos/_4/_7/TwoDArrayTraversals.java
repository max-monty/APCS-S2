package demos._4._7;

public class TwoDArrayTraversals {
    public static void main(String[] args) {
        int[][] grid = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        // Row-major order: 1 2 3 4 5 6 7 8 9 10 11 12
        System.out.println("Row-major:");
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                System.out.print(grid[r][c] + " ");
            }
        }
        System.out.println();

        // Column-major order: 1 5 9 2 6 10 3 7 11 4 8 12
        System.out.println("Column-major:");
        for (int c = 0; c < grid[0].length; c++) {
            for (int r = 0; r < grid.length; r++) {
                System.out.print(grid[r][c] + " ");
            }
        }
        System.out.println();

        // Enhanced for (read-only, always row-major)
        System.out.println("Enhanced for:");
        for (int[] row : grid) {
            for (int val : row) {
                System.out.print(val + " ");
            }
        }
        System.out.println();

        // Process a single row (row 1)
        System.out.print("Row 1: ");
        for (int c = 0; c < grid[0].length; c++) {
            System.out.print(grid[1][c] + " ");
        }
        System.out.println();

        // Process a single column (col 2)
        System.out.print("Col 2: ");
        for (int r = 0; r < grid.length; r++) {
            System.out.print(grid[r][2] + " ");
        }
        System.out.println();
    }
}
