// https://www.geeksforgeeks.org/problems/count-digit-groupings-of-a-number1520/1


Example:

"1119"

Valid groupings:

1 | 1 | 1 | 9
1 | 1 | 19
1 | 11 | 9
111 | 9
11 | 19
1119

Answer = 6

Core DP Idea

At index idx:

form a group
compute its digit sum
next group must have sum >= previous group sum

So state becomes:

dp[idx][prevSum]

meaning:

Number of ways starting from idx
where previous group sum was prevSum

Recursive Relation

Suppose we form substring:

[idx ... j]

with digit sum = currSum

Allowed only if:

currSum >= prevSum

Then:

ways += solve(j + 1, currSum)


class Solution {

    int[][] dp;

    public int TotalCount(String str) {

        int n = str.length();

        // max possible digit sum = 9 * 100 = 900
        dp = new int[n][901];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solve(0, 0, str);
    }

    private int solve(int idx, int prevSum, String str) {

        if (idx == str.length()) {
            return 1;
        }

        if (dp[idx][prevSum] != -1) {
            return dp[idx][prevSum];
        }

        int ways = 0;
        int currSum = 0;

        for (int j = idx; j < str.length(); j++) {

            currSum += str.charAt(j) - '0';

            if (currSum >= prevSum) {
                ways += solve(j + 1, currSum, str);
            }
        }

        return dp[idx][prevSum] = ways;
    }
}





yes but we will never have 0 in our ans

You actually can have 0 ways for some states.

Not necessarily for the initial call, but for intermediate states.

Example idea:

solve(idx, prevSum)

might reach a situation where:

remaining digits cannot form a valid group
because every possible next group has sum < prevSum

Then:

ways = 0

Even if this specific problem often ends up having at least one valid path, memoization code is written generically and safely.

So we use:

-1 = uncomputed
0  = computed and answer is zero

to clearly separate meanings.

Think of it this way:

if (dp[idx][prevSum] != -1)

means:

Have we visited this state before?

NOT:

Does this state have nonzero answers?

Those are different questions.

Also, using 0 as unvisited is dangerous habitually because many DP problems genuinely have zero-answer states.

So competitive programmers almost always use:

-1

for memoization in counting problems.
