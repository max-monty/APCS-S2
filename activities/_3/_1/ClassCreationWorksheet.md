# Class Creation Worksheet

Build a `Thermostat` class from scratch, adding features at each level.

---

## Level 1 🌱 — Define the Class

### 1) Thermostat Basics

In `Thermostat.java`, create a class with:

- Private instance variables:
  - `double currentTemp` — the current temperature
  - `double targetTemp` — the desired temperature
  - `boolean isHeating` — whether the heater is on
- A parameterized constructor that takes all three values
- A `toString()` method that returns something like:
  ```
  Thermostat [current: 68.0, target: 72.0, heating: true]
  ```

Test by creating a `Thermostat` in a `main` method and printing it.

---

## Level 2 🌿 — Accessors and Mutators

### 2) Getters

Add accessor methods for all three instance variables:
- `double getCurrentTemp()`
- `double getTargetTemp()`
- `boolean getIsHeating()`

### 3) Setters with Validation

Add mutator methods:
- `void setTargetTemp(double t)` — only sets if `t` is between 50 and 90 (inclusive)
- `void setIsHeating(boolean h)` — sets the heating mode

---

## Level 3 🌳 — Behavior Methods

### 4) Adjust Temperature

Add a void method `void adjustTemp()` that:
- If `isHeating` is true and `currentTemp < targetTemp`, increase `currentTemp` by 1.0
- If `isHeating` is false and `currentTemp > targetTemp`, decrease `currentTemp` by 1.0
- Otherwise, do nothing

### 5) Check Target

Add a non-void method `boolean isAtTarget()` that returns `true` if `currentTemp` equals `targetTemp`.

### 6) Set Mode

Add a void method `void setMode(String mode)` that:
- If `mode` is `"heat"`, sets `isHeating` to true
- If `mode` is `"cool"`, sets `isHeating` to false

---

## Level 4 🌲 — Constructor Overloading

### 7) Default Constructor

Add a default (no-argument) constructor that sets:
- `currentTemp` to 70.0
- `targetTemp` to 70.0
- `isHeating` to false

### 8) Overloaded Method

Add an overloaded method `adjustTemp` that:
- Increases `currentTemp` by `amount` if `isHeating` is true
- Decreases `currentTemp` by `amount` if `isHeating` is false

---

## Level 5 🌴 — Driver Simulation

### 9) ThermostatDriver

In `ThermostatDriver.java`, write a program that:

1. Creates two thermostats:
   - One using the default constructor
   - One set to current: 60.0, target: 72.0, heating: true
2. Uses a loop to call `adjustTemp()` on each thermostat until both reach their target
3. Prints the state of each thermostat after every adjustment step

Example output:
```
Step 1: Thermostat [current: 70.0, target: 70.0, heating: false] | Thermostat [current: 61.0, target: 72.0, heating: true]
Step 2: Thermostat [current: 70.0, target: 70.0, heating: false] | Thermostat [current: 62.0, target: 72.0, heating: true]
...
```
