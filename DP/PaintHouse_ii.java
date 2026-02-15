costs =
[
  [1, 5, 3],   // House 0
  [2, 9, 4],   // House 1
  [3, 6, 1]    // House 2
]





DP 
⏱ Complexity

Time: O(n · k²)

Space: O(n · k)

  class Solution {
    public int minCostII(int[][] costs) {

        if (costs == null || costs.length == 0) return 0;

        int n = costs.length;
        int k = costs[0].length;

        int[][] dp = new int[n][k];

        // Base case
        for (int j = 0; j < k; j++) {
            dp[0][j] = costs[0][j];
        }

        // Fill DP
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {

                int minPrev = Integer.MAX_VALUE;

                for (int c = 0; c < k; c++) {
                    if (c == j) continue;
                    minPrev = Math.min(minPrev, dp[i-1][c]);
                }

                dp[i][j] = costs[i][j] + minPrev;
            }
        }

        // Find answer
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) {
            ans = Math.min(ans, dp[n-1][j]);
        }

        return ans;
    }
}


O(N*K)

  🧠 Big Insight

From the previous house, we only need to remember:

✅ The smallest cost
✅ The second smallest cost
✅ The color index of the smallest

That’s enough.

Why?

Because when painting current house:

If current color ≠ previous min color → use previous min

If current color == previous min color → must use second min

No scanning needed.
class Solution {
    public int minCostII(int[][] costs) {

        if (costs == null || costs.length == 0) return 0;

        int prevMin = 0;
        int prevSecondMin = 0;
        int prevMinIndex = -1;

        for (int i = 0; i < costs.length; i++) {

            int currMin = Integer.MAX_VALUE;
            int currSecondMin = Integer.MAX_VALUE;
            int currMinIndex = -1;

            for (int j = 0; j < costs[0].length; j++) {

                int cost = costs[i][j];

                if (j == prevMinIndex) {
                    cost += prevSecondMin;
                } else {
                    cost += prevMin;
                }

                if (cost < currMin) {
                    currSecondMin = currMin;
                    currMin = cost;
                    currMinIndex = j;
                } 
                else if (cost < currSecondMin) {
                    currSecondMin = cost;
                }
            }

            prevMin = currMin;
            prevSecondMin = currSecondMin;
            prevMinIndex = currMinIndex;
        }

        return prevMin;
    }
}



🏠 What is the Paint House II Problem?

You are given several houses in a row.

Each house must be painted one color.

For each house, painting with each color has a different cost.

Example Visualization

Imagine:

House 0     House 1     House 2


And available colors:

Red, Blue, Green


You are given a table:

costs[i][j]
i = house number
j = color number


Example:

costs =
[
  [1, 5, 3],   // House 0
  [2, 9, 4],   // House 1
  [3, 6, 1]    // House 2
]


Meaning:

House 0:

Red costs 1

Blue costs 5

Green costs 3

House 1:

Red costs 2

Blue costs 9

Green costs 4

House 2:

Red costs 3

Blue costs 6

Green costs 1

🚨 The Rule (Very Important)

Two adjacent houses cannot have the same color.

So you cannot paint:

House 0 → Red
House 1 → Red   ❌ NOT allowed

🎯 Goal

Choose colors for each house so that:

✔ No two neighboring houses have same color
✔ Total painting cost is as small as possible

Return that minimum total cost.
