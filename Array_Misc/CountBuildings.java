
https://leetcode.com/problems/number-of-ways-to-select-buildings/


Example

s = "001101"

Valid selections:

"010" → (0,2,3), (0,2,4), (1,2,3), (1,2,4)

"101" → (2,3,5), (2,4,5)

Answer = 6


  Visual Example

s = "001101"

Take index 2 (value = '1'):

0 0 | 1 | 1 0 1
^ ^   ^   ^ ^ ^
left     right


Left side has: 2 zeros
Right side has: 1 zero

So ways = 2 * 1 = 2

These are:

(0,2,4)
(1,2,4)

Why counting works

For each middle:

every left choice can pair with every right choice

So we multiply.
class Solution {
    public long numberOfWays(String s) {
        int n = s.length();
        
        long left0 = 0, left1 = 0;
        long right0 = 0, right1 = 0;
        
        // Count total right0 and right1
        for (char c : s.toCharArray()) {
            if (c == '0') right0++;
            else right1++;
        }
        
        long ways = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '0') {
                right0--;
                // "101"
                ways += left1 * right1;
                left0++;
            } else {
                right1--;
                // "010"
                ways += left0 * right0;
                left1++;
            }
        }
        
        return ways;
    }
}
