
https://leetcode.com/problems/flip-string-to-monotone-increasing/description/

class Solution {
    public int minFlipsMonoIncr(String s) {
        int ones = 0;
        int flips = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                ones++;
            } else {
                flips = Math.min(flips + 1, ones);
            }
        }

        return flips;
    }
}


Key Insight

While scanning the string:

if we see 1, no issue yet
if we later see 0, we have 2 choices:
flip this 0 -> 1
flip all previous 1 -> 0

We greedily keep the cheaper option.

Optimal DP / Greedy Solution

Maintain:

ones = number of 1s seen so far
flips = minimum flips needed till current index

Transition:

If current char is:

'1' → just increase ones
'0'
either flip current 0 → flips + 1
or flip all previous 1s → ones

Take minimum.

Example

s = "00110"

0 -> fine
0 -> fine
1 -> ones = 1
1 -> ones = 2
0 -> min(flips+1, ones)
     min(0+1, 2) = 1

Answer = 1
