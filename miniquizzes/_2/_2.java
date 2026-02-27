package miniquizzes._2;

public class _2 {
    public static void main(String[] args) {
        int score = 85;
        boolean isBonusDay = true;

        // PART 1: What gets printed?
        if (score >= 90) {
            System.out.println("A");
        } else if (score >= 80) {
            System.out.println("B");
        } else {
            System.out.println("C");
        }

        // PART 2: What gets printed?
        if (score >= 80) {
            if (isBonusDay) {
                System.out.println("Bonus points!");
            }
            System.out.println("Good job");
        }
        
        // PART 3: Rewrite using a compound boolean (no nesting)
        if (score >= 80) {
            if (isBonusDay) {
                System.out.println("Bonus!");
            }
        }
    }
}
