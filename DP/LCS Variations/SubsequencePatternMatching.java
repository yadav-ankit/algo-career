Count how many times pattern P appears as a subsequence in string S.

example:

S = "tomorrow"
P = "tor"

Possible:

t o m o r r o w
↑ ↑     ↑

t o m o r r o w
↑   ↑   ↑

Answer:

2
  
public class SubsequencePatternMatching {

    static int countWays(String s, String p) {

        int n = s.length();
        int m = p.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                if (s.charAt(i - 1) == p.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];

                else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        return dp[n][m];
    }


    public static void main(String[] args) {

        System.out.println(countWays( "baxmx","ax"));
    }
}



DP intuition

Need:

count ways

Not:

max length

So DP changes.


  Transition

If chars match:

s[i-1]==p[j-1]

Two choices:

Take current char:

dp[i-1][j-1]

Skip current char:

dp[i-1][j]

Therefore:

dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];

If mismatch:

Cannot take:

Only skip:

dp[i][j] = dp[i - 1][j];
