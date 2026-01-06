package Demos.ClassMethods;

import java.util.Scanner;

public class ClassMethods {
    public static void main(String[] args) {
        
        // Java built-in public class (static) methods
        int x = Math.abs(-10);
        System.out.println(x);

        System.out.println();

        // Java built-in public non-static (instance)
        String s = "hello";
        s = s.toUpperCase();
        System.out.println(s);

        Scanner in = new Scanner(System.in);
        double y = in.nextDouble();

        boolean b = check100(4, 5); // Call class method from same class

        AnotherClass.iAmAPublicClassMethod(); // Call class method from another class
        
    }

    // Custom class method
    public static boolean check100(double x, double y) {
        return Math.pow(x, y) > 100;
    }

}
