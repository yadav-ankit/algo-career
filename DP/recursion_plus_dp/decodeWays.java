https://leetcode.com/problems/decode-ways/description/

Recursion tree

For:

226

Tree:

solve(0)
├── solve(1)
│     ├── solve(2)
│     │      └── solve(3)
│     └── solve(3)
└── solve(2)
       └── solve(3)

Notice:

solve(2)

appears twice.

Memoization saves us.
  
class Solution {

    int[] dp;

    public int numDecodings(String s) {

        int n = s.length();

        dp = new int[n];

        Arrays.fill(dp, -1);

        return solve(0, s);
    }

    private int solve(int idx, String s) {

        if (idx == s.length()) {
            return 1;
        }

        if (s.charAt(idx) == '0') {
            return 0;
        }

        if (dp[idx] != -1) {
            return dp[idx];
        }

        int ways = solve(idx + 1, s);

        if (idx + 1 < s.length()) {

            int num =
                (s.charAt(idx) - '0') * 10
                + (s.charAt(idx + 1) - '0');

            if (num >= 10 && num <= 26) {

                ways += solve(idx + 2, s);
            }
        }

        return dp[idx] = ways;
    }
}


if (idx == s.length()) {
            return 1;
        }

should we not return dp[n]

Good question. This is exactly where DP + recursion gets subtle.

We had:

if (idx == s.length()) {
    return 1;
}

You asked:

Why not return dp[n]?

Because:

idx == n

is a base case, not a memoized state.

Our DP array was:

dp = new int[n];

Valid indices:

0 ... n-1

There is:

no dp[n]
What does return 1 mean?

Suppose:

s = "226"

and recursion reaches:

solve(3)

Since:

3 == s.length()

we've successfully decoded everything.

Example:

2 | 26

After decoding:

26

next call:

solve(3)

Meaning:

No characters left.

That's one valid decoding.

So:

return 1;

means:

"I found one complete valid path."

Think of counting problems:

Base case usually returns:

1 = one successful way
0 = impossible way
