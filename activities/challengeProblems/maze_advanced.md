# Maze Generator & Solver

## The Big Picture

You've built a game AI that searches a tree of possible moves to find the best one. Now you're going to flip that around — instead of searching a tree, you're going to **build** one (to generate a maze) and then **search** it (to solve the maze). The algorithms behind this show up everywhere in CS: GPS navigation, network routing, web crawlers, and puzzle solvers.

This project has three phases. Each one builds on the last.

---

## Phase 1: Generate a Random Maze

Use **recursive backtracking** to generate a random, perfect maze (a maze where there is exactly one path between any two cells — no loops, no isolated sections).

### How it works

1. Start with a grid where every cell is surrounded by four walls.
2. Pick a starting cell. Mark it as visited.
3. From the current cell, look at its unvisited neighbors. If there are any:
   - Pick one at random.
   - Remove the wall between the current cell and the chosen neighbor.
   - Move to that neighbor and repeat (recurse).
4. If there are no unvisited neighbors, backtrack to the previous cell and try again.
5. When you've backtracked all the way to the start and there's nowhere left to go, the maze is complete.

### Things to figure out

- How do you represent the grid? Think about what data each cell needs to store (which walls are present, whether it's been visited, its position).
- How do you represent walls? A cell has four walls — but a wall is shared between two cells. When you remove a wall, you need to update both sides.
- How do you find a cell's neighbors, and how do you check if a neighbor is valid (in bounds and unvisited)?

### Deliverable

Print the maze to the console. Use characters like `+`, `-`, `|`, and spaces to draw the walls and passages. You should be able to run your program multiple times and get a different maze each time.

A 4×4 maze might look something like this:

```
+---+---+---+---+
|               |
+   +---+   +   +
|   |       |   |
+   +   +---+   +
|   |   |       |
+   +   +   +---+
|       |       |
+---+---+---+---+
```

---

## Phase 2: Solve It Two Ways

Now implement two different algorithms to solve the maze. The start is the top-left corner and the goal is the bottom-right corner.

### Algorithm 1: Depth-First Search (DFS)

DFS explores as far as possible along a path before backtracking — it's essentially the same logic you used to generate the maze. It will find **a** path, but not necessarily the shortest one.

### Algorithm 2: Breadth-First Search (BFS)

BFS explores all cells at distance 1 first, then distance 2, then distance 3, and so on. It guarantees the **shortest** path.

This will work with ArrayLists, but to scale it to much larger mazes you will need to use a **queue** (`LinkedList` implements `Queue` in Java).

### The core loop (same structure for both)

```
add the start cell to your data structure
mark it as visited

while the data structure is not empty:
    remove a cell (stack = pop, queue = poll)
    if it's the goal, you're done — reconstruct the path
    otherwise, add each unvisited neighbor (that doesn't have a wall in the way) to the data structure
    mark each neighbor as visited and record where it came from
```

### Things to figure out

- The two algorithms are almost identical in code. What's the one difference that changes the behavior so dramatically?
- How do you reconstruct the path once you've found the goal? Think about what information you need to store along the way.
- What does each algorithm's **exploration pattern** look like? DFS will feel like a snake winding through the maze. BFS will feel like water flooding outward from the start.

### Deliverable

Print the solved maze to the console. Mark the DFS path with one character (like `*`) and the BFS path with another (like `.`). Print the maze twice — once with each solution — so you can compare them side by side.

---

## Phase 3: Visualize the Search

This is where it gets cool. Instead of just showing the final path, **step through the search process** so you can watch each algorithm explore the maze.

### What to show

After each step of the algorithm, reprint the maze showing:
- Cells currently in the frontier (waiting to be explored) — mark with `?`
- Cells that have been fully processed — mark with `~`
- The final path once the goal is reached — mark with `*`

### How to control it

Use a `Scanner` to let the user control the visualization:
- Press Enter to advance one step
- Type `run` to finish the search without stopping
- At the start, let the user type `dfs` or `bfs` to choose which algorithm to watch

### Things to figure out

- Your search algorithm currently runs in a loop until it's done. How do you restructure it so it does **one step at a time** and waits for input before continuing?
- Can you let the user run both algorithms on the same maze and compare how many cells each one explored before finding the goal?

---

## Stretch Goals (if you want to keep going)

- **User-defined maze size** — let the user enter the number of rows and columns via Scanner before generating.
- **A\* search** — a smarter version of BFS that uses a heuristic (estimated distance to the goal) to search in the right direction first. This is what actual GPS navigation uses. How many fewer cells does it explore compared to BFS?
- **Different maze algorithms** — try Prim's algorithm or Kruskal's algorithm for generation. They produce mazes with a different "feel" (more branching, shorter dead ends).
- **Maze difficulty scoring** — how would you measure whether a maze is "hard"? Dead ends? Path length vs. grid size? Solution path twistiness?
- **Load a maze from a file** — read a maze from a text file using file I/O instead of generating one. This lets you design specific test cases by hand.