https://leetcode.com/problems/partition-array-for-maximum-sum/description/

Intuition — DP

Define dp[i] = max sum for the first i elements.

For each position i, look back up to k steps and ask:

"What if the last partition ends at i and starts at j?"

That partition has length i - j, max = max(arr[j..i-1]), contribution = max × (i - j).

dp[i] = max over j in [i-k .. i-1] of:
            dp[j] + max(arr[j..i-1]) × (i - j)
    

```java
class Solution {

   public static int maxSumAfterPartitioning(int[] arr, int k) {
    int n = arr.length;
    int[] dp = new int[n + 1];

    for (int i = 1; i <= n; i++) {
       
        int max = 0;
        
        // try all partition lengths 1..k ending at i
        
        for (int len = 1; len <= k && i - len >= 0; len++) {
            
            
            max = Math.max(max, arr[i - len]);       // max of current window
            
            dp[i] = Math.max(dp[i], dp[i - len] + max * len);
        }
    }
    return dp[n];
}
``
