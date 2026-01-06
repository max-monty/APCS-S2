package Demos;

import java.util.Scanner;

public class Input_and_APIs {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        // BASIC SCANNER //

        System.out.print("What is your name? ");
        String name = userInput.nextLine();
        name = name.toUpperCase();
        System.out.println("HI, " + name + ". I AM COMPUTER!");

        // NEWLINE TRAP //

        System.out.print("How old are you? ");
        int a = userInput.nextInt();
        // userInput.nextLine(); 
        System.out.print("What is your name? ");
        String n = userInput.nextLine();
        System.out.println("USER: " + n.toUpperCase() + " AGE: " + a);

        // APIs // 

        // String
        String s = "test";
        s.contains("x");

        // Math
        int x = 4;
        double y = Math.pow(3, x);


        userInput.close();


    }

}
