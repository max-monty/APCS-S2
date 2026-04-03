# ArrayList Intro — Exercises

---

## 1) First ArrayList

Create an `ArrayList<String>` of your 5 favorite foods. Print the list, its size, and the item at index 2.

---

## 2) Translate Array to ArrayList

Rewrite this array code using an ArrayList instead:

```java
int[] scores = {88, 92, 75, 96, 84};
int sum = 0;
for (int i = 0; i < scores.length; i++) {
    sum += scores[i];
}
System.out.println("Average: " + (double) sum / scores.length);
```

What changes? What stays the same?

---

## 3) Growing a List

Start with an empty `ArrayList<Integer>`. Use a loop to add the first 20 even numbers (2, 4, 6, ... 40). Print the list and its size.

Now try doing this with a regular `int[]`. What's different about the setup?

---

## 4) Insert and Remove

Start with: `["Mon", "Tue", "Thu", "Fri"]`

Using `add(index, value)` and `remove(index)`:
1. Insert `"Wed"` in the correct position
2. Remove `"Mon"`
3. Print the result

Predict the list after each step before running.

---

## 5) Side-by-Side

For each operation, write the array version AND the ArrayList version:

| Task | Array | ArrayList |
|------|-------|-----------|
| Create with 5 elements | `int[] a = {1,2,3,4,5};` | ? |
| Get the 3rd element | `a[2]` | ? |
| Change the 1st element to 99 | `a[0] = 99;` | ? |
| Get the length/size | `a.length` | ? |
| Print all elements with a for loop | `for (int i = 0; ...)` | ? |
| Print all with enhanced for | `for (int x : a)` | ? |

---

## 6) Why ArrayList?

In the Fitness Tracker activity, you knew there were 30 days because the file told you on line 1. What if the file didn't tell you? Write a short program that reads integers from a file until there are no more, storing them in an ArrayList. Print the count and average.

Use `demos/_4/_4/scores.txt` as your test file.
