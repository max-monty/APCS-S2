# Problem 3: Substitution Cipher Cracker (Frequency + Constraints)

## Overview

A **monoalphabetic substitution cipher** replaces every letter in a message with a
different letter using a fixed, consistent mapping. For example, every `A` might become
`Q`, every `B` might become `X`, and so on. Given only the ciphertext, your program will
attempt to recover the original message using **letter frequency analysis** — a classical
cryptanalysis technique.

This is a genuine puzzle: there's no single correct answer, and you'll need to reason
about how to design your program so it improves its own guesses.

---

## Background

### English Letter Frequency Order

In ordinary English text, letters appear with roughly this frequency (most → least common):

```
E  T  A  O  I  N  S  H  R  D  L  C  U  M  W  F  G  Y  P  B  V  K  J  X  Q  Z
```

The approach: count how often each letter appears in the ciphertext, rank them from most
to least frequent, then map the most common ciphertext letter → `E`, the next → `T`, and
so on.

This gives a **starting guess** — but English isn't perfectly regular, so you'll also
implement a **hill-climbing** refinement step.

---

## What You Are Given

Your program will receive:

1. **Ciphertext** — a single line of text (read with `Scanner.nextLine()`). Punctuation,
   spaces, and capitalization are **preserved** in output; only letters are substituted.

2. **A hint dictionary** (hard-code this array in your program):

```java
String[] DICTIONARY = {
    "the", "and", "to", "of", "a", "in", "is", "it", "you", "that",
    "he", "was", "for", "on", "are", "as", "with", "his", "they", "at",
    "be", "this", "from", "or", "one", "have", "an", "by", "but", "not",
    "had", "her", "she", "all", "there", "been", "when", "who", "will",
    "would", "said", "each", "which", "do", "their", "what", "so", "up"
};
```

---

## Requirements

### Data Representation

Represent your cipher mapping as a `char[]` of length 26, where index `i` holds the
**plaintext letter** that ciphertext letter `i` maps to.

```java
char[] mapping = new char[26];
// mapping[0] = 'e' means ciphertext 'A'/'a' decodes to 'E'/'e'
```

Also maintain a `boolean[] used` of length 26 to track which plaintext letters have
already been assigned (no two ciphertext letters may decode to the same plaintext letter).

### Required Methods

```java
// Count how many times each letter (0=A ... 25=Z) appears in the text
int[] letterFrequency(String text)

// Apply the current mapping to ciphertext; preserve case, spaces, punctuation
String applyMapping(String ciphertext, char[] mapping)

// Count how many words in the decoded text appear in DICTIONARY
int scoreCandidate(String plaintext)

// Swap the plaintext assignments of two ciphertext letters in the mapping
void swapMapping(char[] mapping, int i, int j)
```

### Algorithm Steps

1. **Frequency analysis:** Call `letterFrequency` on the ciphertext. Sort the 26 letters
   by frequency (highest first). Map the most frequent ciphertext letter to `'e'`, the
   next to `'t'`, and so on through `ETAOINSHRDLCUMWFGYPBVKJXQZ`.

2. **Decode and score:** Call `applyMapping` and `scoreCandidate` on the initial guess.
   Store this as your `bestScore` and `bestPlaintext`.

3. **Hill-climb (at least 500 iterations):** In a loop:
   - Pick two random ciphertext letter indices `i` and `j`.
   - Swap their plaintext assignments using `swapMapping`.
   - Decode the ciphertext and score it.
   - If the new score is **better**, keep the swap. Otherwise, undo it.

4. **Output:**
   - Print the final mapping table (see format below).
   - Print the best-guess plaintext.

### Output Format

```
Mapping:
A -> e
B -> t
C -> a
...
Z -> q

Decoded:
<best guess plaintext here>
```

Preserve the original ciphertext's capitalization: if the original ciphertext character
was uppercase, the decoded character should be uppercase; if lowercase, lowercase.

---

## Sample Input and Output

### Sample Input
```
Zsi bpnn vf eyxvdm vf bvzs bpm jxmibmiz xvlpmi bpib wvl kwevlz, ib bpm sbvxa nwcm vf bpmq.
```

### Sample Output (approximate — yours may vary)
```
Mapping:
A -> f
B -> w
...
Z -> t

Decoded:
The will of doing is with the greatest riches that you count, at the above love of them.
```

*(Because hill-climbing is randomized, your exact output may differ slightly, but the
dictionary word count should be high.)*

---

## Puzzle Twist: Constraints

Your mapping must always satisfy:

1. **Consistency:** One ciphertext letter always maps to the same plaintext letter.
   (This is automatic if you use the `mapping[]` array correctly.)
2. **Injectivity:** No two ciphertext letters map to the same plaintext letter.
   (Enforce this via the `used[]` array and the `swapMapping` method — a swap
   cannot produce duplicates.)

If your initial frequency mapping accidentally assigns the same plaintext letter twice
(possible if two ciphertext letters share the same count), you must resolve the conflict
before proceeding.

---

## Suggested Development Order

1. Write `letterFrequency` and print the top 5 letters of a test string to verify.
2. Write `applyMapping` — this is critical to get right before anything else.
3. Write `scoreCandidate` — how many dictionary words appear in the decoded string?
   Use `String.contains()` or split into words and check each.
4. Build the initial frequency mapping and manually check `applyMapping` output.
5. Add the hill-climbing loop and watch the score go up.

---

## Edge Cases to Handle

- **Short ciphertext** (< 26 unique letters) — some letters may never appear.
  Assign the remaining plaintext letters to unused ciphertext letters arbitrarily.
- **Punctuation and spaces** — `applyMapping` must leave non-letters unchanged.
- **Case preservation** — `'A'` and `'a'` are the same ciphertext letter; decode both
  and adjust case in the output.
- **All-caps ciphertext** — still works, just decode and re-apply case rules.

---

## Scoring Rubric Hint

A `scoreCandidate` value of **8+** (out of the 47 dictionary words) on a typical
English paragraph is a good target. The hill-climbing should consistently push you
above the naive frequency mapping.

---

## Extension Challenges

- **Simulated annealing:** Instead of only accepting improvements, occasionally accept
  worse swaps early in the search (with decreasing probability over time). This helps
  escape local optima.
- **Digraph frequency:** English has predictable two-letter patterns (`TH`, `HE`, `IN`,
  `ER`). Use digraph counts as an additional signal to refine the mapping.
- **Interactive mode:** After decoding, let the user manually override a mapping entry
  (`"Set X -> r"`) and re-score, then continue hill-climbing from the new state.
