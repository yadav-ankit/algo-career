https://leetcode.com/problems/count-ways-to-build-good-strings/description/

The key observation:

You do not care about the actual string.
You only care about the length.
From any length i, you can go to:
i + zero
i + one

So define:

dp[i]=number of ways to build a string of length i

dp[i]=dp[i−zero]+dp[i−one]
  

class Solution {

    public int countGoodStrings(int low, int high, int zero, int one) {

        int MOD = 1_000_000_007;

        int[] dp = new int[high + 1];

        dp[0] = 1;

        int ans = 0;

        for (int i = 1; i <= high; i++) {

            if (i >= zero) {
                dp[i] = (dp[i] + dp[i - zero]) % MOD;
            }

            if (i >= one) {
                dp[i] = (dp[i] + dp[i - one]) % MOD;
            }

            if (i >= low) {
                ans = (ans + dp[i]) % MOD;
            }
        }

        return ans;
    }
}
