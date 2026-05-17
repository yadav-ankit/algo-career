https://leetcode.com/problems/burst-balloons/description/

Total:

Time = O(n³)
Space = O(n²)

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];

        arr[0] = 1;
        arr[n + 1] = 1;

        for (int i = 0; i < n; i++) arr[i + 1] = nums[i];

        int[][] dp = new int[n + 2][n + 2];

        for (int len = 2; len < n + 2; len++) {
            for (int left = 0; left + len < n + 2; left++) {
                int right = left + len;

                for (int k = left + 1; k < right; k++) {
                    dp[left][right] =
                        Math.max(
                            dp[left][right],
                            dp[left][k]
                            + dp[k][right]
                            + arr[left] * arr[k] * arr[right]
                        );
                }
            }
        }

        return dp[0][n + 1];
    }
}


Trick: Add boundaries

Add virtual balloons:

nums = [1,3,1,5,8,1]

Now:

dp[i][j]
=
max coins from bursting balloons between i and j
(exclusive)

Meaning:

(i,j)

are boundaries NOT burst.

Example:

dp[1][4]

means solve:

3 1 5 8

with boundaries:
1       8
State
dp[left][right]
=
maximum coins obtainable
between left and right
(exclusive)
Transition

Suppose balloon k is burst last:

Then:

left ... k ... right

By then:

Everything inside left→k and k→right gone.

So gain:

nums[left] * nums[k] * nums[right]

plus:

dp[left][k]
+
dp[k][right]

Therefore:

dp[left][right]
=
max(
    dp[left][k]
    +
    dp[k][right]
    +
    nums[left]*nums[k]*nums[right]
)

for:

left < k < right
Example

[3,1,5]

After padding:

[1,3,1,5,1]

If bursting 1 last:

Gain:

3*1*5
+
left interval
+
right interval

Try all last balloons → take max.
