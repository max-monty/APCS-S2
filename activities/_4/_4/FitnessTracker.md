# Fitness Tracker — Using Text Files with Arrays

---

## Overview

You have a month of daily fitness data for one person stored in `fitness.txt`. Each line contains the day number followed by three values: steps, active minutes, and calories burned.

Your job: read the data, store it in arrays, and answer questions about the month.

---

## The Data File

`fitness.txt` looks like this (first line is the number of days):

```
30
1 8432 35 2150
2 6211 20 1830
3 11045 55 2640
...
```

Each line after the first: `day steps activeMinutes calories`

---

## Requirements

### Part A — Read and Store

In `FitnessTracker.java`:

1. Open `fitness.txt` using `File` and `Scanner`
2. Read the first line to get the number of days
3. Create three `int[]` arrays: `steps`, `activeMinutes`, `calories`
4. Use a loop to read each line and fill the arrays

Print the first 3 days to verify:
```
Day 1: 8432 steps, 35 min, 2150 cal
Day 2: 6211 steps, 20 min, 1830 cal
Day 3: 11045 steps, 55 min, 2640 cal
```

---

### Part B — Analyze

Using array traversals and algorithms, compute and print:

1. **Average daily steps** (cast to double for precision)
2. **Best day** — the day with the most steps
3. **Goal days** — how many days had 10,000+ steps
4. **Active streak** — the longest consecutive run of days with 30+ active minutes

Expected output format:
```
--- Monthly Report ---
Average steps: 8547.3
Best day: Day 15 (13402 steps)
Days over 10k: 12
Longest active streak: 5 days
```

---

### Part C — Extend

Add one more analysis: **Weekly averages**. Group the days into weeks (days 1–7, 8–14, 15–21, 22–28, 29–30) and print the average steps per week.

```
Week 1: 8234.5 avg steps
Week 2: 9102.1 avg steps
...
```

---

## Starter Code

```java
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FitnessTracker {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("fitness.txt"));

        int n = reader.nextInt();
        reader.nextLine(); // consume the rest of the line after nextInt()
        int[] steps = new int[n];
        int[] activeMinutes = new int[n];
        int[] calories = new int[n];

        // TODO: read data into arrays

        reader.close();

        // TODO: Part B analyses
    }
}
```

---

## Hints

- `reader.nextInt()` reads the next integer, skipping whitespace — perfect for space-separated data
- For the active streak, you'll need a running count and a max count (same pattern as longest run problems)
- For weekly averages, use integer division to figure out which week a day belongs to
