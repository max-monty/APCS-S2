package demos._4._7;

public class TwoDArrayBasics {
    public static void main(String[] args) {
        // Creating with new — 3 rows, 4 columns, all 0
        int[][] grid = new int[3][4];

        // Dimensions
        System.out.println("Rows: " + grid.length);        // 3
        System.out.println("Cols: " + grid[0].length);      // 4

        // Access and modify
        grid[0][0] = 1;
        grid[2][3] = 99;
        System.out.println(grid[0][0]);   // 1
        System.out.println(grid[2][3]);   // 99
        System.out.println(grid[1][1]);   // 0 (default)

        // Creating with initializer list
        int[][] table = {
            {1, 2, 3},
            {4, 5, 6}
        };
        System.out.println(table[1][2]);  // 6

        // A row is just a 1D array
        int[] firstRow = table[0];
        System.out.println(firstRow.length);  // 3

        // Print the grid
        for (int r = 0; r < table.length; r++) {
            for (int c = 0; c < table[0].length; c++) {
                System.out.print(table[r][c] + " ");
            }
            System.out.println();
        }
    }
}
