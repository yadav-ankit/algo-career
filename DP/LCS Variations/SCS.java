
public class ShortestCommonSupersequence {

    public static String scs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        // Build LCS table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];

                else
                    dp[i][j] =
                            Math.max(dp[i - 1][j],
                                     dp[i][j - 1]);
            }
        }

        StringBuilder ans = new StringBuilder();

        int i = n;
        int j = m;

        while (i > 0 && j > 0) {

            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                ans.append(s1.charAt(i - 1));
                i--;
                j--;
            }

            else if (dp[i - 1][j] > dp[i][j - 1]) {
                ans.append(s1.charAt(i - 1));
                i--;
            }

            else {
                ans.append(s2.charAt(j - 1));
                j--;
            }
        }

        while (i > 0)
            ans.append(s1.charAt(--i));

        while (j > 0)
            ans.append(s2.charAt(--j));

        return ans.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(
                scs("abac", "cab")
        );
    }
}

Think opposite of LCS:

For:

A = abcde
B = ace

LCS:

ace

Length = 3

SCS:

Need all characters from both strings, but common chars should come once.

So:

SCS length =
A.length + B.length − LCS length


DP approach (construct actual string)

We first compute LCS table.

DP meaning:

dp[i][j]
=
LCS length of
A[0...i-1]
B[0...j-1]

Then backtrack:

If chars match:

take char once
i--
j--

Else:

Take direction with larger LCS.
