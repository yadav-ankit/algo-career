
Find longest subsequence of A that appears as a contiguous substring in B.

  Example:

A = "abcd"
B = "bacdbdcd"

  Need contiguous in B.

Actual substring:

"cd"
  
public class LongestSubsequenceSubstring {

    static int solve(String a, String b) {

        int n = a.length();
        int m = b.length();

        int[][] dp =  new int[n + 1][m + 1];

        int ans = 0;

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                if (a.charAt(i - 1) == b.charAt(j - 1)) {

                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }

                else {

                    dp[i][j] = dp[i - 1][j];
                }

                ans = Math.max(ans,dp[i][j]);
            }
        }

        return ans;
    }


    public static void main(String[] args) {

        System.out.println(solve("abcd",  "bacdbdcd"));
    }
}

Intuition

This is NOT standard LCS.

Standard LCS:

match →
1 + dp[i-1][j-1]

and mismatch:

max(
dp[i-1][j],
dp[i][j-1]
)

But here:

Need:

skip chars in A → subsequence allowed
cannot skip chars in B → substring required

Meaning:

On mismatch:

We can only move in A.

So recurrence:

If:

A[i-1]==B[j-1]

then:

dp[i][j]
=
1 + dp[i-1][j-1]

Else:

dp[i][j]
=
dp[i-1][j]

Why?

Because:

A → subsequence
(skip allowed)

B → substring
(skip NOT allowed)

This is the trick.
