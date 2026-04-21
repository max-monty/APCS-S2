package activities._4._11;

public class RecursionChallenges {
    public static void main(String[] args) {

        // test your methods here

    }

    // Return the nth Fibonacci number (0-indexed).
    //   fib(0) = 0
    //   fib(1) = 1
    //   fib(n) = fib(n-1) + fib(n-2)   for n >= 2
    // Expected:
    //   fib(0) -> 0
    //   fib(1) -> 1
    //   fib(6) -> 8
    //   fib(10) -> 55
    public static int fib(int n) {
        return 0;
    }

    // Return the string s reversed.
    // Hint: think about "first character" + reverse(rest of string)
    //   OR reverse(all but last) prepended with the last character.
    // Expected:
    //   reverse("")       -> ""
    //   reverse("a")      -> "a"
    //   reverse("hello")  -> "olleh"
    //   reverse("racecar")-> "racecar"
    public static String reverse(String s) {
        return "";
    }

    // CHALLENGE
    // Return true if s reads the same forwards and backwards.
    // Constraint: solve this recursively WITHOUT reversing the whole string.
    // Hint: compare s's first and last characters, then recurse on the middle.
    // Expected:
    //   isPalindrome("")        -> true
    //   isPalindrome("a")       -> true
    //   isPalindrome("racecar") -> true
    //   isPalindrome("hello")   -> false
    public static boolean isPalindrome(String s) {
        return false;
    }
}
