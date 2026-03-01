# Problem 1: Readability 2.0 — Grade Level with Robust Parsing

## Overview

In this problem you will write a Java program that reads a passage of text and estimates
what school grade level it is written for. You'll use the **Coleman–Liau index**, a
readability formula that looks at letter density and sentence density to predict reading
difficulty. The real challenge is writing a parser that handles messy, real-world input
gracefully.

---

## Background: The Coleman–Liau Index

The formula works like this:

```
L = (number of letters / number of words) * 100
S = (number of sentences / number of words) * 100
index = 0.0588 * L − 0.296 * S − 15.8
```

- **Letters** — only alphabetic characters (A–Z, a–z). Digits, punctuation, and spaces
  do not count.
- **Words** — sequences of non-space characters separated by one or more spaces.
  Leading and trailing spaces are ignored. Multiple consecutive spaces count as one
  separator.
- **Sentences** — any sequence ending in `.`, `!`, or `?`. A sentence-ending punctuation
  mark always ends exactly one sentence, even if followed by another punctuation mark
  (`...` = 1 sentence, `?!` = 2 sentences... wait, actually count each `.`, `!`, `?`
  character individually).

> **Clarification on sentences:** Count every occurrence of `.`, `!`, or `?` in the
> input, including ones inside words like `U.S.A.` This is intentional — it matches
> the original CS50 specification.

---

## Output Rules

| Computed index value | Output       |
|----------------------|--------------|
| index < 1            | Before Grade 1 |
| 1 ≤ index < 16       | Grade X (X = index rounded to nearest integer) |
| index ≥ 16           | Grade 16+    |

Use `Math.round()` for rounding.

---

## Requirements

1. Read exactly **one line** of input using `Scanner.nextLine()`.
2. Implement **at least three helper methods**:
   - `int countLetters(String text)` — returns the number of A–Z / a–z characters.
   - `int countWords(String text)` — returns the number of words (handles multiple spaces).
   - `int countSentences(String text)` — returns the number of `.`, `!`, and `?` characters.
3. Compute L, S, and index as `double` values.
4. Print exactly one line matching the output format above.

---

## Puzzle Twist: Robust Word Counting

Your `countWords` method **must** handle all of the following edge cases correctly.
A naive `split(" ")` will fail on several of them — read the documentation for
`String.trim()` and `String.split()` carefully, or consider a manual loop.

| Input | Expected word count |
|-------|---------------------|
| `"hello world"` | 2 |
| `"  hello   world  "` | 2 |
| `"one"` | 1 |
| `"  "` | 0 |
| `""` | 0 |
| `"a  b  c"` | 3 |

> **Hint:** `"  hello   world  ".split(" ")` produces empty strings in the array.

---

## Sample Inputs and Outputs

### Sample 1
**Input:**
```
Congratulations! Today is your day. You're off to Great Places! You're off and away!
```
**Output:**
```
Grade 3
```

**Worked example:**
- Letters: 65 (`Congratulations`, `Today`, `is`, … — count each A–Z/a–z)
- Words: 16
- Sentences: 4 (one `!`, one `.`, one `!`, one `!`)
- L = 65/16 * 100 ≈ 406.25
- S = 4/16 * 100 = 25.0
- index = 0.0588 * 406.25 − 0.296 * 25.0 − 15.8 ≈ 23.89 − 7.4 − 15.8 ≈ 3.0 → **Grade 3**

---

### Sample 2
**Input:**
```
It was a bright cold day in April, and the clocks were striking thirteen.
```
**Output:**
```
Grade 10
```

---

### Sample 3
**Input:**
```
Go.
```
**Output:**
```
Before Grade 1
```

---

### Sample 4
**Input:**
```
A large class of computational problems involve the determination of properties of graphs, digraphs, integers, arrays of integers, finite families of finite sets, boolean formulas and elements of other combinatorial domains.
```
**Output:**
```
Grade 16+
```

---

## Suggested Development Order

1. Write and test `countLetters` first — it's the simplest.
2. Write and test `countSentences` — iterate over characters and check each one.
3. Write and test `countWords` with the edge-case table above before touching the formula.
4. Put the formula together and verify Sample 1's worked example by hand.
5. Try Samples 2–4.

---

## Edge Cases to Handle

- Input with **no sentence-ending punctuation** → `countSentences` returns 0, which
  causes a division by zero when computing S. Decide how to handle this (output
  `Before Grade 1`? Print an error message? Protect with a conditional).
- Input that is **only spaces** → `countWords` must return 0; protect the formula
  from dividing by zero.
- **Mixed punctuation** like `"Really?! Yes."` → sentence count = 3.

---

## Extension Challenges

- **Multi-line input:** Read until the user enters a blank line, accumulate all text,
  then compute the score for the entire passage.
- **Word normalization:** Before counting letters in a word, strip surrounding
  punctuation so `"don't"` counts as one word. How does this change your approach?
- **Multiple scores:** Accept a filename, read every paragraph, and print the grade
  level of each one.
