# AP Computer Science A: Game Challenge Problems

These five challenges are open-ended projects. Unlike previous assignments, you won't
be given method signatures or a development order — you decide how to structure your
code. The only requirements are the game rules and the input/output format. Use
`Scanner` for all input. These do not have an automatic tester. You will have to 
play the game to test it.

---

## Challenge 1: Wordle

### The Game

You have six attempts to guess a secret five-letter word. After each guess, the game
tells you which letters are correct:

- **Correct (uppercase):** The letter is in the word AND in the right position.
- **Misplaced (lowercase):** The letter is in the word but in the wrong position.
- **Wrong (+):** The letter is not in the word at all.

### Example Session

```
Secret word: CRANE

Guess 1: STARE
  +  +  R  +  E

Guess 2: GRIND
  g  R  +  N  +

Guess 3: CRANE
  C  R  A  N  E

You got it in 3 guesses!
```

In guess 1, `R` and `E` are in the correct positions (uppercase). `S`, `T`, and `A`
are not in the word at all (`+`). But wait — `A` *is* in "CRANE." Why does it show `+`?
Because the `A` in "STARE" is at position 3, but in "CRANE" the `A` is at position 2.
So it should actually be misplaced (lowercase `a`). Make sure your logic handles this
correctly.

### Tricky Case: Duplicate Letters

```
Secret word: APPLE
Guess: PAPER

  p  A  P  +  +
```

Think carefully: `P` appears twice in the guess and twice in the secret word. The `P`
at position 2 is in the correct spot (uppercase). The `P` at position 0 is in the word
but not in the right spot (lowercase). But `E` is at position 4 in the guess and
position 4 in... wait, no — `E` is at position 4 in "APPLE" and position 3 in "PAPER."
Work through this yourself.

What happens when a letter appears more times in the guess than in the secret word?
For example, guessing `GEESE` when the secret word is `CRANE` — the guess has three
`E`s but the secret only has one. You should only mark as many `E`s as actually
exist in the secret word (prioritizing correct-position matches first).

### Requirements

- Hard-code an array of at least 20 five-letter words. Pick the secret word randomly.
- Accept guesses via Scanner. Reject guesses that aren't exactly 5 letters.
- Display feedback after each guess using the format above.
- End the game after a correct guess or after 6 failed attempts (reveal the word).

### Things to Think About

- How do you handle the duplicate letter problem described above? This is the hardest
  part of Wordle logic. Consider making multiple passes over the guess.
- How will you track which letters the player has already eliminated?
- Can you display a running summary of known information after each guess?

---

## Challenge 2: Battleship

### The Game

Two players each have a 10×10 grid. Each player secretly places 5 ships on their grid,
then they take turns guessing coordinates to fire at. A hit is marked with `X`, a miss
with `O`. When all cells of a ship are hit, it sinks. The first player to sink all
opponent ships wins.

### Ship Sizes

| Ship           | Size |
|----------------|------|
| Carrier        | 5    |
| Battleship     | 4    |
| Cruiser        | 3    |
| Submarine      | 3    |
| Destroyer      | 2    |

Ships can be placed horizontally or vertically. Ships cannot overlap or extend off
the grid.

### Display Format

Show two grids side by side — your ships and your view of the opponent's board:

```
   YOUR BOARD                  OPPONENT BOARD
   0 1 2 3 4 5 6 7 8 9        0 1 2 3 4 5 6 7 8 9
A  . . S S S . . . . .     A  . . . . . . . . . .
B  . . . . . . . . . .     B  . O . . . . . . . .
C  . . . . . . . . . .     C  . . . X . . . . . .
D  . B . . . . . . . .     D  . . . . . . . . . .
E  . B . . . . . . . .     E  . . . . . . . . . .
F  . B . . . . . . . .     F  . . . . . . . . . .
G  . B . . . . . . . .     G  . . . . . . . . . .
H  . . . . . . . . . .     H  . . . . . . . . . .
I  . . . . . . . . . .     I  . . . . . . . . . .
J  . . . . . . . . . .     J  . . . . . . . . . .
```

### Requirements

- Human vs. computer. The computer places ships randomly and fires at random
  coordinates (no repeats).
- Players place ships by entering a starting coordinate (e.g., `C4`) and orientation
  (`H` or `V`). Reject invalid placements (overlapping, off-grid).
- On each turn, the player enters a coordinate to fire at. Report hit, miss, or sunk
  (and which ship was sunk).
- The game ends when one side has lost all ships.

### Things to Think About

- You'll need multiple 2D arrays. What does each one store, and for which player?
- How do you represent a ship so you can track when it's fully sunk? A ship spans
  multiple cells — what connects them?
- How do you validate ship placement? A 4-cell ship placed at column 8 going
  horizontally would go off the board.
- For an extra challenge, make the computer smarter: when it gets a hit, have it
  fire at adjacent cells on subsequent turns instead of continuing to guess randomly.

---

## Challenge 3: 2048

### The Game

Play on a 4×4 grid of numbered tiles. Each turn, you slide all tiles in one direction
(up, down, left, right). Tiles slide as far as they can. If two tiles with the same
value collide, they merge into one tile worth their sum. After each move, a new tile
(value 2 or 4) appears in a random empty cell. The goal is to create a tile worth 2048.

