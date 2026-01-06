package Demos;

public class Strings {
    public static void main(String[] args) {

        String word1 = "computer";
        String word2 = "science";

        // length
        int length = word1.length();
        System.out.println("Length of word1: " + length);

        // substring (2 parameters)
        String firstHalf = word1.substring(0, word1.length() / 2);
        System.out.println("First half of word1: " + firstHalf);

        // substring (1 parameter)
        String fromIndex = word1.substring(3);
        System.out.println("From index 3: " + fromIndex);

        // indexOf
        int index = word1.indexOf("p");
        System.out.println("Index of 'p': " + index);

        // compareTo (lexicographical order)
        int result = word1.compareTo(word2);
        System.out.println("compareTo result: " + result);

        if (result < 0) {
            System.out.println(word1 + " comes before " + word2);
        } else if (result > 0) {
            System.out.println(word1 + " comes after " + word2);
        } else {
            System.out.println("The words are equal");
        }
    }
}
