# Problem 4: "Recover" — Detect Hidden Messages in Noisy Data (2D Arrays)

## Overview

A secret message has been encoded inside a grid of integers representing pixel
intensities. Each character of the message occupies a **5×5 block** of grid cells.
A cell is considered "on" (part of the letter shape) if its value is at or above a
threshold, and "off" otherwise. Your job is to traverse the grid, recognize each 5×5
pattern, and decode the hidden message — even when the data contains noise.

---

## Background: The Mini Font

Each letter is encoded as a 5-row × 5-column binary pattern. Here is the font for
letters A–F (and the space character). You will hard-code these in your program.

```
A:          B:          C:          D:          E:
0 1 1 1 0   1 1 1 1 0   0 1 1 1 0   1 1 1 1 0   1 1 1 1 1
1 0 0 0 1   1 0 0 0 1   1 0 0 0 0   1 0 0 0 1   1 0 0 0 0
1 1 1 1 1   1 1 1 1 0   1 0 0 0 0   1 0 0 0 1   1 1 1 1 0
1 0 0 0 1   1 0 0 0 1   1 0 0 0 0   1 0 0 0 1   1 0 0 0 0
1 0 0 0 1   1 1 1 1 0   0 1 1 1 0   1 1 1 1 0   1 1 1 1 1

F:          SPACE:
1 1 1 1 1   0 0 0 0 0
1 0 0 0 0   0 0 0 0 0
1 1 1 1 0   0 0 0 0 0
1 0 0 0 0   0 0 0 0 0
1 0 0 0 0   0 0 0 0 0
```

> **Extension:** Your teacher may give you a full A–Z font. The structure is identical;
> just add more entries to your font array.

---

## Grid Format

The input grid has:
- **Height:** a multiple of 5 (each row of characters needs 5 grid rows)
- **Width:** a multiple of 5 (each character column needs 5 grid columns)
- **Values:** integers 0–255 representing intensity

The message is encoded left-to-right in the first (and possibly only) row of 5-row-tall
blocks. You may assume the message is a single line of characters.

### Threshold

A cell is considered **"on"** if its value ≥ **180**.

---

## Data Representation

Store the grid as `int[][] grid`. Hard-code a sample grid in your program for testing,
or read it from `Scanner` (rows × columns of integers).

Store the font as `int[][][] FONT` — an array of letter patterns, where each pattern is
a `int[5][5]`.

Pair letters with their patterns using a parallel `char[] LETTERS` array:

```java
char[] LETTERS = { 'A', 'B', 'C', 'D', 'E', 'F', ' ' };
int[][][] FONT  = {
    { {0,1,1,1,0}, {1,0,0,0,1}, {1,1,1,1,1}, {1,0,0,0,1}, {1,0,0,0,1} }, // A
    { {1,1,1,1,0}, {1,0,0,0,1}, {1,1,1,1,0}, {1,0,0,0,1}, {1,1,1,1,0} }, // B
    // ... etc
};
```

---

## Requirements

### Required Methods

```java
// Extract the 5x5 block starting at grid row r, column c
int[][] extractBlock(int[][] grid, int r, int c)

// Convert a 5x5 block of intensities to binary using the threshold
int[][] binarize(int[][] block, int threshold)

// Count how many cells differ between two 5x5 binary patterns
int countMismatches(int[][] blockBinary, int[][] fontPattern)

// Identify the best-matching letter for a given block (lowest mismatch count)
char recognizeLetter(int[][] block)

// Decode the full grid, returning the hidden message string
String decodeGrid(int[][] grid)
```

### Algorithm

1. In `decodeGrid`, iterate over the grid in **5×5 steps** from left to right.
2. For each step, call `extractBlock` to get the current 5×5 region.
3. Call `recognizeLetter` on that block.
4. `recognizeLetter` should call `binarize` on the block, then compare it to every
   entry in `FONT` using `countMismatches`, and return the letter with the **fewest
   mismatches**.
5. Append each decoded letter to build the final message string.
6. Print the decoded message.

---

## Sample Grid and Expected Output

Below is a 5×10 grid encoding the two-character message `"AB"`.
*(Values ≥ 180 are "on"; values < 180 are "off". Some noise has been added.)*

```
Grid (5 rows × 10 cols):
  30  200  210  205   25    210  200  195  200   25
 200   20   15   10  200    200   10   15   10  200
 195  200  205  200  205    200  205  200  195   20
 200   20   10   25  205    200   10   15   10  200
 195   15   15   15  200    200  200  190  205   20
```

**Expected output:** `AB`

**How it works:**
- Left block (columns 0–4): binarized → matches A's font pattern with 0 mismatches.
- Right block (columns 5–9): binarized → matches B's font pattern with 0 mismatches.

---

## Puzzle Twist: Noise Tolerance

Real grids contain noise — cells that should be "on" are slightly dim, or "off" cells
have a random bright speckle. Your `recognizeLetter` must handle this by choosing the
**closest match** rather than requiring a perfect match.

### Noisy Sample

```
Grid (5 rows × 5 cols) — should decode as 'A' but has 3 noisy pixels:
  30  200  165   205   25      ← cell (0,2) = 165, should be 'on' (≥180): noise!
 200   20   15    10  200
 175  200  205   200  205      ← cell (2,0) = 175, should be 'on': noise!
 200   20   10    25  205
 195   15   15    15  110      ← cell (4,4) = 110, should be 'on': noise!
```

With threshold = 180, this binarizes to:

```
0 1 0 1 0
1 0 0 0 1
0 1 1 1 1
1 0 0 0 1
1 0 0 0 0
```

Mismatches vs. A (expected `1 0 0 0 1` on last row, got `1 0 0 0 0`) = **3 mismatches**.
Mismatches vs. every other letter will be higher. So the output is still `'A'`.

**Rule:** If the minimum mismatch count is > 12 (out of 25 cells), output `'?'` to
signal that no letter was recognized reliably.

---

## Suggested Development Order

1. Hard-code the font patterns for A, B, C, and SPACE. Print them back out to verify.
2. Write `extractBlock` and test it on your sample grid.
3. Write `binarize` and print the result for the A block above.
4. Write `countMismatches` — compare two `int[5][5]` arrays cell by cell.
5. Write `recognizeLetter` — loop over all font entries, keep the best (lowest) score.
6. Write `decodeGrid` and test on the `"AB"` sample.
7. Add the noisy sample and confirm your tolerance works.

---

## Edge Cases to Handle

- Grid width not evenly divisible by 5 → print an error and exit.
- All cells below threshold in a block → will match SPACE (all-zero pattern).
- Two letters with equal mismatch count → tie-break by taking the first in `LETTERS`.

---

## Extension Challenges

- **Region labeling:** After decoding, fill an `int[][] regions` grid where each cell
  belonging to a recognized letter's "on" pixels is labeled with that character's index.
- **Full A–Z font:** Your teacher will provide the complete 26-letter font. Plug it in
  without changing your core algorithm.
- **Variable block size:** Parameterize `extractBlock` and `recognizeLetter` to work
  with N×N blocks (where N is passed as an argument), not just 5×5.
- **Read from Scanner:** Instead of hard-coding the grid, read the dimensions and then
  all values from standard input.
