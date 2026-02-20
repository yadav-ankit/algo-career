https://leetcode.com/problems/maximize-the-confusion-of-an-exam/description/


Instead of trying all possibilities, we:

👉 Find the longest window that can become all 'T' with at most k flips.
👉 Find the longest window that can become all 'F' with at most k flips.

Answer = max(both cases).

🎯 Sliding Window Idea

For a target character ('T' or 'F'):

Expand right pointer.

Count how many characters inside window don’t match target.

If count > k → shrink from left.

Track max window size.

class Solution {

    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(
            maxWindow(answerKey, k, 'T'),
            maxWindow(answerKey, k, 'F')
        );
    }

    private int maxWindow(String s, int k, char target) {
        int i = 0;           // left pointer
        int maxLen = 0;
        int changes = 0;

        for (int j = 0; j < s.length(); j++) {  // right pointer
            if (s.charAt(j) != target) {
                changes++;
            }

            while (changes > k) {
                if (s.charAt(i) != target) {
                    changes--;
                }
                i++;
            }

            maxLen = Math.max(maxLen, j - i + 1);
        }

        return maxLen;
    }
}
