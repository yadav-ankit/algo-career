// https://leetcode.com/problems/longest-string-chain/description/


import java.util.*;

class Solution {
    public int longestStrChain(String[] words) {

        // 1. Sort by word length
        Arrays.sort(words, Comparator.comparingInt(String::length));

        // 2. DP map
        Map<String, Integer> dp = new HashMap<>();
        int maxChain = 1;

        // 3. Process each word
        for (String word : words) {
            int best = 1; // chain with just this word

            for (int i = 0; i < word.length(); i++) {
                // Remove one character
                String prev =
                        word.substring(0, i) + word.substring(i + 1);

                if (dp.containsKey(prev)) {
                    best = Math.max(best, dp.get(prev) + 1);
                }
            }

            dp.put(word, best);
            maxChain = Math.max(maxChain, best);
        }

        return maxChain;
    }
}


🧩 Example Walkthrough (Quick)

Input:

["a","b","ba","bca","bda","bdca"]


DP table builds like:

"a"    → 1
"b"    → 1
"ba"   → 2
"bca"  → 3
"bda"  → 3
"bdca" → 4


✔️ Output: 4

⏱ Time & Space Complexity
Metric	Value
Time	O(n × L²)
Space	O(n × L)

Where:

n = number of words

L = max word length

This easily fits constraints.

🎯 Interview One-Liner (Use This)

“Sort words by length and use DP where each word checks all possible predecessors by deleting one character.”
