package miniquizzes._2;

public class _6 {
    public static void main(String[] args) {

        // PART 1: What does this print?
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 2; j++) {
                System.out.print(i + "" + j + " ");
            }
        }

        System.out.println();

        // PART 2: How many times does "X" print?
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                System.out.print("X");
            }
        }

        System.out.println();

        // PART 3: What pattern does this print?
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
