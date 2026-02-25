# Scope & Access Worksheet

Build a `Temperature` class that practices scope, `this`, and variable shadowing. Ties back to the Thermostat from Day 3.1.

---

## Level 1 🌱 — Constructor Shadowing with `this`

### 1) Temperature Class

In `Temperature.java`, create a class with:

- Private instance variables: `double degrees`, `String scale` (e.g., `"C"` or `"F"`)
- A constructor where the parameter names **match** the instance variable names:
  ```java
  public Temperature(double degrees, String scale)
  ```
  Use `this` to assign correctly.
- `toString()` that returns something like: `72.0 degrees Fahrenheit`

---

## Level 2 🌿 — Local Variable Scope

### 2) Convert to Fahrenheit

Add a method `double toFahrenheit()` that:
- If already Fahrenheit, returns `degrees`
- If Celsius, creates a **local variable** `double converted`, calculates `degrees * 9.0 / 5.0 + 32`, and returns it

### 3) Convert to Celsius

Add a method `double toCelsius()` using the same pattern (local variable for the conversion).

**Question:** Can the `converted` variable in `toFahrenheit()` be accessed from `toCelsius()`? Why or why not?

---

## Level 3 🌳 — `this` with Object Parameters

### 4) Compare Temperatures

Add a method `boolean isWarmerThan(Temperature other)` that:
- Converts both temperatures to the same scale (e.g., both to Fahrenheit)
- Returns `true` if `this` temperature is warmer

Notice: `this` is implicit — `toFahrenheit()` is called on `this` object.

### 5) Swap Values

Add a method `void copyFrom(Temperature other)` that:
- Copies `other`'s degrees and scale into `this` object
- Uses `this.degrees` and `this.scale` to make assignment clear

---

## Level 4 🌲 — Debug Exercise

### 6) Find the Bugs

The class below has **5 scope/access bugs**. Fix them all.

```java
public class BankAccount {
    private double balance;
    private String owner;
    private static int count = 0;

    public BankAccount(double balance, String owner) {
        balance = balance;          // Bug 1
        owner = owner;              // Bug 2
        count++;
    }

    public void deposit(double amount) {
        double newBalance = balance + amount;
        balance = newBalance;
    }

    public void printInfo() {
        System.out.println(owner + ": $" + newBalance);  // Bug 3
    }

    public static void printCount() {
        System.out.println("Accounts: " + count);
        System.out.println("Owner: " + this.owner);      // Bug 4
    }

    public void transfer(BankAccount other, double amount) {
        balance -= amount;
        other.balance += amount;
        double fee = amount * 0.01;
    }

    public void printFee() {
        System.out.println("Fee: " + fee);                // Bug 5
    }
}
```

For each bug, explain:
- What the problem is
- How to fix it

---

## Level 5 🌴 — Full Class Design Challenge

### 7) Design Your Own Class

Design and implement a class of your choice that uses **all** of the following:

- At least 2 private instance variables
- At least 1 static variable
- A constructor that uses `this` (parameter shadowing)
- At least 1 accessor and 1 mutator
- A method that takes an object of the same type as a parameter
- A method with a meaningful local variable
- `toString()`

Ideas: `Song`, `Recipe`, `GameCharacter`, `Planet`, `Smartphone`

Write both the class and a driver that demonstrates every feature. In your driver:
- Use `Scanner` to let the user create at least one object with custom values
- Store at least 3 objects in an **array** and use a **for loop** to print them all
- Use a loop to find the "best" object by some criteria (like finding the max in an array)
