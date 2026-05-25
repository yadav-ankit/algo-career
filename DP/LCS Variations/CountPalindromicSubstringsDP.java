


Time:
O(n²)

Space:
O(n²)
  
public class CountPalindromicSubstringsDP {

    static int count(String s) {

        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int count = 0;

        // length = 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            count++;
        }

        // length = 2
        for (int i = 0; i < n - 1; i++) {

            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                count++;
            }
        }

        // length >= 3
        for (int len = 3; len <= n; len++) {

            for (int i = 0; i <= n - len; i++) {

                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }

        return count;
    }


    public static void main(String[] args) {

        System.out.println(
                count("aaa")
        );
    }
}



inner substring is palindrome:

dp[i + 1][j - 1]

how did u decide i+1 , j-1


This is the key insight in palindrome DP. Let's break it slowly.

Suppose:

s = "abccba"

Indices:

0 1 2 3 4 5
a b c c b a
↑         ↑
i         j

To decide:

Is s[i...j]
=
"abccba"
a palindrome?

First check:

Outer chars equal?

s[i] == s[j]

a == a
✓

Good.

Now remove those outer chars:

abccba
↓    ↓
bccb

Remaining substring:

i+1 ... j-1

1 ... 4

Need to know:

Is "bccb"
a palindrome?
