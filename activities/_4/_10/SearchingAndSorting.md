# Searching and Sorting

---

## Overview

Up to now, you've written a lot of "walk the array and do something" code — summing, counting, finding a max. In this lesson, we formalize three of the most important named algorithms on the AP CS A exam. They all use the traversal patterns you already know; the job is just to name them and recognize how they work step by step.

### Linear Search

Walk the array from one end to the other and check every element against a target value.

- **When to use:** the array is unsorted (or you have no reason to assume it's sorted).
- **Performance:** worst case, you look at every element — `O(n)`. Doubling the array size roughly doubles the work.
- **Variations you'll see on the AP exam:**
  - Return the **index** of the first match, or `-1` if not found.
  - Return the index of the **last** match (scan backward, or keep updating a variable as you go).
  - Return a **boolean** (does the array contain this value?).
  - **Count** how many times the value appears.

Linear search is really just the "find" traversal pattern from Unit 4.3 with a name attached to it.

### Selection Sort

Sort in place by repeatedly **selecting the smallest remaining element** and moving it to the front.

Step by step, for ascending order:

1. Find the smallest value in positions `0..n-1`. Swap it with position `0`.
2. Find the smallest value in positions `1..n-1`. Swap it with position `1`.
3. Continue until the whole array is sorted.

After pass `i`, positions `0..i` are **locked in** — they will never move again.

- **Performance:** always `O(n²)` comparisons, even if the array is already sorted.
- **Swaps:** at most `n-1` — one per pass. Good if swaps are expensive.

### Insertion Sort

Sort in place by growing a **sorted region** on the left. For each new element, slide it backward into the right spot.

Step by step, for ascending order:

1. Treat `arr[0]` as a sorted region of size 1.
2. Take `arr[1]` and insert it into the sorted region in the correct spot.
3. Take `arr[2]` and insert it, sliding larger values right to make room.
4. Continue until every element has been inserted.

- **Performance:** `O(n²)` worst case (reverse-sorted input), but `O(n)` on an already-sorted or nearly-sorted array — much faster than selection sort in that case.
- **Good for:** small arrays or data that's already mostly in order.

### Quick Comparison

| Algorithm | Idea | Best Case | Worst Case | In place? |
|-----------|------|-----------|------------|-----------|
| Linear Search | Check each element until you find target | O(1) | O(n) | n/a |
| Selection Sort | Repeatedly pick the min of the unsorted region | O(n²) | O(n²) | Yes |
| Insertion Sort | Slide each new value back into a sorted region | O(n) | O(n²) | Yes |

> **AP exam tip:** You don't need to memorize Big-O notation, but you should be able to **trace** each of these algorithms by hand and show the state of the array after each pass.

---

## Video Resources

Watch these before (or during) the activity to see the algorithms in motion:

- [Selection Sort — visualized](https://www.youtube.com/shorts/S_ND1OUk3dk)
- [Insertion Sort — visualized](https://www.youtube.com/shorts/mcABvnPT6ZA)
- [15 Sorting Algorithms in 6 Minutes](https://www.youtube.com/watch?v=kPRA0W1kECg) — bonus: see how our two sorts stack up against the other algorithms out there

---

## Activity

Open `SearchingAndSorting.java`. Three method stubs are waiting for you. Implement them one at a time and test each in `main()` before moving on.

---

## Level 1 🌱 — Trace by Hand Before You Code

Before writing any code, trace these examples on paper. Show the array after **each pass** of the outer loop.

### Trace 1 — Selection Sort

Starting array: `{5, 2, 8, 1, 9, 3}`

After pass 0: `{__, __, __, __, __, __}`
After pass 1: `{__, __, __, __, __, __}`
After pass 2: `{__, __, __, __, __, __}`
After pass 3: `{__, __, __, __, __, __}`
After pass 4: `{__, __, __, __, __, __}`

### Trace 2 — Insertion Sort

Starting array: `{5, 2, 8, 1, 9, 3}`

After inserting index 1: `{__, __, __, __, __, __}`
After inserting index 2: `{__, __, __, __, __, __}`
After inserting index 3: `{__, __, __, __, __, __}`
After inserting index 4: `{__, __, __, __, __, __}`
After inserting index 5: `{__, __, __, __, __, __}`

**Think:** on which pass did insertion sort finish "doing real work"? How does that compare with selection sort?

---

## Level 2 🌿 — Linear Search (Last Occurrence)

Fill in:

```java
int findValue(int[] arr, int val)
```

Return the **index of the last occurrence** of `val` in `arr`. Return `-1` if `val` does not appear.

Two valid approaches — pick one:

- **Scan backward** with an indexed for loop. Return as soon as you find a match.
- **Scan forward** and keep updating a variable `lastIndex` every time you see `val`. Return it at the end.

Test in `main()`:

```java
int[] a = {4, 7, 2, 7, 9, 7, 1};
System.out.println(findValue(a, 7));   // 5
System.out.println(findValue(a, 4));   // 0
System.out.println(findValue(a, 100)); // -1
```

**Think:** which approach returns as soon as possible? Which one is "cleaner" to write? Is either one faster in the worst case?

---

## Level 3 🌳 — Selection Sort

Fill in:

```java
void selectionSort(int[] arr)
```

Sort `arr` in ascending order using selection sort. Arrays are passed by reference, so you don't need to return anything — the caller's array will see the changes.

Suggested structure:

```java
for (int i = 0; i < arr.length - 1; i++) {
    // 1) find the index of the smallest value in arr[i..arr.length-1]
    // 2) swap arr[i] with that smallest value
}
```

Write a small helper in `main()` that prints the array so you can watch it change:

```java
int[] a = {5, 2, 8, 1, 9, 3};
selectionSort(a);
// expected: [1, 2, 3, 5, 8, 9]
```

**Checkpoint:** add a `System.out.println(Arrays.toString(arr));` inside the outer loop to see the array after each pass. Does it match the trace you did by hand?

---

## Level 4 🌲 — Insertion Sort

Fill in:

```java
void insertionSort(int[] arr)
```

Sort `arr` in ascending order using insertion sort.

Suggested structure:

```java
for (int i = 1; i < arr.length; i++) {
    // take arr[i] and slide it left while the element to its left is larger
    int j = i;
    while (j > 0 && arr[j - 1] > arr[j]) {
        // swap arr[j-1] and arr[j]
        j--;
    }
}
```

Test with:

```java
int[] a = {5, 2, 8, 1, 9, 3};
insertionSort(a);
// expected: [1, 2, 3, 5, 8, 9]

int[] sorted = {1, 2, 3, 4, 5};
insertionSort(sorted);
// expected: [1, 2, 3, 4, 5] — and your loop should barely do any work
```

**Think:** add a counter to count how many times the inner `while` loop runs. Compare the count for an already-sorted array vs. a reverse-sorted array.

---

## Level 5 🌴 — Extensions

Pick one or more.

### A) Compare the Two Sorts

Generate a random `int[]` of size 1000 (use `Math.random()` or `java.util.Random`). Make two copies. Time each sort with `System.nanoTime()`:

```java
long start = System.nanoTime();
selectionSort(copy1);
long elapsed = System.nanoTime() - start;
```

Which is faster on random data? Which is faster on already-sorted data? Does that match what the table in the overview predicts?

### B) Descending Sort

Add an overload:

```java
void selectionSort(int[] arr, boolean ascending)
```

When `ascending` is `false`, sort from largest to smallest. What's the minimum change needed in the algorithm?

### C) Sort Strings

Write `void insertionSort(String[] arr)` that sorts an array of strings alphabetically. Use `.compareTo()` instead of `<` (review: Strings from Unit 1).

### D) Find All Occurrences

Write `int[] findAll(int[] arr, int val)` that returns a **new** array containing **every index** where `val` appears (not just the last one). Hint: do two passes — one to count, one to fill the result array.
