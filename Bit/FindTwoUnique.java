
# 🔍 Problem

**Given an array where exactly two numbers appear once and all others appear twice, find the two unique numbers.**

---

## 🧠 Core Insight (this is the magic)

1. XOR **all** elements

   * All duplicates cancel out
   * You’re left with:

     ```
     xor = unique1 ^ unique2
     ```

2. Since `unique1 ≠ unique2`, `xor` has **at least one set bit**

3. Use the **rightmost set bit** to split numbers into two groups

   * One unique goes to group A
   * The other unique goes to group B

Then XOR within each group → boom, both uniques appear.

This logic is *perfect*, and interviewers love it.

---

## 🔑 Key Bit Trick Used

```java
rightMostSetBit = xor & -xor;
```

Why this works:

* `-xor` is two’s complement
* AND-ing isolates the **lowest set bit**

---

## ✅ Optimal Java Solution (O(n), O(1))

```java
class Solution {
    public int[] findTwoUnique(int[] nums) {
        int xor = 0;

        // Step 1: XOR all numbers
        for (int x : nums) {
            xor ^= x;
        }

        // Step 2: Find rightmost set bit
        int mask = xor & -xor;

        int num1 = 0, num2 = 0;

        // Step 3: Partition numbers
        for (int x : nums) {
            if ((x & mask) != 0) {
                num1 ^= x;
            } else {
                num2 ^= x;
            }
        }

        return new int[]{num1, num2};
    }
}
```

---

## 🪜 Example Walkthrough

**Input:**

```text
[1, 2, 3, 2, 1, 4]
```

### Step 1: XOR all

```
xor = 3 ^ 4 = 7 (111)
```

### Step 2: Rightmost set bit

```
mask = 7 & -7 = 1 (001)
```

### Step 3: Partition

* Group 1 (bit set): `1, 3, 1`
* Group 2 (bit not set): `2, 2, 4`

XOR groups:

```
Group 1 → 3
Group 2 → 4
```

✅ Answer: **3 and 4**

Clean and deterministic.

---

## ⏱ Complexity (say this confidently)

| Metric | Value  |
| ------ | ------ |
| Time   | `O(n)` |
| Space  | `O(1)` |



---

## 🧠 Interview GOLD explanation (memorize this)

> “XORing all elements gives XOR of the two unique numbers. A set bit in that XOR helps partition the array 
  so duplicates cancel and each unique is isolated.”


