
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

  
  Reframe
total0 = total zeros in string
total1 = total ones in string

As you walk index i:

right0 = total0 - left0 - (1 if s[i]=='0')
right1 = total1 - left1 - (1 if s[i]=='1')

So you only track left0 and left1. Right is derived.

  
class Solution {
  public long numberOfWays(String s) {
    long total0 = 0, total1 = 0;
    for (char c : s.toCharArray()) {
        if (c == '0') total0++; else total1++;
    }

    long left0 = 0, left1 = 0, ways = 0;

    for (char c : s.toCharArray()) {
        if (c == '1') {
            long right0 = total0 - left0;         // zeros remaining to right
            ways += left0 * right0;               // "010"
            left1++;
        } else {
            long right1 = total1 - left1;         // ones remaining to right
            ways += left1 * right1;               // "101"
            left0++;
        }
    }
    return ways;
}
}
