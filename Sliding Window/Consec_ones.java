https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/description/


🧠 Core Insight

Instead of counting swaps directly:

👉 Count how many 1s already appear inside a window of size = total number of 1s

Then:

minimum swaps = window_size - max_ones_in_any_window


Because those missing 1s must be swapped in.

  
✅ Algorithm

Count total ones → k

Create window of size k

Slide over array twice length

Track max ones inside window

Answer = k - maxOnes
  
class Solution {
    public int minSwaps(int[] nums) {
        int k = 0;
        for (int n : nums) {
            if (n == 1) k++;
        }

        // Edge case: no swaps needed
        if (ones == 0) return 0;

        int left = 0, currOnes = 0, maxOnes = 0;
        int n = nums.length;

        // Sliding window over circular array
        for (int right = 0; right < 2 * n; right++) {

            if (nums[right % n] == 1)
                currOnes++;

            // Maintain window size = ones
            if (right - left + 1 > k) {
                if (nums[left % n] == 1)
                    currOnes--;
                left++;
            }

            maxOnes = Math.max(maxOnes, currOnes);
        }

        return k - maxOnes;
    }
}
