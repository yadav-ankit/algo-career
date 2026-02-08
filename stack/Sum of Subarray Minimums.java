This is **LeetCode 907 – Sum of Subarray Minimums**.
It looks hard, but there is a beautiful trick with **monotonic stacks**.

---

## Brute Force (what NOT to do)

Generate all subarrays, find min → **O(n²)** or **O(n³)**.
Too slow for `n` up to 30,000.

---

## Key Idea 

Instead of:

> “For every subarray, find its minimum”

We reverse it:

> “For every element `arr[i]`, count how many subarrays where `arr[i]` is the minimum”

Then:

```
answer += arr[i] * (number of subarrays where it is minimum)
```

---

## How many subarrays where arr[i] is minimum?

We find:

* how far it can extend to the **left**
* how far it can extend to the **right**
  while still being the smallest.

### Using Monotonic Stack

For each index `i`:

* `left[i]` = distance to **previous smaller element**
* `right[i]` = distance to **next smaller element**

Then:

```
count = left[i] * right[i]
contribution = arr[i] * count
```

---

## Example: [3,1,2,4]

For element `1` at index 1:

```
[3, 1, 2, 4]
     ^
```

It can extend:

* left: 2 positions
* right: 3 positions

So it is minimum in:

```
2 * 3 = 6 subarrays
```

Contribution:

```
1 * 6 = 6
```

Do this for all elements.

---

## Final Java Solution (Optimal O(n))

```java
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int MOD = 1_000_000_007;

        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> stack = new Stack<>();

        // Previous smaller (strict)
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Next smaller or equal
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long) arr[i] * left[i] * right[i]) % MOD;
        }

        return (int) ans;
    }
}
```

---

## Why the `>` and `>=` difference?

This avoids **double counting** when values are equal.

Rule:

* One side strict (`>`)
* Other side non-strict (`>=`)

This ensures each subarray has **exactly one minimum owner**.

---

## One-Line Intuition (to remember forever)

> Each number contributes to the answer as many times as the number of subarrays where it is the minimum.

---

## Complexity

* **Time:** `O(n)`
* **Space:** `O(n)`
