# Problem 5: DNA Matching — Find the Person from STR Counts

## Overview

DNA forensics often works by analyzing **Short Tandem Repeats (STRs)** — short
sequences of DNA that repeat consecutively in certain regions of the genome. Different
people have different numbers of repeats for each STR marker. By counting the longest
run of each STR in an unknown sample and comparing those counts to a database of known
individuals, investigators can make an identification.

In this problem you'll implement exactly that: given a DNA sequence and a small database,
find the person whose STR profile matches.

---

## Background: What Is an STR?

An STR like `"AGATC"` might appear in a DNA sequence as a consecutive repeat:

```
...TTAATAAGATCAGATCAGATCTTTATC...
```

The string `AGATC` appears **3 times in a row** here, so the longest run for this STR
is **3**. Another person's DNA might have a run of 7, or just 1.

A forensic match requires that **every** STR count matches exactly.

---

## The Database

Hard-code the following database in your program:

```java
String[] NAMES = { "Alice", "Bob", "Charlie", "Diana", "Eve" };

// STR markers: AGATC, TTTTTTCT, AATG, TCTAG
int[][] DATABASE = {
    //  AGATC  TTTTTTCT  AATG  TCTAG
    {    2,       8,       3,    7   },   // Alice
    {    4,       1,       5,    2   },   // Bob
    {    3,       2,       6,    4   },   // Charlie
    {    6,       4,       2,    3   },   // Diana
    {    1,       7,       4,    5   }    // Eve
};

String[] STRS = { "AGATC", "TTTTTTCT", "AATG", "TCTAG" };
```

---

## Requirements

### Required Methods

```java
// Returns the length of the longest consecutive run of `str` in `dna`
int longestRun(String dna, String str)

// Returns the index of the matching person in DATABASE, or -1 if no match
int findMatch(int[] profile)
```

### Algorithm

1. Read the DNA sequence as a single string using `Scanner.nextLine()`.
2. For each STR in `STRS`, call `longestRun` to compute the count. Store all counts
   in an `int[]` called `profile`.
3. Call `findMatch(profile)` to compare `profile` against every row in `DATABASE`.
   A match requires **every** STR count to be equal.
4. Print the matching name, or `No match` if none is found.

---

## Implementing `longestRun`

This is the core challenge. The naive approach — check every position, count forwards —
works but requires careful index management.

**Algorithm for `longestRun(String dna, String str)`:**

```
max = 0
i = 0
while i < dna.length():
    if dna starting at i contains str:
        count = 0
        while dna starting at i contains str:
            count++
            i += str.length()      // advance past this occurrence
        max = max(max, count)
    else:
        i++                        // advance one character
return max
```

**Key insight:** When you find a run of consecutive repeats, you must advance `i` by
`str.length()` each time (not by 1) to avoid overlapping matches. When the run breaks,
advance by 1 to look for a new run starting later.

Use `dna.startsWith(str, i)` or `dna.substring(i).startsWith(str)` to check for a
match at position `i`.

---

## Sample Inputs and Outputs

### Sample 1 — Alice
**Input:**
```
TTAATAAGATCAGATCTTTATCAATGAATGTCTAG
```
Computed profile: `{ AGATC: 2, TTTTTTCT: 0, AATG: 2, TCTAG: 1 }` → **No match**

*(This is a short synthetic sequence — no one matches all four STRs.)*

---

### Sample 2 — Alice
**Input:**
```
AGATCAGATCTTTTTTCTTTTTTTCTTTTTTTCTAATGAATGAATGTCTAGTCTAGTCTAGTCTAGTCTAGTCTAGTCTAG
```
Computed profile:
- `AGATC` → longest run: 2 ✓
- `TTTTTTCT` → longest run: 3 — wait, count carefully (see note below)
- `AATG` → longest run: 3
- `TCTAG` → longest run: 7

→ Compare against Alice's row `{2, 8, 3, 7}` — `TTTTTTCT` count is 3, not 8.
→ **No match** for this sequence.

*(Tip: construct your own test sequences by concatenating repeats.)*

---

### Sample 3 — Bob
Construct a sequence containing:
- `AGATC` × 4 consecutive
- `TTTTTTCT` × 1 consecutive
- `AATG` × 5 consecutive
- `TCTAG` × 2 consecutive

```
AGATCAGATCAGATCAGATCTTTTTTCTAATGAATGAATGAATGAATGTCTAGTCTAG
```

**Expected output:** `Bob`

---

### Sample 4 — No match
**Input:**
```
AGATCAGATCAGATCTTTTTTCTTTTTTTCTAATGAATGTCTAGTCTAG
```
Computed profile: `{ 3, 2, 2, 2 }` — no row in DATABASE matches.
**Expected output:** `No match`

---

## Worked Example: `longestRun`

Call: `longestRun("AGATCAGATCTTAGATC", "AGATC")`

```
i=0:  "AGATCAGATCTTAGATC".startsWith("AGATC", 0) → true
      count=1, i=5
i=5:  .startsWith("AGATC", 5) → true
      count=2, i=10
i=10: .startsWith("AGATC", 10) → false ("TT...")
      max = max(0, 2) = 2
      i++ → i=11
...   (no more runs)
      
i=12: .startsWith("AGATC", 12) → true
      count=1, i=17
i=17: loop ends (17 >= length 17)
      max = max(2, 1) = 2

Return: 2
```

---

## Puzzle Twist: Careful Indexing

The naive approach of scanning character-by-character and using `indexOf` or a nested
loop will produce off-by-one errors in surprising places. Test your `longestRun` with
these cases:

| DNA                              | STR       | Expected |
|----------------------------------|-----------|----------|
| `"AGATC"`                        | `"AGATC"` | 1        |
| `"AGATCAGATC"`                   | `"AGATC"` | 2        |
| `"TTAGATCTT"`                    | `"AGATC"` | 1        |
| `"AGATCTTAGATCAGATC"`            | `"AGATC"` | 2        |
| `"AGATCAGATCAGATCAGATCAGATC"`   | `"AGATC"` | 5        |
| `"TTTT"`                         | `"AGATC"` | 0        |
| `""`                             | `"AGATC"` | 0        |

---

## Suggested Development Order

1. Write and exhaustively test `longestRun` using the table above.
2. Print the computed profile for Sample 3 and verify each count by hand.
3. Write `findMatch` — a simple loop that checks every row.
4. Test with all samples.

---

## Edge Cases to Handle

- **STR not found** → `longestRun` returns 0.
- **DNA shorter than the STR** → returns 0.
- **Multiple people with the same count for one STR** → still need all counts to match.
- **Empty DNA string** → return 0 for every STR, which will likely be `No match`.

---

## Extension Challenges

- **Read data from CSV format:** Accept the database as lines of input
  (`Alice,2,8,3,7`) rather than hard-coded arrays.
- **Multiple matches:** If more than one person matches, print all matching names.
- **Partial match reporting:** Print how many STRs each person matched, sorted by
  match count, even when no complete match is found.
- **Efficiency:** For very long DNA strings (millions of characters), your current
  algorithm is O(n × m × k) where n = DNA length, m = STR length, k = number of STRs.
  Can you reduce the constant factor with smarter indexing?
