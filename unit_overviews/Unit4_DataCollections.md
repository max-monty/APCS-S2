# Unit 4: Data Collections

**AP Exam Weighting: 30-40%** | AP Topics: 4.1-4.13

---

## Conceptual Overview

Unit 4 introduces the three core data structures in AP CS A: arrays, ArrayLists, and 2D arrays. Students learn to create, traverse, and implement algorithms on each. The unit also covers reading data from text files and using wrapper classes to bridge primitives and objects. This is the highest-weighted unit on the AP exam.

---

## Key Terms

| Term | Definition |
|------|-----------|
| **Array** | An ordered, fixed-size collection of elements of the same type |
| **Element** | A single value stored in an array |
| **Index** | The position of an element (starting at 0) |
| **Length** | Number of elements; `.length` for arrays, `.size()` for ArrayList |
| **Initializer list** | Creating an array with values: `{1, 2, 3}` |
| **Default value** | The value elements get when created with `new` (0, 0.0, false, null) |
| **Traversal** | Visiting each element in a collection |
| **Indexed for loop** | `for (int i = 0; i < arr.length; i++)` |
| **Enhanced for loop** | `for (int x : arr)` — reads each element without the index |
| **Parallel arrays** | Two or more arrays where the same index corresponds to related data |
| **Linear search** | Checking elements one by one to find a target value |
| **ArrayIndexOutOfBoundsException** | Error from accessing an index outside 0 to length-1 |
| **Aliasing** | When two variables point to the same array/object in memory |
| **ArrayList** | A resizable list from `java.util` that stores object references |
| **Autoboxing** | Automatic conversion from primitive to wrapper (`int` → `Integer`) |
| **Unboxing** | Automatic conversion from wrapper to primitive (`Integer` → `int`) |
| **Wrapper class** | `Integer`, `Double` — object versions of primitives for use in ArrayList |
| **ConcurrentModificationException** | Error from modifying an ArrayList during enhanced for loop |
| **2D array** | An array of arrays; accessed with `[row][col]` |
| **Row-major order** | Traversing a 2D array row by row (outer loop = rows) |
| **Column-major order** | Traversing a 2D array column by column (outer loop = columns) |
| **File** | A `java.io` class representing a file on disk |
| **Scanner** | Used to read data from files (or keyboard) |
| **IOException** | Checked exception required when using File I/O |

---

## Topics Covered

### 4.3-4.5 — Arrays

Creation with `new` and initializer lists. Traversal with indexed for, enhanced for, and while loops. Standard algorithms: find max/min, sum/average, count, search, filter, reverse, shift, selection sort.

### 4.6 — Text Files

Reading data from files using `Scanner` and `File`. Methods: `hasNext()`, `hasNextLine()`, `hasNextInt()`, `nextLine()`, `nextInt()`, `next()`, `split()`, `close()`. Requires `import java.io.*` and `throws IOException`.

### 4.7 — Wrapper Classes

`Integer` and `Double` as object wrappers for primitives. Autoboxing/unboxing. `Integer.parseInt()`, `Double.parseDouble()`.

### 4.8-4.10 — ArrayLists

Dynamic-size lists of objects. Methods: `add()`, `get()`, `set()`, `remove()`, `size()`, `contains()`. Traversal pitfalls: remove-during-forward-traversal skips elements (fix: traverse backwards), enhanced for loop throws `ConcurrentModificationException` if modified. Same algorithm patterns as arrays but with ArrayList methods.

### 4.11-4.13 — 2D Arrays

An array of arrays. Created with `new int[rows][cols]` or nested initializer lists. Accessed with `grid[row][col]`. Dimensions: `grid.length` = rows, `grid[0].length` = cols. Traversed with nested for loops (row-major or column-major) or nested enhanced for loops. Algorithms operate on the entire grid, a single row, a single column, or a subsection.

---

## Syntax Reference

### Arrays

```java
int[] nums = {10, 20, 30};
int[] scores = new int[5];           // all 0
int first = nums[0];
nums[2] = 99;
int len = nums.length;               // attribute, NOT a method
```

### Array Traversals

```java
// Indexed for loop
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}

// Enhanced for loop (read-only)
for (int num : arr) {
    System.out.println(num);
}

// Backwards
for (int i = arr.length - 1; i >= 0; i--) {
    System.out.println(arr[i]);
}
```

### Text File I/O

```java
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

Scanner reader = new Scanner(new File("data.txt"));
while (reader.hasNextLine()) {
    String line = reader.nextLine();
}
reader.close();
```

### Wrapper Classes

```java
Integer a = 5;                       // autoboxing
int b = a;                           // unboxing
int num = Integer.parseInt("42");
double val = Double.parseDouble("3.14");
```

### ArrayLists

```java
import java.util.ArrayList;

ArrayList<String> names = new ArrayList<String>();
names.add("Alice");                  // append
names.add(0, "Bob");                 // insert at index
String s = names.get(0);            // access
names.set(1, "Charlie");            // replace
names.remove(0);                     // remove by index
int n = names.size();                // size (method, not attribute)
boolean has = names.contains("Bob"); // search
```

