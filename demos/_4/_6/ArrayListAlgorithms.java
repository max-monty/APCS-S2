package demos._4._6;

import java.util.ArrayList;

public class ArrayListAlgorithms {
    public static void main(String[] args) {

        ArrayList<Integer> scores = new ArrayList<Integer>();
        scores.add(72); scores.add(88); scores.add(95);
        scores.add(64); scores.add(88); scores.add(55);

        // Sum and average
        int sum = 0;
        for (int s : scores) { sum += s; }
        System.out.println("Avg: " + (double) sum / scores.size());

        // Find max
        int max = scores.get(0);
        for (int s : scores) {
            if (s > max) max = s;
        }
        System.out.println("Max: " + max);

        // Count matches
        int passing = 0;
        for (int s : scores) {
            if (s >= 70) passing++;
        }
        System.out.println("Passing: " + passing);

        // Filter into new list
        ArrayList<Integer> honors = new ArrayList<Integer>();
        for (int s : scores) {
            if (s >= 90) honors.add(s);
        }
        System.out.println("Honors: " + honors);

        // Remove all failing scores (backwards!)
        for (int i = scores.size() - 1; i >= 0; i--) {
            if (scores.get(i) < 70) scores.remove(i);
        }
        System.out.println("After remove: " + scores);

        // Remove duplicates
        ArrayList<Integer> unique = new ArrayList<Integer>();
        for (int s : scores) {
            if (!unique.contains(s)) unique.add(s);
        }
        System.out.println("Unique: " + unique);
    }
}
