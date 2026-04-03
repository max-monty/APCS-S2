# ArrayList Algorithms — Exercises

---

## Warm-Up: Predict the Output

For each snippet, predict what prints **before** running it.

### 1)
```java
ArrayList<Integer> nums = new ArrayList<Integer>();
nums.add(10); nums.add(20); nums.add(30);
nums.remove(1);
System.out.println(nums);
System.out.println(nums.size());
```

### 2)
```java
ArrayList<String> words = new ArrayList<String>();
words.add("red"); words.add("blue"); words.add("red"); words.add("green");
for (int i = 0; i < words.size(); i++) {
    if (words.get(i).equals("red")) {
        words.remove(i);
    }
}
System.out.println(words);
```
**Why doesn't this remove both "red"s?** Fix it.

### 3)
```java
ArrayList<Integer> vals = new ArrayList<Integer>();
vals.add(5); vals.add(10); vals.add(15);
vals.add(1, 99);
vals.set(3, 0);
System.out.println(vals);
```

---

## Implement: Algorithm Patterns

### 4) Has Negative

Write a method `boolean hasNegative(ArrayList<Integer> nums)` that returns true if any element is negative.

### 5) All Passing

Write a method `boolean allPassing(ArrayList<Integer> grades)` that returns true only if every grade is >= 60.

### 6) Remove Short Words

Write a method that takes an `ArrayList<String>` and removes all words with fewer than 4 characters. Make sure no words are skipped.

---

## Apply: Grade Book from File

Read `grades.txt` into two parallel ArrayLists: `ArrayList<String> names` and `ArrayList<Integer> grades`. Then:

1. Print the class average
2. Print the name of the top student
3. Remove all students scoring below 60 and print the updated roster
4. Print how many students remain

### grades.txt
```
Marcus 78
Aisha 92
Dev 55
Lena 88
Tom 61
Sara 45
Kai 97
Nina 72
```

### Starter Code

```java
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GradeBook {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("grades.txt"));
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Integer> grades = new ArrayList<Integer>();

        while (reader.hasNextLine()) {
            String[] parts = reader.nextLine().split(" ");
            names.add(parts[0]);
            grades.add(Integer.parseInt(parts[1]));
        }
        reader.close();

        // TODO: average, top student, remove failing, print roster
    }
}
```
