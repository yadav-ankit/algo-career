// https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k/description/



You are given a 0-indexed m x n integer matrix grid and an integer k. You are currently at position (0, 0) and you want to reach position (m - 1, n - 1) moving only down or right.

Return the number of paths where the sum of the elements on the path is divisible by k. Since the answer may be very large, return it modulo 109 + 7.


dp[i][j][r] = number of ways to reach (i, j)
              with sum % k = r

👉 This is why it's 3D DP


class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int MOD = 1_000_000_007;

        int[][][] dp = new int[m][n][k];

        dp[0][0][grid[0][0] % k] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int r = 0; r < k; r++) {

                    if (i == 0 && j == 0) continue;

                    int val = grid[i][j];

                    // from top
                    if (i > 0) {
                        int prevR = r;
                        int newR = (prevR + val) % k;
                        dp[i][j][newR] = (dp[i][j][newR] + dp[i-1][j][prevR]) % MOD;
                    }

                    // from left
                    if (j > 0) {
                        int prevR = r;
                        int newR = (prevR + val) % k;
                        dp[i][j][newR] = (dp[i][j][newR] + dp[i][j-1][prevR]) % MOD;
                    }
                }
            }
        }

        return dp[m-1][n-1][0];
    }
}
