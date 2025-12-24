

Swap
a = a ^ b;
b = a ^ b;
a = a ^ b;

Simple (overflow risk)
a = a + b;
b = a - b;
a = a - b;


# 🧠 BIT MANIPULATION MASTER TIPS (ULTIMATE)

---

## 🔹 1. Basic Bit Operators (must know)

| Operator | Meaning                      |    |
| -------- | ---------------------------- | -- |
| `&`      | AND                          |    |
| `        | `                            | OR |
| `^`      | XOR                          |    |
| `~`      | NOT                          |    |
| `<<`     | Left shift                   |    |
| `>>`     | Right shift (sign preserved) |    |
| `>>>`    | Right shift (zero fill)      |    |

---

## 🔹 2. Check if a number is even or odd

```java
if ((n & 1) == 0) // even
else // odd
```

✔️ Fastest possible check.

---

## 🔹 3. Check if ith bit is set

```java
boolean isSet = (n & (1 << i)) != 0;
```

---

## 🔹 4. Set ith bit

```java
n = n | (1 << i);
```

---

## 🔹 5. Clear ith bit

```java
n = n & ~(1 << i);
```

---

## 🔹 6. Toggle ith bit

```java
n = n ^ (1 << i);
```

---

## 🔹 7. Remove the lowest set bit (🔥 VERY IMPORTANT)

```java
n = n & (n - 1);
```

Uses:

* Count set bits
* Power of two check
* Subset generation

---

## 🔹 8. Check if number is power of 2

```java
n > 0 && (n & (n - 1)) == 0
```

---

## 🔹 9. Count set bits (Brian Kernighan)

```java
int count = 0;
while (n != 0) {
    n &= (n - 1);
    count++;
}
```

Interview favorite 💯

---



---

## 🔹 11. Find rightmost set bit

```java
n & -n
```

Used in:

* Divide numbers by bit groups
* XOR problems
* Fenwick Tree

---

## 🔹 12. Multiply / divide by powers of 2

```java
n << k   // n * 2^k
n >> k   // n / 2^k (positive n)
```

---

## 🔹 13. Convert uppercase ↔ lowercase (ASCII hack)

```java
char lower = (char)(c | 32);
char upper = (char)(c & ~32);
```

🔥 Very few people know this.

---

## 🔹 14. Check if two numbers have opposite signs

```java
(a ^ b) < 0
```

---

## 🔹 15. Clear bits from LSB to ith bit

```java
n & (-1 << (i + 1))
```

---

## 🔹 16. Clear bits from MSB to ith bit

```java
n & ((1 << i) - 1)
```

---

## 🔹 17. Get the lowest set bit index

```java
int pos = Integer.numberOfTrailingZeros(n);
```

(Standard library flex 💪)

---

## 🔹 18. Hamming Distance (bit flips)

```java
countBits(A ^ B)
```

---

## 🔹 19. Subset generation using bitmask

```java
for (int mask = 0; mask < (1 << n); mask++) {
    for (int i = 0; i < n; i++) {
        if ((mask & (1 << i)) != 0) {
            // include element i
        }
    }
}
```

---

## 🔹 20. Important Interview Patterns

| Problem Type           | Technique        |
| ---------------------- | ---------------- |
| Single / unique number | XOR              |
| Range queries          | Bitmask / prefix |
| Subsets                | Bitmask          |
| Power checks           | `n & (n - 1)`    |
| Counting               | Kernighan        |
| Flips                  | XOR + count      |

---


