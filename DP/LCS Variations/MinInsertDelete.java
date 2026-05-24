
Minimum number of insertions and deletions to convert A → B

  
Formula to remember

If:

L = LCS(A,B)

Then:

Minimum deletions =
A.length − L
Minimum insertions =
B.length − L

public class MinInsertDelete {

    static int lcs(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1))

                    dp[i][j] = 1 + dp[i - 1][j - 1];

                else

                    dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1]);
            }
        }

        return dp[n][m];
    }


    static void solve(String a, String b) {

        int lcs = lcs(a, b);

        int deletions = a.length() - lcs;

        int insertions = b.length() - lcs;

        System.out.println("Deletion = " + deletions);

        System.out.println("Insertion = " + insertions);
    }


    public static void main(String[] args) {

        solve("heap","pea");
    }
}



This one is pure LCS.

Problem:

Convert string A into B using only:

insertion
deletion

Need minimum operations.

Example:

A = "heap"
B = "pea"

Convert:

heap
↓ delete h
eap
↓ delete e
ap
↓ insert p
pea

Answer:

Deletions = 2
Insertions = 1
Total = 3
Intuition

Suppose:

A = heap
B = pea

Common subsequence:

LCS = ea

Length:

2

Keep those chars.

Everything else:

Remove from A:

heap
^^
remove h,p

deletions =
A.length − LCS
=
4 − 2
=
2

Add missing chars to reach B:

pea
^
insert p

insertions =
B.length − LCS
=
3 − 2
=
1
Formula to remember

If:

L = LCS(A,B)

Then:

Minimum deletions =
A.length − L
Minimum insertions =
B.length − L

Total:

(A.length − L)
+
(B.length − L)
