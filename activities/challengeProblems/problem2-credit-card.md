# Problem 2: Credit Card Validator + Card Type Detector

## Overview

Credit card numbers look random, but they follow strict mathematical rules designed to
catch typos and transcription errors. In this problem you will implement **Luhn's
algorithm** to validate a credit card number, then identify which card network issued it
based on its length and starting digits — all without ever converting the number to a
numeric type.

---

## Background: Luhn's Algorithm

Luhn's algorithm works on the string of digits from **right to left**:

1. Starting from the **second-to-last** digit and moving left, **double every other
   digit**.
2. If doubling a digit produces a value ≥ 10, **subtract 9** from it (equivalently,
   add the two resulting digits together: 18 → 1 + 8 = 9).
3. Sum all the digits (both the doubled/adjusted ones and the untouched ones).
4. If the total **modulo 10 equals 0**, the number is valid.

### Worked Example

Card number: `4003600000000014`

Write the digits right-to-left and label alternating positions:

```
Position (1-indexed from right):  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16
Digit:                             4  1  0  0  0  0  0  0  0  0  6  3  0  0  4  (wait — see below)
```

Let's do it step by step with the actual number:

```
Digits:    4  0  0  3  6  0  0  0  0  0  0  0  0  0  1  4
           ^                                              ^
         index 0                                      index 15
```

- Odd positions from the right (index 15, 13, 11, … from right = positions 1, 3, 5…):
  leave unchanged → `4, 0, 0, 0, 0, 0, 3, 0` ... (every other starting from last)
- Even positions from the right: double them, subtract 9 if ≥ 10.

Sum = 20, 20 % 10 = 0 → **VALID**

> **Important:** You are working with a `String`. Use `charAt(i) - '0'` to get the
> integer value of each character digit. **Do not use `Long.parseLong` or `Integer
> .parseInt` on the whole number** — some valid card numbers are too large for `long`.

---

## Card Type Rules

If the number passes Luhn's check, classify it:

| Type       | Length      | Starting digits |
|------------|-------------|-----------------|
| AMEX       | 15 digits   | `34` or `37`    |
| MASTERCARD | 16 digits   | `51`–`55`       |
| VISA       | 13 or 16 digits | `4`         |
| VALID      | anything else valid | —         |

If the number fails Luhn's check: output `INVALID`.

---

## Requirements

1. Read the card number as a `String` (use `Scanner.next()`).
2. **Reject immediately** (output `INVALID`) if the input contains any non-digit
   character, including spaces, hyphens, or letters.
3. Implement a `boolean isValid(String number)` method that runs Luhn's algorithm.
4. Implement a `String cardType(String number)` method that returns `"AMEX"`,
   `"MASTERCARD"`, `"VISA"`, or `"VALID"`. This method may assume the number has
   already been validated.
5. A `boolean allDigits(String s)` helper method to check for non-digit characters.
6. Output exactly one word on its own line.

---

## Sample Inputs and Outputs

### Sample 1 — Valid VISA
**Input:** `4003600000000014`
**Output:** `VISA`

### Sample 2 — Valid AMEX
**Input:** `378282246310005`
**Output:** `AMEX`

### Sample 3 — Valid MASTERCARD
**Input:** `5105105105105100`
**Output:** `MASTERCARD`

### Sample 4 — Invalid number (bad checksum)
**Input:** `1234567890123456`
**Output:** `INVALID`

### Sample 5 — Contains non-digit characters
**Input:** `4003-6000-0000-0014`
**Output:** `INVALID`

### Sample 6 — Valid but unknown type
**Input:** `6011111111111117`  *(Discover card — not in our type list)*
**Output:** `VALID`

### Sample 7 — All zeros (edge case)
**Input:** `0000000000000000`
**Output:** `INVALID`

---

## Puzzle Twist: No Numeric Conversion

You **may not** use `Long.parseLong()`, `Integer.parseInt()`, or any numeric parsing
on the full card number string. Every digit must be extracted character-by-character.

This means your Luhn loop will look something like:

```java
for (int i = number.length() - 1; i >= 0; i--) {
    int digit = number.charAt(i) - '0';
    // ... alternate doubling logic based on position from right
}
```

Track whether a position is "even from the right" or "odd from the right" using a
counter or by checking `(number.length() - 1 - i) % 2`.

---

## Suggested Development Order

1. Write and test `allDigits(String s)` — loop through every character.
2. Write and test `isValid(String number)` with the worked example above.
   Print the running sum at each step to verify.
3. Write and test `cardType(String number)` with `startsWith()` and `length()`.
4. Connect everything in `main`.

---

## Edge Cases to Handle

- **Empty string** → `INVALID`
- **Single digit** `"0"` → does it pass Luhn? (`0 % 10 == 0` → yes, but only 1 digit,
  so it's `VALID` by type rules — interesting! Think about whether you want to handle
  this.)
- **Exactly 13 digits starting with 4** → `VISA`
- **16 digits starting with 50** → passes Luhn? If so, `VALID` (not MASTERCARD, which
  requires 51–55).

---

## Extension Challenges

- Accept card numbers with **spaces or hyphens** as formatting (e.g.,
  `4003 6000 0000 0014`) by stripping those characters first before validation.
- Print a **formatted receipt**: mask all but the last four digits
  (`**** **** **** 0014`).
- Support **Discover** (16 digits, starts with `6011`) and **UnionPay** (16–19 digits,
  starts with `62`) in your type detector.
