package demos._4._5;

import java.util.ArrayList;

public class ArrayListBasics {
    public static void main(String[] args) {

        // Creation — no size needed
        ArrayList<String> names = new ArrayList<String>();

        // add — appends to end
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        System.out.println(names);        // [Alice, Bob, Charlie]
        System.out.println(names.size()); // 3

        // get — access by index (like arr[i])
        System.out.println(names.get(0)); // Alice
        System.out.println(names.get(2)); // Charlie

        // set — replace at index (like arr[i] = x)
        names.set(1, "Beatrice");
        System.out.println(names);        // [Alice, Beatrice, Charlie]

        // add at index — inserts, shifts everything right
        names.add(1, "Zara");
        System.out.println(names);        // [Alice, Zara, Beatrice, Charlie]

        // remove — removes at index, shifts everything left
        names.remove(0);
        System.out.println(names);        // [Zara, Beatrice, Charlie]

        // ArrayList of Integers (not int — wrapper class)
        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(10);  // autoboxing: 10 -> Integer(10)
        nums.add(20);
        nums.add(30);
        int first = nums.get(0); // unboxing: Integer -> int
        System.out.println(first + 5);    // 15
    }
}
