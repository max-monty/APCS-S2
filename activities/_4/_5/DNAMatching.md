# DNA Matching — Find the Person from STR Counts

## Overview

DNA forensics works by analyzing **Short Tandem Repeats (STRs)** — short sequences of DNA that repeat consecutively. Different people have different repeat counts for each STR marker. By counting the longest run of each STR in an unknown sample and comparing to a database, investigators can identify a person.

You'll read both the database and the DNA sample from text files, then find the match.

---

## Background: What Is an STR?

An STR like `"AGATC"` might appear in a DNA sequence as a consecutive repeat:

```
...TTAATAAGATCAGATCAGATCTTTATC...
```

`AGATC` appears **3 times in a row** here, so the longest run is **3**. A forensic match requires that **every** STR count matches exactly.

---

## The Files

### `database.txt`

```
AGATC TTTTTTCT AATG TCTAG
Alice 2 8 3 7
Bob 4 1 5 2
Charlie 3 2 6 4
Diana 6 4 2 3
Eve 1 7 4 5
```

Line 1: the STR markers (space-separated). Every line after: a name followed by their count for each STR.

### `sample1.txt`, `sample2.txt`, `sample3.txt`

Each file contains a single line: the DNA sequence to analyze.

---

## Requirements

### Step 1 — Read the Database

Read `database.txt` into parallel arrays:
- `String[] strs` — the STR markers from line 1
- `String[] names` — one per person
- One `int[]` per STR marker to hold each person's count, e.g. `int[] agatcCounts`, `int[] ttttttctCounts`, etc.

Alternatively, you can store all counts in a single 2D array.

**Hint:** Read line 1 and `split(" ")` to get the STRs. For each remaining line, split it, grab the name from index 0, and parse the ints from the rest.

### Step 2 — Read the DNA

Read the DNA sequence from a sample file (start with `sample2.txt`).

### Step 3 — Count Longest Runs

Write this method:

```java
static int longestRun(String dna, String str)
```

Returns the length of the longest consecutive run of `str` in `dna`.

For each STR, compute the longest run and store the results in an `int[] profile`.

### Step 4 — Find the Match

Compare `profile` against each person's counts. A match means **every** STR count is equal. Print the matching name, or `"No match"`.

---

## Expected Results

| File | Profile | Result |
|------|---------|--------|
| `sample1.txt` | `{3, 2, 2, 2}` | No match |
| `sample2.txt` | `{4, 1, 5, 2}` | Bob |
| `sample3.txt` | `{1, 7, 4, 5}` | Eve |

---

## Test Your `longestRun`

Before wiring everything together, verify your method with these cases:

| DNA | STR | Expected |
|-----|-----|----------|
| `"AGATC"` | `"AGATC"` | 1 |
| `"AGATCAGATC"` | `"AGATC"` | 2 |
| `"AGATCTTAGATCAGATC"` | `"AGATC"` | 2 |
| `"AGATCAGATCAGATCAGATCAGATC"` | `"AGATC"` | 5 |
| `"TTTT"` | `"AGATC"` | 0 |

---

## Starter Code

```java
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DNAMatching {
    public static void main(String[] args) throws IOException {
        // Read database
        Scanner db = new Scanner(new File("database.txt"));
        String[] strs = db.nextLine().split(" ");

        // There are 5 people and 4 STRs.
        String[] names = new String[5];
        int[] agatcCounts = new int[5];
        int[] ttttttctCounts = new int[5];
        int[] aatgCounts = new int[5];
        int[] tctagCounts = new int[5];

        // TODO: loop through remaining lines, split each,
        // store name and parse the 4 counts

        db.close();

        // Read DNA sample
        Scanner sample = new Scanner(new File("sample2.txt"));
        String dna = sample.nextLine();
        sample.close();

        // TODO: compute profile using longestRun

        // TODO: find and print the match
    }

    static int longestRun(String dna, String str) {
        // TODO
        return 0;
    }
}
```

---

## Suggested Development Order

1. Write and test `longestRun` using the table above
2. Hardcode a small DNA string and verify your profile matches expected values
3. Wire up file reading for the database
4. Wire up file reading for the DNA sample
5. Test with all three sample files

---

## Extension

- Read the sample filename from the command line: `args[0]`
- If multiple people match, print all of them
- Print a partial match report: for each person, show how many STRs matched
