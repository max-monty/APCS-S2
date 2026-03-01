# Problem 6: "Runoff" — Ranked-Choice Election Simulator

## Overview

In a **ranked-choice election**, voters don't just vote for one candidate — they rank
all candidates in order of preference. If no candidate wins an outright majority (more
than 50% of votes), the candidate with the fewest first-choice votes is eliminated, and
their voters' ballots are redistributed to those voters' next preferred non-eliminated
candidate. This continues until someone has a majority or a tie is declared.

This problem asks you to implement the full ranked-choice simulation. It's more complex
than it first appears: elimination, vote redistribution, cycle detection, and tie
handling all require careful design.

---

## Definitions

- **First-choice votes:** For each ballot, look at the voter's rankings and find their
  top-ranked candidate who has **not been eliminated**.
- **Majority:** A candidate has a majority if their first-choice vote count is strictly
  greater than half the total number of ballots.
- **Elimination:** In each round, eliminate the candidate(s) with the fewest
  first-choice votes among non-eliminated candidates.
- **Tie:** If all remaining (non-eliminated) candidates have equal vote counts, declare
  a tie and print all tied candidates.

---

## Data Representation

```java
String[] candidates;          // candidate names, length = numCandidates
int[][] preferences;          // preferences[voter][rank] = candidate index
                               // preferences[v][0] = v's first choice, etc.
boolean[] eliminated;         // eliminated[i] = true if candidate i is out
int[] votes;                  // current round first-choice vote counts
```

---

## Input Format

```
<number of candidates>
<candidate name 1>
<candidate name 2>
...
<number of voters>
<voter 1 ranking: name name name ...>
<voter 2 ranking: name name name ...>
...
```

Voters enter their full ranking as space-separated candidate names. Convert names to
indices when storing in `preferences`.

### Sample Input

```
3
Alice
Bob
Charlie
5
Alice Bob Charlie
Charlie Alice Bob
Bob Charlie Alice
Alice Charlie Bob
Charlie Bob Alice
```

---

## Requirements

### Required Methods

```java
// Tabulate first-choice votes for the current round (resets votes[] to 0 first)
void tabulate()

// Return true if any non-eliminated candidate has > 50% of ballots
boolean hasWinner()

// Print the name(s) of the winner(s) if hasWinner() is true
void printWinner()

// Return the minimum vote count among non-eliminated candidates
int findMin()

// Return true if all non-eliminated candidates are tied (all have findMin() votes)
boolean isTie()

// Eliminate all non-eliminated candidates who have findMin() votes
void eliminate()
```

### Main Loop

```java
while (true) {
    tabulate();
    if (hasWinner()) { printWinner(); break; }
    if (isTie())     { printTie();   break; }
    eliminate();
}
```

---

## How `tabulate` Works

For each voter:
1. Look at their preferences in order (rank 0, rank 1, rank 2, …).
2. Find the first candidate in their ranking who is **not eliminated**.
3. Increment that candidate's vote count.

If a voter's entire ranking consists of eliminated candidates (shouldn't happen in
a well-formed election, but handle it gracefully), skip that voter.

---

## Sample Walkthrough

**Candidates:** Alice, Bob, Charlie (indices 0, 1, 2)

**Preferences:**
```
Voter 0: [0, 1, 2]  → Alice, Bob, Charlie
Voter 1: [2, 0, 1]  → Charlie, Alice, Bob
Voter 2: [1, 2, 0]  → Bob, Charlie, Alice
Voter 3: [0, 2, 1]  → Alice, Charlie, Bob
Voter 4: [2, 1, 0]  → Charlie, Bob, Alice
```

**Round 1:**
- Alice: voters 0, 3 → 2 votes
- Bob: voter 2 → 1 vote
- Charlie: voters 1, 4 → 2 votes
- Total: 5 votes; majority = >2.5, so need ≥ 3.
- No majority. Min = 1 (Bob). Not all tied.
- **Eliminate Bob.**

**Round 2:**
- Voter 0: first non-eliminated = Alice (index 0) ✓ → Alice
- Voter 1: first non-eliminated = Charlie (index 2) ✓ → Charlie
- Voter 2: first was Bob (eliminated), next is Charlie ✓ → Charlie
- Voter 3: first non-eliminated = Alice ✓ → Alice
- Voter 4: first non-eliminated = Charlie ✓ → Charlie
- Alice: 2 votes. Charlie: 3 votes.
- Charlie has 3 votes = 3/5 = 60% > 50%. **Charlie wins!**

**Output:**
```
Charlie
```

---

## More Sample Inputs and Outputs

### Sample 2 — Tie
**Input:**
```
2
Alice
Bob
4
Alice Bob
Alice Bob
Bob Alice
Bob Alice
```

**Round 1:**
- Alice: 2, Bob: 2. 2/4 = 50% — not strictly > 50%.
- `isTie()` → true (all remaining tied).
**Output:**
```
Alice
Bob
```

---

### Sample 3 — Multiple Elimination Rounds
**Input:**
```
4
Alice
Bob
Charlie
Diana
6
Alice Bob Charlie Diana
Bob Alice Charlie Diana
Charlie Bob Alice Diana
Diana Alice Bob Charlie
Alice Charlie Bob Diana
Bob Charlie Alice Diana
```

Work through this yourself! Hint: Diana gets 1 first-choice vote in round 1.

---

## Puzzle Twist: Simultaneous Elimination

When multiple candidates share the lowest vote count, **all** of them are eliminated
simultaneously in the same round — not one at a time. Your `eliminate` method must
call `findMin` once and mark all candidates with that count as eliminated in a single
pass.

**Why it matters:** Eliminating one-at-a-time could change who gets eliminated in
subsequent steps and produce a different (incorrect) result.

---

## Puzzle Twist: Infinite Loop Prevention

If `isTie()` returns `false` but `hasWinner()` also returns `false`, the loop
continues. Could the loop run forever? Think about it:

- Each round either finds a winner, declares a tie, or eliminates at least one
  candidate.
- With n candidates, the loop runs at most n − 1 rounds.

Make sure your `isTie` check runs **after** `tabulate` but **before** `eliminate`,
and that `isTie` correctly detects when all remaining candidates have equal votes
(not just when all candidates including eliminated ones are tied).

---

## Suggested Development Order

1. Write input parsing: read candidates into `String[]`, voters into `int[][]`.
   Test by printing the preferences array.
2. Write `tabulate` — the most important method. Test on Round 1 of Sample 1.
3. Write `hasWinner` and `printWinner`. Test on Round 2 of Sample 1.
4. Write `findMin` and `isTie`. Test on Sample 2.
5. Write `eliminate`. Test on Sample 1 Round 1 → 2 transition.
6. Put the main loop together.

---

## Edge Cases to Handle

- **Only 1 candidate:** They win immediately in round 1 (100% > 50%).
- **Only 2 candidates in the final round:** The one with more votes wins; if equal,
  it's a tie.
- **Voter ranks fewer candidates than exist:** Your preferences array should still
  work — just treat unranked candidates as "not preferred" (they won't appear in the
  ballot row, so they'll never be the fallback choice for that voter).

---

## Extension Challenges

- **Weighted ballots:** Give some voters extra weight (e.g., weight 2) and adjust
  vote counting accordingly.
- **Display full round-by-round results:** After each round, print a table showing
  every candidate's current vote count (or "ELIMINATED").
- **Approval voting comparison:** After the ranked-choice result, also simulate a
  simple plurality vote (only first-choice counts). Do they produce the same winner?
