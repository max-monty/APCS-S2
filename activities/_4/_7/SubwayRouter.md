# Subway Router — MBTA Path Finder

## Overview

The MBTA operates several subway lines that intersect at shared stations. Your job is
to write a program that finds the shortest route between any two stations, telling the
rider which lines to take and where to transfer.

---

## Starter Code

```java
ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
ArrayList<String> lineNames = new ArrayList<String>();

void setupLines() {
  ArrayList<String> red = new ArrayList<String>();
  red.add("Alewife"); red.add("Davis"); red.add("Porter"); red.add("Harvard");
  red.add("Central"); red.add("Kendall"); red.add("Park Street");
  red.add("South Station"); red.add("Broadway"); red.add("Andrew");
  lines.add(red);
  lineNames.add("Red Line");

  ArrayList<String> green = new ArrayList<String>();
  green.add("Lechmere"); green.add("Science Park"); green.add("North Station");
  green.add("Haymarket"); green.add("Park Street"); green.add("Boylston");
  green.add("Copley"); green.add("Kenmore");
  lines.add(green);
  lineNames.add("Green Line");

  ArrayList<String> orange = new ArrayList<String>();
  orange.add("Oak Grove"); orange.add("Malden"); orange.add("Wellington");
  orange.add("Sullivan"); orange.add("North Station"); orange.add("Haymarket");
  orange.add("State"); orange.add("Downtown Crossing"); orange.add("Chinatown");
  orange.add("Tufts Medical Center");
  lines.add(orange);
  lineNames.add("Orange Line");

  ArrayList<String> blue = new ArrayList<String>();
  blue.add("Wonderland"); blue.add("Revere Beach"); blue.add("Beachmont");
  blue.add("Suffolk Downs"); blue.add("Airport"); blue.add("Aquarium");
  blue.add("State"); blue.add("Government Center"); blue.add("Bowdoin");
  lines.add(blue);
  lineNames.add("Blue Line");
}
```

---

## Your Task

Write a `findRoute` method that takes a start station and an end station and prints the
shortest route. A rider can travel in either direction on a line. Your method should:

1. Check if both stations are on the same line. If so, print the direct route.
2. If not, check all pairs of lines for a **one-transfer route**: ride one line to a
   shared transfer station, then ride a second line to the destination.
3. If no one-transfer route exists, check all triples of lines for a **two-transfer
   route**: ride three lines with two transfers in between.
4. If multiple routes exist, print the shortest one (fewest total stations).

A **transfer station** is any station that appears on more than one line. Don't
double-count a transfer station when comparing route lengths.

---

## Example Output

**Harvard to South Station** (direct):
```
Red Line: Harvard -> Central -> Kendall -> Park Street -> South Station
```

**Harvard to Copley** (one transfer):
```
Red Line: Harvard -> Central -> Kendall -> Park Street
Transfer to Green Line at Park Street
Green Line: Park Street -> Boylston -> Copley
```

---

## Test Cases

| Start | End | Expected Transfers |
|---|---|---|
| Alewife | Andrew | 0 |
| Harvard | Kenmore | 1 |
| Kenmore | Tufts Medical Center | 1 |
| Wonderland | Oak Grove | 1 |
| Lechmere | Airport | 2 |
| Davis | Chinatown | ? |
| Park Street | Park Street | 0 (same station) |

---

## Things to Watch Out For

- Some stations appear on more than one line. Make sure you consider all lines a
  station belongs to, not just the first one you find.
- When comparing route lengths across a transfer, don't count the transfer station
  twice.
- Riders can go in either direction on a line.

---

## Extensions

- **Travel time:** Assume 2 minutes between adjacent stations and 5 minutes per
  transfer. Print the estimated travel time.
- **Interactive mode:** Use a `Scanner` to let the user enter routes repeatedly.
- **Add a line:** Add the Silver Line. If your code is well-designed, this should be
  easy.