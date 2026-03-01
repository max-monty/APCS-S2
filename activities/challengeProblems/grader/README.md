# APCS Challenge Problem Grader

A local website where students upload their Java solution files and have them automatically tested.

## Requirements

- **Python 3** (already on macOS) — `python3 --version`
- **Java JDK** — `javac -version` (needed to compile student code)

## Running the grader

```bash
cd activities/challengeProblems/grader
python3 server.py
```

Then open **http://localhost:3000** in a browser.

## How it works

1. Student picks a problem from the sidebar
2. Student uploads their `.java` file
3. The server compiles it with `javac`, runs it against multiple test inputs, and returns pass/fail for each test case
4. Results appear immediately with expected vs. actual output

## Problems covered

| # | Problem | Tests |
|---|---------|-------|
| 1 | Readability 2.0 | 4 — exact stdout match |
| 2 | Credit Card Validator | 6 — exact stdout match |
| 3 | Cipher Cracker | 1 — format + dictionary word score |
| 4 | Recover | 1 — output contains "AB" |
| 5 | DNA Matching | 5 — exact stdout match |
| 6 | Runoff | 3 — exact/contains match |
| 7 | Speller Lite | 3 — contains key output strings |
| 8 | Minesweeper | 1 — contains all 4 clue grid rows |

## Notes for students

- **Do not use `package` declarations** — the grader compiles in a temp directory.
- **Problems 4 and 8** use hard-coded grids — use the sample grids from the problem spec.
- **Problem 3** scoring is approximate since hill climbing is randomized.
- Each test has a **5-second timeout** — check for infinite loops if you see timeouts.
