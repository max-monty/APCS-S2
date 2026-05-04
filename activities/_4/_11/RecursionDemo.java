package activities._4._11;

public class RecursionDemo {
    public static void main(String[] args) {
        System.out.println(fib(24));
    }

    public static int fib(int n) {
        // if base case return ?
        // otherwise recursive calls
        if(n <= 1) return n;
        return fib(n-1) + fib(n-2);
    }

    public static int factorial(int n) {
        if(n == 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }



}
