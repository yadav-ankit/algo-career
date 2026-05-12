
https://leetcode.com/problems/reducing-dishes/description/

why int[][] dp = new int[n + 1][n + 2];

Because of this transition:

dp[idx + 1][time + 1]

Both indices can go beyond normal ranges.

Dimensions Meaning
dp[idx][time]

where:

idx ranges from 0 -> n
time ranges from 1 -> n+1

  also in Bottom Up DP why are doing idx +1 in 

int take = satisfaction[idx] * time + dp[idx + 1][time + 1];

we should take current index only

  Because the DP state means:

dp[idx][time]
=
best answer starting FROM idx
when current cooking time = time

So once we decide to TAKE current dish:

satisfaction[idx] * time

current dish is already used.

Now we must move to:

next index
  
class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);

        int n = satisfaction.length;

        int[][] dp = new int[n + 1][n + 2];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int time = idx + 1; time >= 1; time--) {

                int take = satisfaction[idx] * time
                        + dp[idx + 1][time + 1];

                int skip = dp[idx + 1][time];

                dp[idx][time] = Math.max(take, skip);
            }
        }

        return dp[0][1];
    }
}
