# 2D Array Exercises

**Covers: AP 4.11-4.13 — 2D Array Creation, Traversals, and Algorithms**

---

## Part 1: Predict the Output

For each snippet, predict the output **before** running it.

### Snippet 1

```java
int[][] grid = new int[2][3];
grid[0][1] = 5;
grid[1][2] = 8;
System.out.println(grid.length);
System.out.println(grid[0].length);
System.out.println(grid[0][0]);
System.out.println(grid[0][1]);
```

### Snippet 2

```java
int[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
System.out.println(m[0][2]);
System.out.println(m[2][0]);
System.out.println(m[1][1]);
```

### Snippet 3 — Row-Major vs Column-Major

```java
int[][] g = {{1, 2}, {3, 4}, {5, 6}};

// What prints?
for (int c = 0; c < g[0].length; c++) {
    for (int r = 0; r < g.length; r++) {
        System.out.print(g[r][c] + " ");
    }
}
```

### Snippet 4 — Enhanced For

```java
int[][] data = {{10, 20}, {30, 40}};
int sum = 0;
for (int[] row : data) {
    for (int val : row) {
        sum += val;
    }
}
System.out.println(sum);
```

### Snippet 5 — Tricky Indexing

```java
int[][] t = {{5, 10, 15}, {20, 25, 30}};
for (int r = 0; r < t.length; r++) {
    System.out.print(t[r][r] + " ");
}
```

---

## Part 2: Short Exercises

### Exercise 1 — Create and Fill

Create a 4×5 `int[][]` where each element equals `row * col`. Print it as a grid.

Expected output:
```
0 0 0 0 0
0 1 2 3 4
0 2 4 6 8
0 3 6 9 12
```

### Exercise 2 — Row Sums

Given this array:

```java
int[][] scores = {
    {80, 90, 85},
    {70, 75, 88},
    {95, 92, 100}
};
```

Write code that prints the sum of each row:

```
Row 0: 255
Row 1: 233
Row 2: 287
```

### Exercise 3 — Column Max

Using the same `scores` array, find and print the maximum value in **each column**:

```
Col 0 max: 95
Col 1 max: 92
Col 2 max: 100
```

### Exercise 4 — Count Matches

Write a method `countAbove(int[][] grid, int threshold)` that returns the number of elements strictly greater than `threshold`.

Test with:
```java
int[][] data = {{3, 7, 2}, {8, 1, 9}, {4, 6, 5}};
System.out.println(countAbove(data, 5));  // should print 4
```

### Exercise 5 — Border Sum

Write a method `borderSum(int[][] grid)` that returns the sum of only the elements on the border (top row, bottom row, left column, right column).

Test with:
```java
int[][] g = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
System.out.println(borderSum(g));  // 1+2+3+4+6+7+8+9 = 40
```

**Hint:** an element is on the border if `r == 0`, `r == grid.length - 1`, `c == 0`, or `c == grid[0].length - 1`.

### Exercise 6 — Transpose

Write a method that returns a **new** 2D array that is the transpose of the input (rows become columns, columns become rows).

```java
int[][] original = {{1, 2, 3}, {4, 5, 6}};  // 2×3
int[][] transposed = transpose(original);     // 3×2
// transposed = {{1, 4}, {2, 5}, {3, 6}}
```

**Think about:** what are the dimensions of the new array?


### Exercise 7 — Shift

Write a method that returns a **new** 2D array where every element has been shifted one position to the right and one position down, with values wrapping around the edges.

```java
int[][] original = {{1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}};

int[][] shifted = shiftRightDown(original);
// shifted = {{9, 7, 8},
//            {3, 1, 2},
//            {6, 4, 5}}
```


---

## Part 3: Side-by-Side — 1D vs 2D

Fill in the 2D equivalent for each 1D pattern.

| Task | 1D Array | 2D Array |
|------|----------|----------|
| Create with size | `int[] a = new int[5];` | `int[][] a = new int[___][___];` |
| Number of elements | `a.length` | `a.length * ___` |
| Access element | `a[i]` | `a[___][___]` |
| Traverse all | `for (int i = 0; ...)` | `for (int r = 0; ...) { for (int c = 0; ...) { ... } }` |
| Enhanced for | `for (int x : a)` | `for (int[] row : a) { for (int x : ___) { ... } }` |
| Find max | init `max = a[0]` | init `max = a[0][0]` |
