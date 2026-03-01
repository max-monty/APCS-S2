# Problem 7: "Speller Lite" — Fast Word Checker Without HashMap

## Overview

Spell-checkers are one of the oldest and most practically important string algorithms.
In this problem you'll build one from scratch — without using Java's `HashSet` or
`HashMap`. Instead, you'll implement your own fast lookup structure using either
**binary search on a sorted array** or a **bucket-based structure** using an
`ArrayList<ArrayList<String>>`. The goal is to understand *why* data structures matter
for performance, not just how to use built-in ones.

---

## How the Spell-Checker Works

1. **Load** a dictionary of valid words into your data structure.
2. **Read** a passage of text (one line).
3. For each word in the passage, **normalize** it (lowercase, strip surrounding
   punctuation), then **look it up** in your dictionary.
4. Output all misspelled words in **alphabetical order** (unique — don't repeat the
   same word twice), along with summary statistics.

---

## The Dictionary

Hard-code this small dictionary in your program for testing. Your teacher may provide
a larger file for the final version.

```java
String[] RAW_DICTIONARY = {
    "a", "able", "about", "above", "across", "after", "again", "against", "all",
    "almost", "also", "always", "among", "an", "and", "another", "any", "are",
    "around", "as", "at", "away", "be", "because", "been", "before", "being",
    "between", "both", "but", "by", "came", "can", "come", "could", "did", "do",
    "does", "each", "either", "else", "even", "every", "few", "find", "for",
    "found", "from", "get", "give", "go", "good", "great", "had", "has", "have",
    "he", "her", "here", "him", "his", "how", "i", "if", "in", "into", "is",
    "it", "its", "just", "know", "large", "last", "let", "like", "little", "long",
    "made", "make", "many", "me", "might", "more", "most", "much", "must", "my",
    "never", "new", "next", "no", "not", "now", "of", "often", "on", "once",
    "one", "only", "or", "other", "our", "out", "over", "own", "part", "people",
    "place", "put", "read", "right", "said", "same", "saw", "say", "see", "set",
    "she", "should", "show", "since", "small", "so", "some", "something", "stand",
    "still", "such", "take", "tell", "than", "that", "the", "their", "them",
    "then", "there", "these", "they", "things", "think", "this", "those", "through",
    "time", "to", "together", "too", "turn", "under", "until", "up", "us", "use",
    "very", "was", "water", "way", "we", "well", "went", "were", "what", "when",
    "where", "which", "while", "who", "why", "will", "with", "work", "would",
    "write", "you", "your"
};
```

---

## Requirements

### Normalization Rules

Before looking up a word:

1. Convert to **lowercase**.
2. Strip **leading** punctuation characters: remove any `'`, `"`, `(`, `[` at the start.
3. Strip **trailing** punctuation characters: remove any `.`, `,`, `!`, `?`, `;`, `:`,
   `'`, `"`, `)`, `]` at the end.
4. After stripping, if the result is empty (e.g., input was `"---"`), skip it.
5. Do **not** strip punctuation from the middle of a word — `"don't"` stays `"don't"`;
   `"self-aware"` stays `"self-aware"`.

### Constraint: No HashSet / HashMap

You must implement **one** of these two approaches:

#### Option A: Binary Search on a Sorted Array

1. Sort `RAW_DICTIONARY` into a `String[] sortedDict` (you may use `Arrays.sort`).
2. Implement `boolean binarySearch(String[] dict, String word)`:
   - Compare `word` to the middle element using `String.compareTo`.
   - If equal, return `true`.
   - If `word` comes before the middle, search the left half.
   - If `word` comes after the middle, search the right half.
   - If the range is empty, return `false`.
3. **Do not** use `Arrays.binarySearch`.

#### Option B: Bucket Structure

1. Create `ArrayList<ArrayList<String>> buckets` with 26 buckets (one per letter).
2. For each word in the dictionary, compute its bucket index as `word.charAt(0) - 'a'`
   and add it to that bucket's list. Sort each bucket alphabetically.
