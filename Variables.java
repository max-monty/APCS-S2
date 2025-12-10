public class Main {
    public static void main(String[] args) {
        double x = 2.50; // not float anymore 

        System.out.print("Hello "); // stays on same line
        System.out.println("world!"); // moves to a new line

        // --- String literals + escape sequences ---
        System.out.println("Quote: \"Java rocks!\"");
        System.out.println("Backslash: C:\\Users\\Max\\notes.txt");
        System.out.println("Line1\nLine2\nLine3");

        // --- String + number concatenation ---
        int year = 2025;
        String msg = "Exam year: " + year; // numeric → String via concatenation
        System.out.println(msg);

        // --- Range/overflow demo ---
        int big = Integer.MAX_VALUE; // largest int
        System.out.println("MAX = " + big);
        System.out.println("MAX + 1 = " + (big + 1)); // overflow wraps (not what you expect)
    }
}
