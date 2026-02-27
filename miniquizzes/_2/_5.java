package miniquizzes._2;

public class _5 {
    public static void main(String[] args) {

        String s = "Hello";

        // PART 1: What does this print?
        for (int i = 0; i < s.length(); i++) {
            System.out.print(s.charAt(i) + "-");
        }

        // PART 2: What does this print?
        String result = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            result += s.charAt(i);
        }
        System.out.println(result);

        // PART 3: Write code to count how many lowercase vowels (a,e,i,o,u)
        // are in the String str. Store in int vowelCount.
        String str = "Education"; // example: should count 5s

    }
}
