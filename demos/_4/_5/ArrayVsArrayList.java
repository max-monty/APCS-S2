package demos._4._5;

import java.util.ArrayList;

public class ArrayVsArrayList {
    public static void main(String[] args) {

        // === ARRAY: fixed size, declared up front ===
        String[] arrNames = new String[3];
        arrNames[0] = "Alice";
        arrNames[1] = "Bob";
        arrNames[2] = "Charlie";
        // arrNames[3] = "Nope"; // crash! ArrayIndexOutOfBoundsException

        System.out.println(arrNames.length);  // 3 (attribute)
        System.out.println(arrNames[1]);      // Bob (bracket access)

        // === ARRAYLIST: grows as needed ===
        ArrayList<String> listNames = new ArrayList<String>();
        listNames.add("Alice");
        listNames.add("Bob");
        listNames.add("Charlie");
        listNames.add("Diana"); // no problem — it grows

        System.out.println(listNames.size());  // 4 (method)
        System.out.println(listNames.get(1));  // Bob (method access)

        // === Key differences ===
        // Array:     String[]     | .length   | arr[i]     | arr[i] = x    | fixed size
        // ArrayList: ArrayList<>  | .size()   | .get(i)    | .set(i, x)    | grows/shrinks

        // === When to use which ===
        // Array:     you know the size ahead of time, or need primitives
        // ArrayList: size is unknown or changes (add/remove during program)
    }
}
