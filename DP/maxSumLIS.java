Given an array of positive integers arr[], find the maximum sum of a subsequence such that the elements of the subsequence form a strictly increasing sequence.
In other words, among all strictly increasing subsequences of the array, return the one with the largest possible sum.

Examples:

Input: arr[] = [1, 101, 2, 3, 100]
Output: 106
Explanation: The maximum sum of an increasing sequence is obtained from [1, 2, 3, 100].

class Solution {
    public int maxSumIS(int[] arr, int n) {
        int[] dp = new int[n];

        // Step 1: initialization
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
        }

        // Step 2: build dp
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
        }

        // Step 3: find max sum
        int maxSum = 0;
        for (int val : dp) {
            maxSum = Math.max(maxSum, val);
        }

        return maxSum;
    }
}
