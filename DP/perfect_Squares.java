https://leetcode.com/problems/perfect-squares/description/

Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

Complexity
Time: O(n root n))
Space: O(n)

class Solution {

    public int numSquares(int n) {

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j * j <= i; j++) {

                dp[i] = Math.min(dp[i], dp[i - (j * j)] + 1);
            }
        }

        return dp[n];
    }
}


Because we are trying to build the answer for i using a smaller already-solved subproblem.



dp[i] = minimum squares needed to form i

Suppose:

i = 12

Now try every perfect square:

1, 4, 9

If we pick 4 (2*2):

12 = 4 + 8

So:

answer for 12
=
1 square we used (4)
+
minimum squares needed for remaining 8

That becomes:

dp[12] = dp[8] + 1
