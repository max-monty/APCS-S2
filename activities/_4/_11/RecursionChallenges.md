# Recursion — Day 1

---

## Overview

A **recursive method** is a method that calls itself. Every recursive method needs two parts:

1. **Base case(s)** — the simplest version of the problem, solved directly (no recursive call). This is what stops the recursion.
2. **Recursive call(s)** — the method calls itself on a **smaller** version of the problem. "Smaller" has to mean closer to the base case, or the method will never stop.

If either part is missing or wrong, you get infinite recursion and a `StackOverflowError`.

### The classic example: factorial

```
n! = n * (n-1) * (n-2) * ... * 1
0! = 1   (by definition)
```

Rewritten recursively:

```
factorial(0) = 1                         <-- base case
factorial(n) = n * factorial(n - 1)      <-- recursive call
```

In Java:

```java
public static int factorial(int n) {
    if (n <= 1) {
        return 1;                        // base case
    }
    return n * factorial(n - 1);         // recursive call
}
```

**Trace of `factorial(4)`:**

```
factorial(4)
 = 4 * factorial(3)
 = 4 * (3 * factorial(2))
 = 4 * (3 * (2 * factorial(1)))
 = 4 * (3 * (2 * 1))       <-- base case hit
 = 4 * (3 * 2)
 = 4 * 6
 = 24
```

Notice that the calls pile up going **down**, and the answers come back **up** once the base case is reached.

### When recursion has TWO recursive calls: Fibonacci

```
fib(0) = 0
fib(1) = 1
fib(n) = fib(n-1) + fib(n-2)    for n >= 2
```

Same shape as factorial, just **two** recursive calls combined. The sequence: `0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...`

---

## Iterative vs Recursive

Every recursive method can also be written iteratively (with loops). You'll see both styles throughout CS. Recursion is usually cleaner when the problem naturally breaks into a smaller version of itself.

| | Iterative | Recursive |
|---|---|---|
| Uses | Loops + variables | Method calls itself |
| "State" lives in | Local variables | The call stack |
| Stops via | Loop condition | Base case |
| Best for | Counting, accumulating | Problems that shrink naturally (trees, halving, divide-and-conquer) |

---

## Activity

Open `RecursionChallenges.java`. Fill in the three methods. Each level builds on the last.

---

## Level 1 🌱 — Warm-up: Trace Factorial

Before writing code, trace these by hand and check your work against the demo output:

1. `factorial(3)` = ?
2. `factorial(6)` = ?
3. What happens if you call `factorial(-1)` with the code as written? Why? What should the base case be to make it safe?

---

## Level 2 🌿 — Fibonacci

Fill in:

```java
public static int fib(int n)
```

Return the nth Fibonacci number (0-indexed).

- `fib(0)` → 0
- `fib(1)` → 1
- `fib(n)` → `fib(n-1) + fib(n-2)` for n ≥ 2

**Checklist before you write code:**

- What is/are the base case(s)?
- What is the recursive call (or calls)?
- Why does this naturally have TWO recursive calls instead of one?

Test in `main()`:

```java
System.out.println(fib(0));   // 0
System.out.println(fib(1));   // 1
System.out.println(fib(6));   // 8
System.out.println(fib(10));  // 55
```

**Think:** try calling `fib(40)`. It takes noticeably longer. Why? Draw out the call tree for `fib(5)` — notice how `fib(3)` gets computed more than once.

---

## Level 3 🌳 — Recursive String Reversal

Fill in:

```java
public static String reverse(String s)
```

Return `s` reversed. You must solve this **recursively** — no loops, no `StringBuilder.reverse()`.

**Think about it:**

- What is the smallest/simplest string to reverse? (That's your base case.)
- If you already had `reverse(s.substring(1))`, how could you use it to build `reverse(s)`?

Test:

```java
System.out.println(reverse(""));         // ""
System.out.println(reverse("a"));        // "a"
System.out.println(reverse("hello"));    // "olleh"
System.out.println(reverse("racecar"));  // "racecar"
```

**Hint:** two valid recursive structures work here — one peels off the first character, one peels off the last. Try both.

---

## Level 4 🌲 — Palindrome Check (Challenge)

Fill in:

```java
public static boolean isPalindrome(String s)
```

Return `true` if `s` reads the same forwards and backwards.

**Constraint:** Solve this recursively **without** reversing the whole string first. Compare the first and last characters, then recurse on the middle.

**Three cases to handle:**

1. Base case — an empty string or a single character is always a palindrome.
2. If the first and last characters don't match → `false`.
3. Otherwise → recurse on the middle (everything except the first and last characters).

Test:

```java
System.out.println(isPalindrome(""));         // true
System.out.println(isPalindrome("a"));        // true
System.out.println(isPalindrome("racecar"));  // true
System.out.println(isPalindrome("level"));    // true
System.out.println(isPalindrome("hello"));    // false
```

**Think:** each recursive call makes the string **two characters** shorter instead of one. How many calls deep does `isPalindrome` go for a string of length `n`, compared to how many `reverse` would go?

---

## Level 5 🌴 — Extensions

Pick one or more.

### A) Sum of Digits

Write `public static int sumDigits(int n)` that returns the sum of the digits of `n` (assume `n >= 0`).

- `sumDigits(0)` → 0
- `sumDigits(7)` → 7
- `sumDigits(1234)` → 10

**Hint:** `n % 10` gives the last digit, `n / 10` drops the last digit.

### B) Power

Write `public static int power(int base, int exp)` that returns `base` raised to `exp` (assume `exp >= 0`). No `Math.pow`.

- `power(2, 0)` → 1
- `power(2, 10)` → 1024
- `power(3, 4)` → 81

### C) Case-Insensitive Palindrome

Modify `isPalindrome` (or write a new version) that ignores case: `isPalindrome("Racecar")` → `true`.

### D) Fibonacci with Memoization (Preview)

Your `fib(40)` was slow because the same calls are computed over and over. Use an `int[]` "memo" where `memo[i]` stores `fib(i)` once it's been computed, and reuse it on later calls. Time `fib(40)` before and after.

---

## What a Good Recursive Method Has

Use this checklist on every method you write in this unit:

- [ ] A base case that returns without calling itself
- [ ] The base case is actually reachable from the recursive call
- [ ] Each recursive call makes the input **smaller** (closer to the base case)
- [ ] The method combines the recursive result with something from the current call (multiply, add, concat, etc.) to build the full answer
