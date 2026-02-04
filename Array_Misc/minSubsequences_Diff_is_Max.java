https://github.com/doocs/leetcode/blob/main/solution/2200-2299/2294.Partition%20Array%20Such%20That%20Maximum%20Difference%20Is%20K/README_EN.md

You are given an integer array nums and an integer k. You may partition nums into one or more subsequences such that each element in nums appears in exactly one of the subsequences.

Return the minimum number of subsequences needed such that the difference between the maximum and minimum values in each subsequence is at most k.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

 

Example 1:

Input: nums = [3,6,1,2,5], k = 2
Output: 2
Explanation:
We can partition nums into the two subsequences [3,1,2] and [6,5].
The difference between the maximum and minimum value in the first subsequence is 3 - 1 = 2.
The difference between the maximum and minimum value in the second subsequence is 6 - 5 = 1.
Since two subsequences were created, we return 2. It can be shown that 2 is the minimum number of subsequences needed.

-------------

## Key insight

* The **order doesn’t matter** for the constraint
  👉 only `max − min ≤ k` matters inside each subsequence.
* Since subsequences don’t need to be contiguous, we’re free to **rearrange mentally**.

So the smartest move is:

> **Sort the array first.**

Once sorted, the best way to minimize the number of subsequences is to:

* pack as many numbers as possible into one subsequence
* then start a new one only when the condition breaks

This is a greedy strategy, and it’s optimal.

---

## Greedy strategy (after sorting)

1. Sort `nums`
2. Start a new subsequence with the **smallest unused element**
3. Keep adding elements **as long as**

   ```
   current_value − start_value ≤ k
   ```
4. When it breaks, start a **new subsequence**
5. Count how many times you start a new one


## Example walkthrough

### Input

```
nums = [3, 6, 1, 2, 5]
k = 2
```

### Step 1: Sort

```
[1, 2, 3, 5, 6]
```

### Step 2: Build subsequences

* Start with `1`

  * `2 − 1 = 1 ≤ 2` ✅
  * `3 − 1 = 2 ≤ 2` ✅
  * `5 − 1 = 4 > 2` ❌ → new subsequence
* Start with `5`

  * `6 − 5 = 1 ≤ 2` ✅

### Result

```
Subsequence 1: [1,2,3]
Subsequence 2: [5,6]
```

✅ Answer = **2**

---


```java
public int minSubsequences(int[] nums, int k) {
    Arrays.sort(nums);

    int count = 1;
    int start = nums[0];

    for (int i = 1; i < nums.length; i++) {
        if (nums[i] - start > k) {
            count++;
            start = nums[i];
        }
    }
    return count;
}
```

---

## Time & Space Complexity

* **Time:** `O(n log n)` (sorting)
* **Space:** `O(1)` (ignoring sort internals)

