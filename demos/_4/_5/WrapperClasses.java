package demos._4._5;

public class WrapperClasses {
    public static void main(String[] args) {

        // int <-> Integer (autoboxing / unboxing)
        Integer a = 5;           // autoboxing: int -> Integer
        int b = a;               // unboxing: Integer -> int
        System.out.println(a + b); // 10

        // double <-> Double
        Double x = 3.14;
        double y = x;

        // Why this matters: ArrayList can't hold int, only Integer
        // ArrayList<int> broken;         // won't compile
        // ArrayList<Integer> works;      // this is fine

        // Parsing strings into numbers
        String s1 = "42";
        int num = Integer.parseInt(s1);         // "42" -> 42
        System.out.println(num + 8);            // 50

        String s2 = "3.14";
        double val = Double.parseDouble(s2);    // "3.14" -> 3.14
        System.out.println(val * 2);            // 6.28

        // This is how you'll convert file data read as strings
        String line = "Alice 92";
        String[] parts = line.split(" ");
        String name = parts[0];                 // "Alice"
        int score = Integer.parseInt(parts[1]); // 92
        System.out.println(name + ": " + score);
    }
}