### ArrayList Traversal Pitfalls

```java
// BUG: remove during forward traversal skips elements
for (int i = 0; i < list.size(); i++) {
    if (condition) list.remove(i);   // skips the next element!
}

// FIX: traverse backwards
for (int i = list.size() - 1; i >= 0; i--) {
    if (condition) list.remove(i);
}

// ERROR: modifying during enhanced for
for (String s : list) {
    list.remove(s);                  // ConcurrentModificationException!
}
```

### 2D Arrays

```java
// Creation
int[][] grid = new int[3][4];       // 3 rows, 4 columns, all 0
int[][] grid = {{1, 2, 3}, {4, 5, 6}};

// Dimensions
int rows = grid.length;             // number of rows
int cols = grid[0].length;          // number of columns

// Access
int val = grid[1][2];               // row 1, col 2
grid[0][0] = 99;                    // modify

// Access a full row (1D array)
int[] row = grid[0];
```

### 2D Array Traversals

```java
// Row-major order (standard)
for (int r = 0; r < grid.length; r++) {
    for (int c = 0; c < grid[0].length; c++) {
        System.out.print(grid[r][c] + " ");
    }
    System.out.println();
}

// Column-major order
for (int c = 0; c < grid[0].length; c++) {
    for (int r = 0; r < grid.length; r++) {
        System.out.print(grid[r][c] + " ");
    }
    System.out.println();
}

// Enhanced for (read-only)
for (int[] row : grid) {
    for (int val : row) {
        System.out.print(val + " ");
    }
    System.out.println();
}
```

---

## Standard Algorithm Patterns

### Find Maximum (1D)

```java
int max = arr[0];
for (int i = 1; i < arr.length; i++) {
    if (arr[i] > max) max = arr[i];
}
```

### Sum and Average (1D)

```java
int sum = 0;
for (int num : arr) sum += num;
double avg = (double) sum / arr.length;
```

### Count Matches / Has / All (1D)

```java
int count = 0;
boolean hasNeg = false;
boolean allPass = true;
for (int num : arr) {
    if (num > 0) count++;
    if (num < 0) hasNeg = true;
    if (num < 60) allPass = false;
}
```

### Linear Search (1D)

```java
int index = -1;
for (int i = 0; i < arr.length; i++) {
    if (arr[i] == target) { index = i; break; }
}
```

### 2D Array — Sum a Row

```java
int sum = 0;
for (int c = 0; c < grid[0].length; c++) {
    sum += grid[row][c];
}
```

### 2D Array — Sum a Column

```java
int sum = 0;
for (int r = 0; r < grid.length; r++) {
    sum += grid[r][col];
}
```

### 2D Array — Find Max of Entire Grid

```java
int max = grid[0][0];
for (int r = 0; r < grid.length; r++) {
    for (int c = 0; c < grid[0].length; c++) {
        if (grid[r][c] > max) max = grid[r][c];
    }
}
```

### 2D Array — Count Matches

```java
int count = 0;
for (int[] row : grid) {
    for (int val : row) {
        if (val > 0) count++;
    }
}
```

---

## When to Use Which Loop (2D)

| Situation | Best Loop |
|-----------|-----------|
| Process all elements, no index needed | Nested enhanced for |
| Need row/col index (modify, neighbors, subsections) | Nested indexed for |
| Process a single row | Single for loop over columns |
| Process a single column | Single for loop over rows |
| Column-major order | Swap loop order (outer = cols) |

---

## Common Pitfalls

1. **Off-by-one**: Valid indices are `0` to `length - 1`
2. **`.length` vs `.length()` vs `.size()`**: Arrays use `.length`, Strings use `.length()`, ArrayLists use `.size()`
3. **Enhanced for can't modify primitives**: Assigning to the loop variable only changes the copy
4. **Integer division in averages**: Cast to `double` before dividing
5. **Find-max initialized to 0**: Use `arr[0]` instead — what if all values are negative?
6. **Aliasing**: `int[] b = a` does NOT copy — both point to the same array
7. **ArrayList remove during forward traversal**: Skips elements — traverse backwards
8. **ConcurrentModificationException**: Don't add/remove during enhanced for on ArrayList
9. **2D array dimensions swapped**: `grid.length` = rows, `grid[0].length` = cols
10. **Neighbor checks in 2D**: Always bounds-check before accessing `grid[r+1][c]` etc.

---

## AP Exam Tips

- **Question 3 (FRQ)** is always Data Analysis with ArrayList — practice traversal, removal, and multi-list algorithms
- **Question 4 (FRQ)** is always 2D Array — practice row/column/subsection traversals and neighbor-checking
- Enhanced for is preferred when you don't need the index and aren't modifying the collection
- When removing from an ArrayList in a loop, go backwards or use an indexed for loop
- For 2D arrays, practice both row-major and column-major traversals — the exam tests both
- Tracing through nested loops (predicting output) is heavily tested in multiple choice
- Know the difference between array (fixed size, bracket syntax) and ArrayList (dynamic, method calls)
