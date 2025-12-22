
💡 Example
weights = [1, 3, 4, 5]
values  = [1, 4, 5, 7]
W = 7


Best choice:

take weight 3 (value 4)

take weight 4 (value 5)

✅ Total value = 9
  
public int knapsack(int[] wt, int[] val, int W) {
    int n = wt.length;
    int[][] dp = new int[n + 1][W + 1];

    for (int i = 1; i <= n; i++) {
        for (int w = 0; w <= W; w++) {
            if (wt[i - 1] <= w) {
                dp[i][w] = Math.max(
                    dp[i - 1][w],
                    val[i - 1] + dp[i - 1][w - wt[i - 1]]
                );
            } else {
                dp[i][w] = dp[i - 1][w];
            }
        }
    }
    return dp[n][W];
}


--------------------

  🧾 Example (Unbounded)
weights = [2, 3]
values  = [4, 5]
capacity = 7


Best strategy:

2 + 2 + 3 = 7

4 + 4 + 5 = 13

0/1 could never do this because items repeat.
  
public int unboundedKnapsack(int[] wt, int[] val, int W) {
    int n = wt.length;
    int[][] dp = new int[n + 1][W + 1];

    for (int i = 1; i <= n; i++) {
        for (int w = 0; w <= W; w++) {
            if (wt[i - 1] <= w) {
                dp[i][w] = Math.max(
                    dp[i - 1][w],
                    val[i - 1] + dp[i][w - wt[i - 1]]
                );
            } else {
                dp[i][w] = dp[i - 1][w];
            }
        }
    }
    return dp[n][W];
}


📘 DP Formula Comparison
0/1 Knapsack
dp[i][w] = max(
    dp[i-1][w],                       // skip
    value[i] + dp[i-1][w - weight[i]] // take once
)

Unbounded Knapsack
dp[i][w] = max(
    dp[i-1][w],                     // skip
    value[i] + dp[i][w - weight[i]] // take again
)
