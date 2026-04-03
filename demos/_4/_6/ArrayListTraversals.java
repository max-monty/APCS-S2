package demos._4._6;

import java.util.ArrayList;

public class ArrayListTraversals {
    public static void main(String[] args) {

        ArrayList<String> words = new ArrayList<String>();
        words.add("ant");
        words.add("bat");
        words.add("cat");
        words.add("dog");

        // Indexed for loop
        for (int i = 0; i < words.size(); i++) {
            System.out.print(words.get(i) + " ");
        }
        System.out.println();

        // Enhanced for loop
        for (String w : words) {
            System.out.print(w.toUpperCase() + " ");
        }
        System.out.println();

        // === DANGER: removing during forward traversal ===
        ArrayList<String> nums = new ArrayList<String>();
        nums.add("a"); nums.add("b"); nums.add("c"); nums.add("d");

        // This SKIPS elements:
        // for (int i = 0; i < nums.size(); i++) {
        //     nums.remove(i);  // after remove, everything shifts left
        // }

        // Fix: traverse backwards
        for (int i = nums.size() - 1; i >= 0; i--) {
            nums.remove(i);
        }
        System.out.println(nums.size()); // 0

        // === DANGER: modifying during enhanced for ===
        // This throws ConcurrentModificationException:
        // for (String w : words) {
        //     words.remove(w);  // can't modify list during enhanced for
        // }
    }
}
