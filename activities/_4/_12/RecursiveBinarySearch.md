# Recursion — Day 2: Binary Search

---

## Overview

Binary search is the classic "divide and conquer" algorithm. Given a **sorted** array, instead of scanning every element, you:

1. Look at the **middle** element.
2. If it's the target, you're done.
3. If the target is **smaller**, throw away the right half and search the left half.
4. If the target is **larger**, throw away the left half and search the right half.
5. Repeat until you find it — or the window is empty, meaning the target isn't there.

Every step throws away half of what's left. That's why binary search is drastically faster than linear search on large arrays.

### Performance

| Search | Worst-case comparisons for n = 1,000 | For n = 1,000,000 |
|---|---|---|
| Linear search | 1,000 | 1,000,000 |
| Binary search | ~10 | ~20 |

Binary search is `O(log n)`. Every call roughly **halves** the problem, and `log₂(1,000,000) ≈ 20`.

### Why recursion fits binary search

"Search a sorted array from `lo` to `hi`" naturally reduces to "search a sorted array from `lo` to `mid-1`" or "from `mid+1` to `hi`" — the exact same problem on a smaller window. That's the pattern recursion is built for.

---

## The Recursive Structure

```java
int binarySearch(int[] arr, int target, int lo, int hi)
```

- **Parameters** `lo` and `hi` are the inclusive bounds of the window we're still searching.
- **Initial call:** `binarySearch(arr, target, 0, arr.length - 1)`
- **Base cases:**
  - `lo > hi` → the window is empty, target is not in the array → return `-1`
  - `arr[mid] == target` → found it → return `mid`
- **Recursive calls:** search either the left half (`lo` to `mid - 1`) or the right half (`mid + 1` to `hi`), never both.

Pseudocode:

```
binarySearch(arr, target, lo, hi):
    if lo > hi:
        return -1                                 // base case: empty window
    mid = (lo + hi) / 2
    if arr[mid] == target:
        return mid                                // base case: found
    if arr[mid] < target:
        return binarySearch(arr, target, mid+1, hi)   // search right
    else:
        return binarySearch(arr, target, lo, mid-1)   // search left
```

### Wrapper method

Callers shouldn't have to know about `lo` and `hi`. Wrap the recursive method:

```java
public static int search(int[] arr, int target) {
    return binarySearch(arr, target, 0, arr.length - 1);
}
```

This is a common pattern in recursive code: a small public method that sets up the initial state, plus a recursive "helper" that does the actual work.

---

## Activity

Open `RecursiveBinarySearch.java`. Fill in `binarySearch`. The `search` wrapper is already written — leave it alone.

---

## Level 1 🌱 — Trace by Hand First

Sorted array:

```
index:  0  1  2  3  4  5   6   7   8   9
arr:   [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]
```

For each target, write down the `lo`, `hi`, and `mid` at each recursive call until the method returns.

1. Find `14`
2. Find `2`
3. Find `20`
4. Find `5` (not in array)
5. Find `13` (not in array)

**Think:** what's the maximum number of recursive calls before any target is resolved? Compare that to the array length.

---

## Level 2 🌿 — Implement It

Fill in:

```java
public static int binarySearch(int[] arr, int target, int lo, int hi)
```

Use the pseudocode above as a starting point, but **write it yourself** — don't copy the demo.

**Checklist before writing code:**

- What two base cases do you need?
- How do you compute `mid`?
- After checking `arr[mid]`, what's the new `lo` and new `hi` for each half?
- What value should the recursive call return (and what do you do with that value)?

Test with the array from Level 1:

```java
int[] sorted = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
System.out.println(search(sorted, 14));  // 6
System.out.println(search(sorted, 2));   // 0
System.out.println(search(sorted, 20));  // 9
System.out.println(search(sorted, 5));   // -1
System.out.println(search(sorted, 100)); // -1
```

---

## Level 3 🌳 — Add a Trace

Make a copy of your method called `binarySearchTrace(int[] arr, int target, int lo, int hi)`. At the top of each call, print:

```
lo=X hi=Y mid=Z arr[mid]=V
```

Run it for targets `14`, `5`, and `20`. Do the printed values match the traces you did by hand in Level 1?

---

## Level 4 🌲 — Linear vs Binary Race

Write a method that generates a sorted `int[]` of size `n` (just fill with `i * 2`, or any sorted pattern). Then:

1. Use `System.nanoTime()` to time 1,000 searches with **linear search** (you can copy from Lesson 10).
2. Use `System.nanoTime()` to time 1,000 searches with your recursive binary search.
3. Try `n = 1,000`, `n = 100,000`, `n = 10,000,000`.

How does the gap between the two grow as `n` grows?

**Think:** binary search only works on **sorted** data. Is it still a win if the array isn't already sorted and you'd have to sort it first?

---

## Level 5 🌴 — Extensions

Pick one or more.

### A) First Occurrence

Modify binary search so that when the array has duplicates, it returns the index of the **first** occurrence of `target`.

- `{1, 2, 2, 2, 3}`, target `2` → `1` (not `2` or `3`)

**Hint:** when you find a match, don't return immediately — keep searching the left half for an earlier match.

### B) `contains` Method

Write `public static boolean contains(int[] arr, int target)` that uses your recursive binary search internally. Return `true` if the target is present.

### C) Generic Binary Search on Strings

Write a binary search that works on a sorted `String[]`. Use `.compareTo()` instead of `<` / `>` / `==`. Test it against a sorted list of names.

### D) Insertion Point

Instead of returning `-1` when the target is missing, return the **index where it would be inserted** to keep the array sorted.

- `{1, 3, 5, 7}`, target `4` → `2` (would go between 3 and 5)
- `{1, 3, 5, 7}`, target `0` → `0`
- `{1, 3, 5, 7}`, target `9` → `4`

---

## Recap

From the last two lessons you should now be able to:

- Identify base case(s) and recursive call(s) in any recursive method
- Trace a recursive call stack by hand
- Write simple recursive methods (factorial, Fibonacci, string reverse, palindrome)
- Implement and reason about recursive binary search
- Explain why binary search is `O(log n)` and when it applies

These same patterns — "solve the base case, shrink the problem, combine the result" — come back in AP CS A free-response problems and in every data-structures class you'll take after this.