### Example Turn

```
Before (slide left):         After:
   .  2  .  2                  4  .  .  .
   4  .  4  .                  8  .  .  .
   2  .  .  .                  2  .  .  .
   .  .  .  2                  2  .  .  .
                              (new tile appears in a random empty cell)
```

### Merging Rules

These details matter — get them wrong and the game will feel broken:

1. When sliding, tiles merge toward the direction of movement. If a row contains
   `2 2 2 .` and you slide left, the result is `4 2 . .` (the leftmost pair merges
   first), not `2 4 . .`.
2. A tile can only merge once per turn. Sliding `4 4 4 4` left produces `8 8 . .`,
   not `16 . . .`.
3. If a move wouldn't change the board at all (nothing slides or merges), it doesn't
   count as a turn and no new tile appears.

### Input/Output Format

```
SCORE: 164

  2    4    .    2
  8    8    .    .
  4    .    2    .
  .    .    .    .

Move (w/a/s/d): a
```

### Requirements

- The board is a 4×4 `int[][]` (use 0 for empty cells).
- Start with two random tiles (90% chance of 2, 10% chance of 4).
- Accept `w` (up), `a` (left), `s` (down), `d` (right) as input.
- Track and display the score (the sum of all values created by merging).
- The game is won when a 2048 tile appears. The game is lost when the board is full
  and no merges are possible in any direction.

### Things to Think About

- The core problem is implementing the slide-and-merge for a single row or column.
  Once you can do it for one direction, how can you reuse that logic for all four?
- How do you check if the game is over? You need to verify that no move in any
  direction would change anything.
- How do you prevent a tile from merging twice in one move?

---

## Challenge 4: Tic-Tac-Toe with AI

### The Game

Standard 3×3 Tic-Tac-Toe, human (`X`) vs. computer (`O`). The twist: the computer
should be impossible to beat. A perfect Tic-Tac-Toe AI never loses — it either wins
or forces a draw.

### Display Format

```
  0   1   2
0  X | O |
  ---+---+---
1    | X |
  ---+---+---
2  O |   |

Your move (row col): 2 2
```

### Requirements

- Human plays `X` and goes first. Computer plays `O`.
- Accept moves as `row col` (e.g., `1 2`). Reject occupied cells and out-of-range
  input.
- The computer must play optimally — it should never lose.
- Detect and announce win, loss, or draw.

### Building the AI: Minimax

The key to an unbeatable AI is the **minimax algorithm**. The idea: before the
computer makes a move, it mentally plays out every possible future of the game and
picks the move that leads to the best guaranteed outcome.

Here's the concept:

- The computer is the **maximizer** — it wants to pick the move with the highest
  score.
- The human is the **minimizer** — the algorithm assumes the human will always pick
  the move that's worst for the computer.
- Assign scores to end states: computer wins = +10, human wins = -10, draw = 0.
- For each empty cell, the computer imagines placing its piece there, then asks
  "what would the human do next?" The human would then pick their best move, and so
  on, until the game ends.
- The algorithm is recursive — it calls itself to evaluate deeper and deeper future
  states, alternating between maximizing and minimizing.

You don't need to search very deep — Tic-Tac-Toe has at most 9 moves total, so the
entire game tree is small enough to search exhaustively.

### Things to Think About

- What are the base cases for your recursive minimax function? (When does the game
  end?)
- How do you represent "it's the maximizer's turn" vs. "it's the minimizer's turn"?
- After the basic AI works, try subtracting the search depth from the score so the
  computer prefers winning sooner over winning later. Without this, the AI might
  delay a winning move unnecessarily.

---

## Challenge 5: Connect Four

### The Game

Two players take turns dropping pieces into a 6-row × 7-column grid. Pieces fall to
the lowest available row in the chosen column. The first player to get four in a row
(horizontally, vertically, or diagonally) wins. If the board fills up with no winner,
it's a draw.

### Display Format

```
  0   1   2   3   4   5   6
|   |   |   |   |   |   |   |
|   |   |   |   |   |   |   |
|   |   |   |   |   |   |   |
|   |   | X |   |   |   |   |
|   | O | O |   |   |   |   |
| X | X | O | X |   |   |   |
+---+---+---+---+---+---+---+

Player X, choose a column (0-6): 4
```

### Requirements

- Two-player game (human vs. human), or human vs. a computer that picks a random
  valid column.
- Accept a column number as input. Reject full columns and out-of-range input.
- After each move, check for a winner (four in a row in any direction).
- Detect and announce a draw when the board is full.

### Things to Think About

- The "drop" mechanic is the first interesting problem: when a player picks column 3,
  how do you find the lowest empty row in that column?
- Win checking needs to look in four directions: horizontal, vertical, and both
  diagonals. How do you avoid going out of bounds during these checks?
- Do you check the entire board after every move, or can you limit your search to
  the area around the most recently dropped piece?
- For an extra challenge, implement a smarter AI. Can you apply the same minimax
  concept from Tic-Tac-Toe? What makes Connect Four harder for minimax than
  Tic-Tac-Toe? (Hint: think about how many possible game states exist in a 6×7 grid
  vs. a 3×3 grid.)
