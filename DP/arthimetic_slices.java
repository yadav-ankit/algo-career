https://leetcode.com/problems/arithmetic-slices-ii-subsequence/description/


Key Insight
count     -> existing sequences of length >= 2
ans += count -> after adding nums[i], they become length >= 3
+1        -> new pair created with (j, i)

Example:

[2,4] + 6 -> [2,4,6]

[2,4] was stored earlier as a length-2 sequence.


class Solution {

    public int numberOfArithmeticSlices(int[] nums) {

        int n = nums.length;

        // dp[i] stores:
        // difference -> count of arithmetic subsequences
        // ending at index i with that difference
        Map<Long, Integer>[] dp = new HashMap[n];

        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }

        int ans = 0;

        // Try every pair (j, i)
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                // Use long to avoid integer overflow
                long diff = (long) nums[i] - nums[j];

                // Number of subsequences ending at j
                // having same difference
                int count = dp[j].getOrDefault(diff, 0);

                // Extending all those subsequences
                // creates valid subsequences of length >= 3
                ans += count;

                // Store:
                // 1. Existing subsequences extended by nums[i]
                // 2. New pair (nums[j], nums[i])
                dp[i].put(diff, dp[i].getOrDefault(diff, 0) + count + 1);
            }
        }

        return ans;
    }
}


--------------------
https://leetcode.com/problems/arithmetic-slices/description/
    
An integer array is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
Given an integer array nums, return the number of arithmetic subarrays of nums.

A subarray is a contiguous subsequence of the array.


    
    class Solution {

    public int numberOfArithmeticSlices(int[] nums) {

        int n = nums.length;

        if (n < 3) {
            return 0;
        }

        int curr = 0;
        int ans = 0;

        for (int i = 2; i < n; i++) {

            // Check if current triplet forms arithmetic sequence
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {

                // Extend previous arithmetic slices
                curr++;

                ans += curr;

            } else {

                // Break in arithmetic pattern
                curr = 0;
            }
        }

        return ans;
    }
}
