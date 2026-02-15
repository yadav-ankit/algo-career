🏠 Paint House I — What’s Different?

In Paint House I:

You still have houses in a row

Adjacent houses cannot have same color

BUT there are only 3 colors:

Red, Blue, Green


That’s it. Fixed. Always 3.

🧠 What Are We Given?
costs[i][0] → cost to paint house i Red
costs[i][1] → cost to paint house i Blue
costs[i][2] → cost to paint house i Green

🎯 Goal

Paint all houses with minimum cost
No two adjacent houses same color.

class Solution {
    public int minCost(int[][] costs) {

        if (costs == null || costs.length == 0) return 0;

        int n = costs.length;
        int[][] dp = new int[n][3];

        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];

        for (int i = 1; i < n; i++) {
            dp[i][0] = costs[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        return Math.min(dp[n-1][0],
               Math.min(dp[n-1][1], dp[n-1][2]));
    }
}
