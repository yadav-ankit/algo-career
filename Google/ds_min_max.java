## Problem

designing a DS to store values(MIN/MAX/Current) for a stock (Like google finance).
Value changes every millisecond. Added the complexity by adding a new functionality of Update, which could change any previous value.


Design a DS for stock prices:

Operations:

```text
update(timestamp, price)   // insert or modify old timestamp price
current()                  // latest timestamp price
maximum()                  // highest price
minimum()                  // lowest price
```

Example:

```text
update(1,100)
update(2,80)
update(3,120)

current() -> 120
max() -> 120
min() -> 80

update(2,140)

current() -> 120
max() -> 140
min() -> 100
```

---

## Data structures used

### 1. HashMap

Store:

```text
timestamp → price
```

Example:

```text
1 → 100
2 → 80
3 → 120
```

Need this to:

* find old price during update
* get current price

Complexity:

```text
O(1)
```

---

### 2. TreeMap

Store:

```text
price → frequency
```

Example:

```text
80 → 1
100 → 2
120 → 1
```

Need frequency because multiple timestamps can have same price.

Get:

```java
priceCount.firstKey();   // minimum price
priceCount.lastKey();    // maximum price
```

Complexity:

```text
O(log n)
```

---

### 3. latestTimestamp

Track:

```text
latest timestamp seen
```

Need:

```java
current()
```

in:

```text
O(1)
```

---

## Complexity

| Operation | Complexity |
| --------- | ---------- |
| update    | O(log n)   |
| current   | O(1)       |
| maximum   | O(log n)   |
| minimum   | O(log n)   |

---

## Java Code

```java
import java.util.*;

class StockPrice {

    Map<Integer, Integer> timestampToPrice = new HashMap<>();
    TreeMap<Integer, Integer> priceCount = new TreeMap<>();

    int latestTimestamp = 0;

    public void update(int timestamp, int price) {

        // timestamp already exists → remove old price frequency
        if (timestampToPrice.containsKey(timestamp)) {

            int oldPrice = timestampToPrice.get(timestamp);

            priceCount.put(oldPrice, priceCount.get(oldPrice) - 1);

            if (priceCount.get(oldPrice) == 0)
                priceCount.remove(oldPrice);
        }

        // add new price
        timestampToPrice.put(timestamp, price);

        priceCount.put(price, priceCount.getOrDefault(price, 0) + 1);

        latestTimestamp = Math.max(latestTimestamp, timestamp);
    }

    public int current() {
        return timestampToPrice.get(latestTimestamp);
    }

    public int maximum() {
        return priceCount.lastKey();
    }

    public int minimum() {
        return priceCount.firstKey();
    }
}
```

---

## Dry run

```java
update(1,100);
update(2,80);
update(3,120);
```

State:

```text
timestampToPrice:
1 → 100
2 → 80
3 → 120

priceCount:
80 → 1
100 → 1
120 → 1

latest = 3
```

Results:

```java
current(); // 120
maximum(); // 120
minimum(); // 80
```

---

Update old timestamp:

```java
update(2,140);
```

Old:

```text
80 → 1
```

Remove:

```text
80 → 0 → delete
```

Add:

```text
140 → 1
```

New state:

```text
priceCount:
100 → 1
120 → 1
140 → 1
```

Results:

```java
current(); // 120
maximum(); // 140
minimum(); // 100
```

This is the whole trick: **timestamp → price** in HashMap, **price → frequency** in TreeMap.
