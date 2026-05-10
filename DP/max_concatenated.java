// https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/description/


Core Idea

At every index:

Take current string
OR
Skip current string

So recursion tree becomes:

                    ""
               /          \
            "un"           ""
          /     \        /    \
      "uniq"   "un"   "iq"    ""

We only continue if resulting string has all unique chars.

Complexity

There are 2^n subsets:

Time:  O(2^n * 26)
Space: O(n)

Since:

n <= 16

this works perfectly.

  
class Solution {

    public int maxLength(List<String> arr) {
        return solve(0, arr, "");
    }

    private int solve(int index, List<String> arr, String temp) {

        // invalid string
        if (!isUnique(temp)) {
            return 0;
        }

        // reached end
        if (index == arr.size()) {
            return temp.length();
        }

        // include current string
        int include = solve(index + 1, arr, temp + arr.get(index));

        // exclude current string
        int exclude = solve(index + 1, arr, temp);

        return Math.max(include, exclude);
    }

    private boolean isUnique(String s) {

        boolean[] seen = new boolean[26];

        for (char ch : s.toCharArray()) {

            if (seen[ch - 'a']) {
                return false;
            }

            seen[ch - 'a'] = true;
        }

        return true;
    }
}