3. Implement `boolean bucketSearch(ArrayList<ArrayList<String>> buckets, String word)`:
   - Find the correct bucket.
   - Do a linear scan (or binary search within the bucket's list) for `word`.

---

## Output Format

```
MISSPELLED WORDS
<word1>
<word2>
...

WORDS IN TEXT:  <total>
MISSPELLINGS:   <count>
```

Misspelled words must be in alphabetical order and listed only once each (even if the
word appears multiple times in the passage).

---

## Sample Input and Output

### Sample 1
**Passage:**
```
The quick brown fox jumps over the lazy dog.
```

After normalization: `the quick brown fox jumps over the lazy dog`

- "the" ✓, "quick" ✗, "brown" ✗, "fox" ✗, "jumps" ✗, "over" ✓, "the" (skip dup),
  "lazy" ✗, "dog" ✗

**Output:**
```
MISSPELLED WORDS
brown
dog
fox
jumps
lazy
quick

WORDS IN TEXT:  9
MISSPELLINGS:   6
```

---

### Sample 2
**Passage:**
```
"Hello," said the little girl, who had never been to such a place before.
```

After normalization (stripping `"` and `,` etc.):
`hello said the little girl who had never been to such a place before`

Check each. "hello" ✗, "said" ✓, "the" ✓, "little" ✓, "girl" ✗, "who" ✓, "had" ✓,
"never" ✓, "been" ✓, "to" ✓, "such" ✓, "a" ✓, "place" ✓, "before" ✓

**Output:**
```
MISSPELLED WORDS
girl
hello

WORDS IN TEXT:  14
MISSPELLINGS:   2
```

---

### Sample 3 — Repeated misspelling
**Passage:**
```
A dog is a dog. Every dog needs a walk.
```

"dog" appears 3 times but should appear only **once** in the misspelled list.

**Output:**
```
MISSPELLED WORDS
dog
needs
walk

WORDS IN TEXT:  9
MISSPELLINGS:   3
```

*(Note: "every" is not in the sample dictionary. Make sure you check!)*

---

## Implementing Binary Search

Your `binarySearch` method should work as follows. Trace through this example:

```
dict: ["a", "an", "and", "at", "be", "by"]
word: "and"

low=0, high=5, mid=2 → dict[2]="and" → found! return true

word: "any"
low=0, high=5, mid=2 → "and".compareTo("any") < 0 → search right
low=3, high=5, mid=4 → dict[4]="be" → "be".compareTo("any") > 0 → search left
low=3, high=3, mid=3 → dict[3]="at" → "at".compareTo("any") > 0 → search left
low=3, high=2 → low > high → return false
```

---

## Puzzle Twist: Must Be Reasonably Fast

On a dictionary of 10,000 words, a naive O(n) linear scan would require up to 10,000
comparisons per word. With binary search, that drops to about 14 comparisons (log₂ 10000
≈ 13.3). 

To verify your implementation is working correctly, add a **comparison counter**:

```java
static int comparisonCount = 0;
```

Increment it each time you call `compareTo` inside your search. For a 130-word passage
and a 10,000-word dictionary, you should see far fewer than 130 × 10,000 = 1,300,000
comparisons with binary search.

---

## Suggested Development Order

1. Write `normalize(String word)` and test it on the edge cases below.
2. Load the dictionary into your sorted array or bucket structure.
3. Write and test `binarySearch` (or `bucketSearch`) against a few known words.
4. Write word splitting: split the passage on spaces, normalize each token, skip empties.
5. Use an `ArrayList<String>` to collect unique misspellings. Sort before printing.
6. Print the full output and verify with Sample 1.

---

## Edge Cases for Normalization

| Input token | After normalization |
|-------------|---------------------|
| `"Hello,"` | `"hello"` |
| `'"word"'` | `"word"` |
| `"(test)"` | `"test"` |
| `"don't"` | `"don't"` (apostrophe in the middle stays) |
| `"..."`   | `""` (empty → skip) |
| `"End."` | `"end"` |
| `"A"` | `"a"` |

---

## Extension Challenges

- **Read from a file:** Accept a filename as a command-line argument for both the
  dictionary and the passage.
- **Suggestions:** For each misspelled word, print the 3 closest dictionary words by
  **edit distance** (the number of character insertions, deletions, or substitutions
  needed to convert one word to another).
- **Performance benchmark:** Time your binary search vs. a naïve linear scan on
  10,000 words and a 500-word passage. Print elapsed milliseconds using
  `System.currentTimeMillis()`.
