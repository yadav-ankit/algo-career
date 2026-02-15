
Problem

Given a string s, sort it so that characters appear in descending order of frequency.

Example:

Input:  "tree"
Output: "eert"   (or "eetr")


⏱ Complexity

Time: O(n)

Space: O(n)
  
import java.util.*;

class Solution {
    public String frequencySort(String s) {

        // Step 1: Count frequency
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Step 2: Bucket array
        List<Character>[] bucket = new List[s.length() + 1];

        for (char c : map.keySet()) {
            int freq = map.get(c);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(c);
        }

        // Step 3: Build result
        StringBuilder sb = new StringBuilder();

        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                for (char c : bucket[i]) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }
        }

        return sb.toString();
    }
}
