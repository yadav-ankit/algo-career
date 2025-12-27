https://leetcode.com/problems/maximum-frequency-of-an-element-after-performing-operations-i/description/
https://www.youtube.com/watch?v=BaEebscdBJM&list=PLpIkg8OmuX-Kqkb8DqDe_4-Tiav6ilS_L&index=6

class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {

        // Find maximum value to size arrays
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        // freq[v] = how many times v already appears
        int[] freq = new int[maxVal + 1];

        // diff[v] = difference array for reachable ranges
        int[] diff = new int[maxVal + 2]; // +2 for r+1 safety

        // Step 1: Build freq[] and diff[] ranges
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            freq[val]++;

            // Range of values this number can be converted to
            int l = Math.max(val - k, 0);
            int r = Math.min(val + k, maxVal);

            diff[l]++;        // range starts
            diff[r + 1]--;    // range ends
        }

        int result = 1;
        int activeRanges = 0; // prefix sum over diff[]

        // Step 2: Sweep all possible target values
        for (int target = 0; target <= maxVal; target++) {

            // Prefix sum to get active ranges at 'target'
            activeRanges += diff[target];

            // How many already equal to target
            int targetFreq = freq[target];

            // How many others can be converted to target
            int needConversion = activeRanges - targetFreq;

            // We can only use numOperations changes
            int maxPossibleFreq =
                    Math.min(needConversion, numOperations);

            // Update answer
            result = Math.max(
                    result,
                    targetFreq + maxPossibleFreq
            );
        }

        return result;
    }
}


---

## How to read this code (important)

### `diff[]`

* Counts **how many numbers can reach this value**
* Built using range `[x-k, x+k]`

### `activeRanges`

* Prefix sum of `diff`
* Means: *“how many total numbers can become this value”*

### `needConversion`

```
activeRanges - alreadyEqual
```

Because:

* already equal → no operation
* rest → 1 operation each

### Final frequency

```
alreadyEqual + min(numOperations, canConvert)
```

---

## Why this is efficient

* No sorting
* No nested loops
* Pure prefix sums

**Time:** `O(n + max(nums))`
**Space:** `O(max(nums))`

---

## Interview one-liner (very strong)

> “I convert each element into a reachable value range, use a difference array to 
  count coverage per value, then cap conversions using numOperations.”


