
import java.util.*;

class TUF {
    // Function to find the length of the Longest Common Subsequence (LCS)
    static int lcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // Create a 2D array to store results of subproblems
        int dp[][] = new int[n + 1][m + 1];

        // Initialize the dp array with -1 to indicate that subproblems are not solved yet
        for (int rows[] : dp)
            Arrays.fill(rows, -1);

        // Initialize the first row and first column with zeros since LCS with an empty string is zero
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }

        // Fill the dp array using dynamic programming
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // If the characters at the current indices are the same, increment the LCS length
                if (s1.charAt(i - 1) == s2.charAt(i - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                // If the characters are different, choose the maximum LCS length by either
                // excluding a character in s1 or excluding a character in s2
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[n][m]; // Return the length of the Longest Common Subsequence (LCS)
    }

    public static void main(String args[]) {
        String s1 = "acd";
        String s2 = "ced";

        // Call the lcs function and print the result
        System.out.println("The Length of Longest Common Subsequence is " + lcs(s1, s2));
    }
}


----------------

  for LC substring

 We have two conditions:

if(S1[i-1] != S2[j-1]), the characters don’t match, therefore the consecutiveness of characters is broken. So we set the cell value (dp[i][j]) as 0.
if(S1[i-1] == S2[j-1]), then the characters match and we simply set its value to 1+dp[i-1][j-1]. 
  We have done so because dp[i-1][j-1] gives us the longest common substring till the last cell 
  character (current strings -{matching character}). As the current cell’s character is matching we are adding 1 to the consecutive chain.
  

