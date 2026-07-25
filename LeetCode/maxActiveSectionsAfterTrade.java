https://leetcode.com/problems/maximize-active-section-with-trade-i/description/


### Key Concepts and Strategy

*   **The Trade Operation (0:53 - 1:21):** A single trade consists of two steps:
    1.  Convert a continuous block of '1's surrounded by '0's into '0's.
    2.  Convert a continuous block of '0's surrounded by '1's into '1's.
*   **Padding Requirement (1:34 - 2:05):** The problem requires treating the string as having '1's padded at both ends (`1 + S + 1`), which helps define boundaries for blocks.
*   **Core Strategy (4:21 - 12:17):** The creator explains that since one trade is allowed, the objective is to find a contiguous block of '1's to turn into '0's such that it merges two adjacent '0'-blocks, resulting in a new, larger block of '1's that maximizes the total count.

### Implementation Steps (14:02 - 17:11)

1.  **Count Existing '1's:** Determine the initial count of active sections in the string (15:00 - 15:20).
2.  **Find Zero Blocks:** Store the sizes of all contiguous blocks of '0's in a vector/list (15:21 - 16:17).
3.  **Maximize Pair Sum:** Iterate through the zero-blocks to find the maximum possible sum of two adjacent blocks. This sum represents the potential gain of '1's from performing the trade (16:20 - 17:03).
4.  **Final Result:** Add the maximum pair sum to the original count of '1's to get the final answer (17:03 - 17:10).

### Complexity

*   **Time Complexity:** $O(N)$, where $N$ is the length of the string, as the solution involves linear passes to count '1's 
and identify zero-block sizes (17:11 - 17:22).


class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        // Pad with '1' on both ends
        String t = "1" + s + "1";
        int n = t.length();

        // Step 1: Count existing '1's in original string
        int totalOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') totalOnes++;
        }

        // Step 2: Collect sizes of all '0' blocks in padded string
        List<Integer> zeroBlocks = new ArrayList<>();
        int i = 0;
        while (i < n) {
            if (t.charAt(i) == '0') {
                int size = 0;
                while (i < n && t.charAt(i) == '0') {
                    size++;
                    i++;
                }
                zeroBlocks.add(size);
            } else {
                i++;
            }
        }

        // Step 3: Find max sum of two adjacent zero blocks
        int maxGain = 0;
        for (int j = 0; j + 1 < zeroBlocks.size(); j++) {
            maxGain = Math.max(maxGain, zeroBlocks.get(j) + zeroBlocks.get(j + 1));
        }

        // Step 4: Answer = existing 1s + best gain from trade
        return totalOnes + maxGain;
    }
}
