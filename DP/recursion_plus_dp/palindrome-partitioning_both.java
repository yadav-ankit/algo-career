
https://leetcode.com/problems/palindrome-partitioning-ii/description/

Palindrome Partitioning II (basic DP)
Time O(n³)
Space O(n)
    
class Solution {

    int[] dp;

    public int minCut(String s) {

        int n = s.length();

        dp = new int[n];

        Arrays.fill(dp, -1);

        return solve(0, s) - 1;
    }

    private int solve(int idx, String s) {

        if (idx == s.length()) {
            return 0;
        }

        if (dp[idx] != -1) {
            return dp[idx];
        }

        int minCuts = Integer.MAX_VALUE;

        for (int j = idx; j < s.length(); j++) {

            if (isPalindrome(s, idx, j)) {
                minCuts = Math.min(minCuts,1 + solve(j + 1, s));
            }
        }

        return dp[idx] = minCuts;
    }

    private boolean isPalindrome(String s,int l,int r) {
        while (l < r) {
            if (s.charAt(l)!= s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}


Partition into palindromes with minimum cuts.

Example:

"a | a | b"

cuts:

2

Better:

"aa | b"

cuts:

Answer:

1
Step 1: Define recursive state

Let:

solve(idx)

mean:

Minimum cuts needed for substring starting at idx

Original problem:

solve(0)
Step 2: Think recursively

At index idx, try every partition:

Example:

s = "aab"

At:

solve(0)

Possible partitions:

Take:

"a"

Palindrome ✓

Need:

1 + solve(1)

Take:

"aa"

Palindrome ✓

Need:

1 + solve(2)

Take:

"aab"

Not palindrome ✗

Skip

So:

solve(0)
=
min(
1+solve(1),
1+solve(2)
)
Base case

If:

idx == n

then:

Need:

0 cuts

because nothing remains.

Return:

0
Memoization DP

State:

solve(idx)

Memo:

dp[idx]

  -----------------------------------------

https://leetcode.com/problems/palindrome-partitioning/description/
   
Complexity	Value
Time	O(n * 2^n)
Aux Space	O(n)
    

    class Solution {

    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> partition(String s) {
        solve(0, s, new ArrayList<>());
        return ans;
    }

    private void solve(int idx, String s, List<String> path) {

        if (idx == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int j = idx; j < s.length(); j++) {

            if (isPalindrome(s, idx, j)) {

                path.add(s.substring(idx, j + 1));

                solve(j + 1, s, path);

                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int l, int r) {

        while (l < r) {

            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }

            l++;
            r--;
        }

        return true;
    }
}
