package demos._4._11;

public class RecursionBasics {
    public static void main(String[] args) {

        // === FACTORIAL ===
        // n! = n * (n-1) * (n-2) * ... * 1
        // Recursive definition: n! = n * (n-1)!
        System.out.println("=== FACTORIAL ===");
        System.out.println("0! = " + factorial(0));   // 1
        System.out.println("1! = " + factorial(1));   // 1
        System.out.println("5! = " + factorial(5));   // 120
        System.out.println("7! = " + factorial(7));   // 5040

        // === COUNTDOWN ===
        // Simple way to watch the call stack build up and unwind.
        System.out.println("\n=== COUNTDOWN ===");
        countdown(5);

        // === VISUALIZE THE STACK ===
        // Shows exactly when each call starts and when it returns.
        System.out.println("\n=== TRACE FACTORIAL(4) ===");
        factorialTrace(4, 0);
    }

    // RECURSIVE METHOD
    //   BASE CASE: n <= 1 -> return 1
    //     (stops the recursion; must be reached eventually)
    //   RECURSIVE CALL: factorial(n - 1)
    //     (same method, but on a SMALLER input that gets closer to the base case)
    public static int factorial(int n) {
        if (n <= 1) {
            return 1;                       // base case
        }
        return n * factorial(n - 1);        // recursive call
    }

    // Print numbers from n down to 1, then "Blast off!"
    public static void countdown(int n) {
        if (n == 0) {                       // base case
            System.out.println("Blast off!");
            return;
        }
        System.out.println(n);
        countdown(n - 1);                   // recursive call
    }

    // Same as factorial() but prints an indented trace so students can see
    // the "call going down" and "return coming back up" halves of recursion.
    public static int factorialTrace(int n, int depth) {
        String pad = "  ".repeat(depth);
        System.out.println(pad + "-> factorialTrace(" + n + ")");
        int result;
        if (n <= 1) {
            result = 1;
        } else {
            result = n * factorialTrace(n - 1, depth + 1);
        }
        System.out.println(pad + "<- returns " + result);
        return result;
    }
}
