# Minesweeper — 2D Array Challenge

**Covers: 2D Arrays, Nested Loops, Neighbor Checking, Edge Cases**

---

## Goal

Build the clue-generation engine for Minesweeper. Given a grid where `'*'` is a mine and `'.'` is empty, produce a new grid where each empty cell shows the count of neighboring mines (0-8). Mines stay as `'*'`.

This is the same logic behind every game of Minesweeper — when you click a cell and see a number, that number was computed by code exactly like what you're about to write.

---

## How It Works

Every cell has up to 8 neighbors (up, down, left, right, and 4 diagonals). Corner cells have 3 neighbors, edge cells have 5, and interior cells have 8. For each empty cell, count how many of its neighbors are mines.

```
Neighbor offsets:
(-1,-1) (-1, 0) (-1,+1)
( 0,-1)  [cell]  ( 0,+1)
(+1,-1) (+1, 0) (+1,+1)
```

---

## Example

**Input:**
```
. . * .
. . . .
. * . .
. . . .
```

**Output:**
```
0 1 * 1
1 2 2 1
1 * 1 0
1 1 1 0
```

Cell `(0,0)` has no mine neighbors → `0`. Cell `(0,1)` has one mine neighbor at `(0,2)` → `1`. Cell `(1,1)` has mine neighbors at `(0,2)` and `(2,1)` → `2`.

---

## Steps

### 1. Set up the board

Start with one of the provided sample boards (hardcoded as a `char[][]`).

### 2. Write `inBounds`

```java
static boolean inBounds(char[][] board, int r, int c)
```

Returns `true` if `(r, c)` is a valid position in the grid. This is the key to avoiding `ArrayIndexOutOfBoundsException` when checking neighbors near edges and corners.

### 3. Write `countNeighborMines`

```java
static int countNeighborMines(char[][] board, int r, int c)
```

Check all 8 neighbors of `(r, c)`. For each one that's in bounds and contains `'*'`, increment a counter. Use a nested loop over row offsets `{-1, 0, 1}` and column offsets `{-1, 0, 1}`, skipping `(0, 0)` (the cell itself).

### 4. Write `generateClues`

```java
static char[][] generateClues(char[][] board)
```

Create a **new** `char[][]` of the same size. For each cell: if it's a mine, copy `'*'`. Otherwise, call `countNeighborMines` and store the result as a character: `(char)('0' + count)`.

### 5. Write `printBoard`

```java
static void printBoard(char[][] board)
```

Print the grid with spaces between cells and a newline after each row.

### 6. Test with all sample boards

Run your program on each sample and verify the output matches.

---

## Sample Boards

```java
// Sample 1 — 4×4
char[][] board1 = {
    {'.', '.', '*', '.'},
    {'.', '.', '.', '.'},
    {'.', '*', '.', '.'},
    {'.', '.', '.', '.'}
};

// Sample 2 — 3×3 all mines
char[][] board2 = {
    {'*', '*', '*'},
    {'*', '*', '*'},
    {'*', '*', '*'}
};

// Sample 3 — 3×3 no mines
char[][] board3 = {
    {'.', '.', '.'},
    {'.', '.', '.'},
    {'.', '.', '.'}
};

// Sample 4 — 3×5
char[][] board4 = {
    {'.', '.', '*', '.', '.'},
    {'.', '.', '.', '.', '*'},
    {'*', '.', '.', '.', '.'}
};
```

### Expected Outputs

**Sample 1:**
```
0 1 * 1
1 2 2 1
1 * 1 0
1 1 1 0
```

**Sample 2:**
```
* * *
* * *
* * *
```

**Sample 3:**
```
0 0 0
0 0 0
0 0 0
```

**Sample 4:**
```
0 1 * 2 1
1 2 1 2 *
* 1 0 1 1
```

---

## Starter Code

```java
public class Minesweeper {
    public static void main(String[] args) {
        char[][] board = {
            {'.', '.', '*', '.'},
            {'.', '.', '.', '.'},
            {'.', '*', '.', '.'},
            {'.', '.', '.', '.'}
        };

        char[][] clues = generateClues(board);
        printBoard(clues);
    }

    static boolean inBounds(char[][] board, int r, int c) {
        // TODO
        return false;
    }

    static int countNeighborMines(char[][] board, int r, int c) {
        // TODO
        return 0;
    }

    static char[][] generateClues(char[][] board) {
        // TODO
        return null;
    }

    static void printBoard(char[][] board) {
        // TODO
    }
}
```

---

## Verify Your `countNeighborMines`

Use this 3×3 board to test each cell individually:

```java
char[][] test = {
    {'.', '*', '.'},
    {'.', '.', '.'},
    {'.', '.', '*'}
};
```

| Cell | Mine Neighbors | Expected |
|------|---------------|----------|
| (0,0) | (0,1) | 1 |
| (0,2) | (0,1) | 1 |
| (1,0) | (0,1) | 1 |
| (1,1) | (0,1), (2,2) | 2 |
| (1,2) | (0,1), (2,2) | 2 |
| (2,0) | none | 0 |
| (2,1) | (2,2) | 1 |

---

## What This Reviews

| Concept | Where it shows up |
|---------|------------------|
| 2D array creation | Board and clues grids |
| Nested for loops | Traversing the grid, checking neighbors |
| Bounds checking | `inBounds` prevents crashes at edges/corners |
| Row-major traversal | `generateClues` processes every cell |
| `char` arithmetic | `(char)('0' + count)` converts int to char |
| Creating new arrays | `generateClues` returns a new grid (doesn't modify input) |
| Helper methods | Breaking the problem into `inBounds`, `countNeighborMines`, `generateClues` |

---

## Extensions

- **Random board generator:** Write a method that takes `rows`, `cols`, and `mineCount`, randomly places mines, and returns a `char[][]` board. Use `Math.random()`.
- **Read board from file:** Read a board from a text file where each line is a row of `.` and `*` characters.
- **Region labeling:** Find all connected groups of `0` cells (cells that share an edge). Two `0` cells in the same group get the same region ID. This is the logic behind the "auto-reveal" in real Minesweeper.
- **Interactive game:** Use `Scanner` to let the user click cells. If it's a mine → game over. If it's a number → reveal that cell. If it's a 0 → reveal the whole connected region.
