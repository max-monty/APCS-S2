package Demos.Input;

import java.util.Scanner;

public class InputClassDemo {

public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.println("Hey, can you tell your name? ");

    String name = input.nextLine();

    System.out.println("Cool. Your name has " + name.length() + " characters!");




}
    
}
