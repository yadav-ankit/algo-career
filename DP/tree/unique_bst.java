https://leetcode.com/problems/unique-binary-search-trees/description/

For each number i chosen as the root:

Left subtree must use values 1 ... i-1
Right subtree must use values i+1 ... n
Number of possible left BSTs = dp[i-1]
Number of possible right BSTs = dp[n-i]

Since every left BST can pair with every right BST:

BSTs with root i
= dp[i-1] * dp[n-i]

Sum over all possible roots:

dp[n] = Σ dp[i-1] * dp[n-i]
        for i = 1 to n

This is the famous Catalan Number recurrence.
  
class Solution {

    public int numTrees(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {

            for (int j = 0; j < i; j++) {
              // left node = j
              // right nodes = total nodes - root node - left nodes
                dp[i] += dp[j] * dp[i - 1 - j]; 
            }

        }

        return dp[n];
    }
}
