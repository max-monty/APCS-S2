# Playlist Builder — ArrayLists Meet Text Files

---

## Overview

You have a file of songs (`songs.txt`) with an unknown number of entries. Each line: `title,artist,durationInSeconds`. Since you don't know the file size ahead of time, this is a natural use case for ArrayList.

---

## The Data

```
Bohemian Rhapsody,Queen,355
Billie Jean,Michael Jackson,294
...
```

---

## Part A — Load the Playlist

Read `songs.txt` into three parallel ArrayLists:
- `ArrayList<String> titles`
- `ArrayList<String> artists`
- `ArrayList<Integer> durations`

Print the number of songs loaded and the first 3 entries.

```
Loaded 15 songs
1. Bohemian Rhapsody by Queen (5:55)
2. Billie Jean by Michael Jackson (4:54)
3. Smells Like Teen Spirit by Nirvana (5:01)
```

**Hint:** To format seconds as `m:ss`, use `duration / 60` and `duration % 60`.

---

## Part B — Playlist Stats

Compute:
1. **Total playlist duration** in minutes and seconds
2. **Longest song** (title and duration)
3. **Shortest song** (title and duration)
4. **Average song length**

---

## Part C — Edit the Playlist

1. **Remove** all songs shorter than 3 minutes (180 seconds)
   - Be careful: removing during traversal shifts indices. Use a backwards loop or track your index.
2. **Add** your own favorite song to the end
3. Print the updated playlist with numbered entries

---

## Part D — Search

Write a method that takes a keyword and returns an `ArrayList<String>` of all song titles containing that keyword (case-insensitive).

Test with: `"the"` — should find "Smells Like Teen Spirit" (nope), "Hey Jude" (nope), "Stairway to Heaven" (has "the" in it? no)... actually test it and see what you get.

**Hint:** `title.toLowerCase().contains(keyword.toLowerCase())`

---

## Starter Code

```java
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlaylistBuilder {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("songs.txt"));

        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<String> artists = new ArrayList<String>();
        ArrayList<Integer> durations = new ArrayList<Integer>();

        while (reader.hasNextLine()) {
            String[] parts = reader.nextLine().split(",");
            titles.add(parts[0]);
            artists.add(parts[1]);
            durations.add(Integer.parseInt(parts[2]));
        }
        reader.close();

        System.out.println("Loaded " + titles.size() + " songs");

        // TODO: Parts B, C, D
    }
}
```
