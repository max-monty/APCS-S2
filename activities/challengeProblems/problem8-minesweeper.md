# Problem 8: Minesweeper Clue Generator (2D Arrays + Edge Cases)

## Overview

Minesweeper is a classic puzzle game played on a grid. Some cells contain hidden mines;
others are safe. Each safe cell displays a number (0–8) telling the player how many of
its up-to-eight neighbors contain mines. Your job is to take a raw minefield grid and
produce the completed clue grid — then go further and identify connected regions of
zero-clue cells, which form the "safe zones" that automatically reveal themselves when
a player clicks on any cell within them.

---

## Part 1: Generating Clues

### Input Format

The minefield is a `char[][]` grid containing:
- `'*'` — a mine
- `'.'` — an empty cell (not a mine)

### Output Format

A new `char[][]` where:
- `'*'` cells remain `'*'`
- `'.'` cells become `'0'`–`'8'` indicating the count of neighboring mines

"Neighbors" means all cells that share a side or a corner (up, down, left, right,
and all 4 diagonals) — up to 8 neighbors. Cells on the border of the grid have fewer
neighbors; your code must handle this without throwing an `ArrayIndexOutOfBoundsException`.

---

## Sample Input and Output

### Sample 1 — 4×4 Grid
**Input:**
```java
char[][] board = {
    {'.', '.', '*', '.'},
    {'.', '.', '.', '.'},
    {'.', '*', '.', '.'},
    {'.', '.', '.', '.'}
};
```

**Output:**
```java
char[][] expected = {
    {'0', '1', '*', '1'},
    {'1', '2', '2', '1'},
    {'1', '*', '1', '0'},
    {'1', '1', '1', '0'}
};
```

### Sample 2 — 3×3 Grid, All Mines
**Input:**
```java
char[][] board = {
    {'*', '*', '*'},
    {'*', '*', '*'},
    {'*', '*', '*'}
};
```

**Output:**
```java
char[][] expected = {
    {'*', '*', '*'},
    {'*', '*', '*'},
    {'*', '*', '*'}
};
```

### Sample 3 — 3×3 Grid, No Mines
**Input:**
```java
char[][] board = {
    {'.', '.', '.'},
    {'.', '.', '.'},
    {'.', '.', '.'}
};
```

**Output:**
```java
char[][] expected = {
    {'0', '0', '0'},
    {'0', '0', '0'},
    {'0', '0', '0'}
};
```

### Sample 4 — 3×5 Grid
**Input:**
```java
char[][] board = {
    {'.', '.', '*', '.', '.'},
    {'.', '.', '.', '.', '*'},
    {'*', '.', '.', '.', '.'}
};
```

**Output:**
```java
char[][] expected = {
    {'0', '1', '*', '2', '1'},
    {'1', '2', '1', '2', '*'},
    {'*', '1', '0', '1', '1'}
};
```

---

## Requirements

### Required Methods for Part 1

```java
// Returns a new char[][] with clue numbers filled in; mines stay as '*'
char[][] generateClues(char[][] board)

// Returns true if (r, c) is a valid position in the grid
boolean inBounds(int rows, int cols, int r, int c)

// Count mines in all 8 neighbors of cell (r, c)
int countNeighborMines(char[][] board, int r, int c)
```

### Implementation Notes

- `generateClues` should **not modify** the original `board`. Create a new `char[][]`
  and copy values over.
- In `countNeighborMines`, use a nested loop over row offsets `{-1, 0, 1}` and column
  offsets `{-1, 0, 1}`. Skip offset `(0, 0)` (the cell itself) and skip any neighbor
  that's out of bounds using `inBounds`.
- Store clue numbers as characters: `(char)('0' + count)` converts an integer `count`
  into its character representation.

---

## Part 2: Connected Zero-Region Labeling

After generating the clue grid, find all **connected regions of `'0'` cells** and
assign each region a unique integer ID. Two `'0'` cells are connected if they are
adjacent horizontally or vertically (not diagonally — this matches standard Minesweeper
reveal behavior).

