package MiniQuizzes;

public class Strings {
    public static void main(String[] args) {
        String s1 = "Hi! I love AP Computer Science!";
        
        int x = s1.indexOf("!");
        String s2 = s1.substring(0, x);
        int y = s2.length();

        System.out.println(s2 + "\nLength: " + y);

        // WHAT IS THE OUTPUT??
    }
}
