https://leetcode.com/problems/knight-probability-in-chessboard/description/

Each state explores 8 moves:

O(8 * n² * k)
≈ O(n² * k)

  Probability =
(sum of probabilities of all valid next moves) / 8
  
class Solution {

    int[][] dirs = {
            {-2, -1}, {-2, 1},
            {-1, -2}, {-1, 2},
            {1, -2}, {1, 2},
            {2, -1}, {2, 1}
    };

    Double[][][] dp;

    public double knightProbability(int n, int k, int row, int column) {

        dp = new Double[n][n][k + 1];

        return solve(n, k, row, column);
    }

    private double solve(int n, int k, int r, int c) {

        // outside board
        if (r < 0 || c < 0 || r >= n || c >= n) {
            return 0.0;
        }

        // no moves left
        if (k == 0) {
            return 1.0;
        }

        if (dp[r][c][k] != null) {
            return dp[r][c][k];
        }

        double prob = 0.0;

        for (int[] dir : dirs) {

            int nr = r + dir[0];
            int nc = c + dir[1];

            prob += solve(n, k - 1, nr, nc) / 8.0;
        }

        return dp[r][c][k] = prob;
    }
}


why go why 3d array again

Because the answer depends on 3 things simultaneously:

(row, col, remainingMoves)

So memoization state must include all 3.

Suppose you only store:

dp[row][col]

That would mean:

probability from this cell is always same

But that's false.

Example:

Same cell (2,2):

With:

k = 1

probability may be:

0.75

But with:

k = 10

probability becomes much smaller.

So:

same cell
different remaining moves
different answers

Hence moves count must be part of DP state.

So state is:

dp[r][c][k]

meaning:

probability of staying on board
starting from (r,c)
with k moves remaining

Think recursively:

solve(r, c, k)

calls:

solve(nextR, nextC, k - 1)

Since k changes every recursion, memoization must distinguish them.

Analogy

This is similar to:

dp[index][sum]
dp[row][col][steps]
dp[city][fuel]

Whenever future possibilities depend on a changing parameter, that parameter becomes part of state.

Here the changing parameter is:

remaining moves

so we need 3D DP.
