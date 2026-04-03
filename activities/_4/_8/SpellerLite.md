# Speller Lite — Review Challenge

**Covers: Text Files, ArrayLists, String Methods**

---

## Goal

Build a spell-checker. Your program reads a dictionary of valid words from one file, reads a passage from another file, and reports every word in the passage that isn't in the dictionary — with no duplicates.

This pulls together everything from the past week: file I/O, arrays, ArrayLists, String manipulation, and traversal algorithms.

---

## Expected Output

```
MISSPELLED WORDS
quick
brown
fox
jumps
lazy
dog
...

WORDS IN TEXT:  29
MISSPELLINGS:   12
```

*(passage1.txt — most misspellings are common words not in the small 179-word dictionary)*

---

## Steps

### 1. Load the dictionary

Read `dictionary.txt` into an `ArrayList<String>`. The file has one word per line, all lowercase.

### 2. Read the passage

Read the entire passage from `passage1.txt` into a single `String` (concatenate each line). Then split it into an array of raw words on spaces.

### 3. Check each word

Loop through the raw words. For each one, call `normalize(word)` (provided in the starter code) to strip punctuation and lowercase it. If the result is empty, skip it. Otherwise:

- Count it toward the total word count
- If it's **not** in the dictionary and **not** already in your misspellings list, add it

### 4. Print results

Print all misspelled words (one per line), the total word count, and the number of misspellings in the format shown above.

---

## Starter Code

```java
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellerLite {
    public static void main(String[] args) throws IOException {
        // 1. Load dictionary

        // 2. Read passage and split into words

        // 3. Normalize each word, check spelling, collect misspellings

        // 4. Print results
    }

    static String normalize(String word) {
        word = word.toLowerCase();
        while (word.length() > 0 && "\"'(".indexOf(word.charAt(0)) >= 0) {
            word = word.substring(1);
        }
        while (word.length() > 0 && ".,!?;:\"')".indexOf(word.charAt(word.length() - 1)) >= 0) {
            word = word.substring(0, word.length() - 1);
        }
        return word;
    }
}
```

---

## Hints

- You've read files into ArrayLists before — same pattern here, twice.
- Test with `passage1.txt` first, then try `passage2.txt` and `passage3.txt`.

---

## What This Reviews

| Concept | Where it shows up |
|---------|------------------|
| File I/O with Scanner | Reading dictionary and passage |
| `split()` | Breaking passage into words |
| ArrayList (dynamic size) | Dictionary, misspellings list |
| ArrayList `.contains()` | Checking dictionary, deduplication |
| String methods | Reading the provided `normalize` method |
| Enhanced for loop | Traversing words |

---

## Extensions

- **Alphabetical output:** Print the misspelled words in sorted order. How would you insert each word into the right position as you go?
- **Binary search:** Load the dictionary into a sorted `String[]` array instead of an ArrayList. Write a binary search method to look up words — much faster than `.contains()` on a large dictionary.
- **Suggestions:** For each misspelled word, find and print the closest dictionary word alphabetically
- **Interactive mode:** Let the user type a passage instead of reading from a file
