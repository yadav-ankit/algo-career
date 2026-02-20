Given a binary string s, return the number of non-empty substrings that have the 
  same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.


Example 1:

Input: s = "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
Notice that some of these substrings repeat and are counted the number of times they occur.
Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.



public class Solution {

    public int countBinarySubstrings(String s) {

        int prevCount = 0;
        int currCount = 1;
        int result = 0;

        for (int i = 1; i < s.length(); i++) {

            if (s.charAt(i) == s.charAt(i - 1)) {
                currCount++;
            } else {
                result += Math.min(prevCount, currCount);
                prevCount = currCount;
                currCount = 1;
            }
        }

        // last group pair
        result += Math.min(prevCount, currCount);

        return result;
    }
}
