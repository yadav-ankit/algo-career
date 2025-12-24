

import java.util.*;

class Solution {
    public static List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        for (int mask = 0; mask < (1 << n); mask++) {
            List<Integer> subset = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }

            result.add(subset);
        }

        return result;
    }
}

🧠 Core Idea (super important)

For a set of size n:

Total subsets = 2ⁿ

Each subset can be represented by an n-bit binary number

Each bit decides include (1) or exclude (0) an element

So we iterate numbers from 0 to (1<<n) - 1 and treat each number as a mask.

🪜 How Bitmask Represents a Subset

Example array:

arr = [a, b, c]
index:  0  1  2


Binary masks:

Mask (decimal)	Binary	Subset
0	000	{}
1	001	{a}
2	010	{b}
3	011	{a, b}
4	100	{c}
5	101	{a, c}
6	110	{b, c}
7	111	{a, b, c}

Beautiful and systematic.