### Output for Part 2

An `int[][] regions` grid of the same size as the board where:
- Cells that are `'0'` in the clue grid get a region ID (1, 2, 3, …)
- All other cells (mines or non-zero clues) get ID `0`

### Part 2 Sample 1 — Two Small Regions

Using Sample 1's clue grid:
```java
// Clue grid (from Sample 1):
char[][] clueBoard = {
    {'0', '1', '*', '1'},
    {'1', '2', '2', '1'},
    {'1', '*', '1', '0'},
    {'1', '1', '1', '0'}
};
```

**Regions output:**
```java
int[][] regions = {
    {1, 0, 0, 0},
    {0, 0, 0, 0},
    {0, 0, 0, 2},
    {0, 0, 0, 2}
};
```

Region 1 has 1 cell: `(0,0)`. Region 2 has 2 cells: `(2,3)` and `(3,3)`.

### Part 2 Sample 2 — Two Regions Separated by a Wall of Mines

**Input board:**
```java
char[][] board = {
    {'.', '.', '.', '*', '.', '.', '.'},
    {'.', '.', '.', '*', '.', '.', '.'},
    {'.', '.', '.', '*', '.', '.', '.'},
    {'.', '.', '.', '*', '.', '.', '.'},
    {'.', '.', '.', '*', '.', '.', '.'}
};
```

**Clue grid:**
```java
char[][] clueBoard = {
    {'0', '0', '2', '*', '2', '0', '0'},
    {'0', '0', '3', '*', '3', '0', '0'},
    {'0', '0', '3', '*', '3', '0', '0'},
    {'0', '0', '3', '*', '3', '0', '0'},
    {'0', '0', '2', '*', '2', '0', '0'}
};
```

**Regions:**
```java
int[][] regions = {
    {1, 1, 0, 0, 0, 2, 2},
    {1, 1, 0, 0, 0, 2, 2},
    {1, 1, 0, 0, 0, 2, 2},
    {1, 1, 0, 0, 0, 2, 2},
    {1, 1, 0, 0, 0, 2, 2}
};
```

Region 1 = left cluster of zeros; Region 2 = right cluster. The column of mines and
their neighboring clue cells (`'2'` and `'3'`) form a barrier that prevents the two
regions from connecting.

---

## Requirements for Part 2

### Required Methods

```java
// Returns int[][] where each '0' cell in clueBoard has a region ID (1, 2, 3, ...)
// Non-zero and mine cells get 0.
int[][] labelRegions(char[][] clueBoard)

// Recursive flood-fill: mark all '0' cells reachable from (r, c) with regionId
void floodFill(char[][] clueBoard, int[][] regions, int r, int c, int regionId)
```

### Flood-Fill Algorithm (Recursive)

Use recursion for flood-fill. All boards in this assignment are small enough that
recursion will not cause a stack overflow.

Think of it like pouring paint onto a tile floor: the paint lands on one `'0'` cell,
labels it, and then spreads to each of its four neighbors (up, down, left, right).
Each neighbor does the same thing — labels itself and spreads further. The paint stops
when it hits a cell that's out of bounds, already labeled, or not a `'0'`.

To write `floodFill`, you need to think about two things:

1. **Base cases:** What are the three reasons the method should stop and do nothing?
2. **Recursive step:** If this cell *is* a valid, unlabeled `'0'`, label it — then
   what four calls should you make to keep spreading?

In `labelRegions`, iterate over every cell. When you find a `'0'` with `regions[r][c] == 0`
(not yet labeled), call `floodFill` with a new `regionId`, then increment `regionId`.

---

## Part 3 (Extension): Reveal Simulation

Implement a click simulation:

