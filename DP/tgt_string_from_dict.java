https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/description/

too much confusion in m , n , i , j = col here

class Solution {

    long MOD = 1_000_000_007L;

    public int numWays(String[] words, String target) {

        int m = words[0].length();
        int n = target.length();

        int[][] freq = new int[m][26];

        // count chars column-wise
        for (String word : words) {
            for (int col = 0; col < m; col++) {
                freq[col][word.charAt(col) - 'a']++;
            }
        }

        Long[][] dp = new Long[n][m];

        return (int) solve(0, 0, target, freq, dp, m, n);
    }

    private long solve(int i, int col,
                       String target,
                       int[][] freq,
                       Long[][] dp,
                       int m,
                       int n) {

        // formed target
        if (i == n) {
            return 1;
        }

        // no columns left
        if (col == m) {
            return 0;
        }

        if (dp[i][col] != null) {
            return dp[i][col];
        }

        long skip = solve(i, col + 1, target, freq, dp, m, n);

        long take = 0;

        int count = freq[col][target.charAt(i) - 'a'];

        if (count > 0) {
            take = (count * solve(i + 1, col + 1,
                    target, freq, dp, m, n)) % MOD;
        }

        return dp[i][col] = (skip + take) % MOD;
    }
}


why we are concered with freq...we can have 1 letter from string_1 and 2 from string_2

That’s exactly why freq works 😄

We are not locking ourselves to one specific string.

At every column, you can choose the character from any word.

So instead of caring which word gives the character, we only care:

how many choices exist at this column

Example:

words = ["acca","bbbb","caca"]
target = "aba"

Look at column 0:

a
b
c

Frequency becomes:

a -> 1
b -> 1
c -> 1

Now suppose target needs 'a'.

There is exactly:

1 way

to pick 'a' from this column.

Now look at column 3:

a
b
a

Frequency:

a -> 2
b -> 1

If target needs 'a' here:

you have:

2 independent choices
choose from "acca"
choose from "caca"

So we multiply by 2.

Why word identity doesn't matter

Suppose:

pick target[0] from word1
pick target[1] from word2
pick target[2] from word1

Totally allowed.

The problem only restricts:

column order

NOT:

same word usage

So once we move past a column, the actual word used earlier becomes irrelevant.

Only the number of available choices matters.

That’s why compressing into frequencies is valid.
