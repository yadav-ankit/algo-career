https://leetcode.com/problems/word-break/description/

Typical optimized complexity becomes:

Time: O(n²)
Space: O(n)

  dp[idx] = Can substring from idx be segmented?
  
class Solution {

    int[] dp;
    Set<String> set;

    public boolean wordBreak(String s, List<String> wordDict) {

        dp = new int[s.length()];
        Arrays.fill(dp, -1);

        set = new HashSet<>(wordDict);

        return solve(0, s);
    }

    private boolean solve(int idx, String s) {

        if (idx == s.length()) {
            return true;
        }

        if (dp[idx] != -1) {
            return dp[idx] == 1;
        }

        for (int j = idx; j < s.length(); j++) {

            String word = s.substring(idx, j + 1);

            if (set.contains(word) && solve(j + 1, s)) {

                dp[idx] = 1;
                return true;
            }
        }

        dp[idx] = 0;

        return false;
    }
}