```java
// Simulate clicking cell (r, c).
// If it's a mine: return false (game over).
// If it's a non-zero clue: reveal only that cell (mark it in a boolean[][] revealed).
// If it's a zero: reveal the entire connected region.
// Return true (game continues).
boolean reveal(int r, int c, char[][] clueBoard, int[][] regions, boolean[][] revealed)
```

Print the board state after each click, showing:
- Revealed cells: their clue character
- Unrevealed cells: `'?'`
- Mines (if clicked): `'*'`

### Sample Reveal Session

Using Sample 1's 4×4 board:

**Initial (all hidden):**
```java
char[][] display = {
    {'?', '?', '?', '?'},
    {'?', '?', '?', '?'},
    {'?', '?', '?', '?'},
    {'?', '?', '?', '?'}
};
```

**After click (3, 3) — a zero cell, reveals region 2: cells (2,3) and (3,3):**
```java
char[][] display = {
    {'?', '?', '?', '?'},
    {'?', '?', '?', '?'},
    {'?', '?', '?', '0'},
    {'?', '?', '?', '0'}
};
```

**After click (0, 0) — a zero cell, reveals region 1: just cell (0,0):**
```java
char[][] display = {
    {'0', '?', '?', '?'},
    {'?', '?', '?', '?'},
    {'?', '?', '?', '0'},
    {'?', '?', '?', '0'}
};
```

**After click (1, 2) — a non-zero clue, reveals just that cell:**
```java
char[][] display = {
    {'0', '?', '?', '?'},
    {'?', '?', '2', '?'},
    {'?', '?', '?', '0'},
    {'?', '?', '?', '0'}
};
```

---

## Suggested Development Order

1. Write `inBounds` — test it with a 3×3 grid.
2. Write `countNeighborMines` — verify on cell (0,0) of Sample 1 (should be 0, since
   the mine at (0,2) is not adjacent to (0,0)).
3. Write `generateClues` — print the result for Sample 1 and compare.
4. Write `floodFill` using recursion — test it on a small grid with a few connected zeros.
5. Write `labelRegions` — test on Part 2 Sample 2.
6. (Extension) Write `reveal` and the display loop.

---

## Edge Cases to Handle

- **1×1 grid with a mine:** Output `{ {'*'} }` for clues; `{ {0} }` for regions.
- **1×1 grid with no mine:** Output `{ {'0'} }` for clues; `{ {1} }` for regions
  (a single zero-cell is its own region).
- **All mines:** No zero cells, so `regions` is all 0.
- **All zeros (no mines):** The entire grid is one region.
- **Corner cells:** Only 3 neighbors. `inBounds` handles this — test it explicitly.
- **Edge cells (non-corner):** Only 5 neighbors.

---

## Test Your `countNeighborMines`

Use this 3×3 board to verify each cell:

```java
// Board:
char[][] board = {
    {'.', '*', '.'},
    {'.', '.', '.'},
    {'.', '.', '*'}
};

// Expected clues:
char[][] expected = {
    {'1', '*', '1'},
    {'1', '2', '2'},
    {'0', '1', '*'}
};
```

| Cell   | Neighbors that are mines | Expected clue |
|--------|--------------------------|---------------|
| (0,0)  | (0,1)                    | `'1'`         |
| (0,2)  | (0,1)                    | `'1'`         |
| (1,0)  | (0,1)                    | `'1'`         |
| (1,1)  | (0,1), (2,2)             | `'2'`         |
| (1,2)  | (0,1), (2,2)             | `'2'`         |
| (2,0)  | none                     | `'0'`         |
| (2,1)  | (2,2)                    | `'1'`         |

---

## Extension Challenges

- **Read the board from Scanner:** Accept rows × cols, then read rows as strings of
  `.` and `*` characters.
- **Generate a random board:** Given dimensions and a mine count, randomly place mines
  using `Math.random()`.
- **Full interactive game:** Use a loop: display the hidden board, read a `(row, col)`
  input, call `reveal`, print the updated board, repeat until all non-mine cells are
  revealed (win) or a mine is clicked (lose).
