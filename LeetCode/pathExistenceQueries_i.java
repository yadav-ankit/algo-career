
https://leetcode.com/problems/path-existence-queries-in-a-graph-i/description/


You are given an integer n representing the number of nodes in a graph, labeled from 0 to n - 1.

You are also given an integer array nums of length n sorted in non-decreasing order, and an integer maxDiff.

An undirected edge exists between nodes i and j if the absolute difference between nums[i] and nums[j] is at most maxDiff (i.e., |nums[i] - nums[j]| <= maxDiff).

You are also given a 2D integer array queries. For each queries[i] = [ui, vi], determine whether there exists a path between nodes ui and vi.

Return a boolean array answer, where answer[i] is true if there exists a path between ui and vi in the ith query and false otherwise.

  
What this solution does

Since nums is already sorted (the constraint says non-decreasing), no sort step needed.

Connected component assignment (lines 4–13):

cpp
if (nums[i] - nums[i-1] > maxDiff)
    compId++;          // gap too large → new component
component[i] = compId;

Walk left to right. If consecutive difference exceeds maxDiff, it's a new component. Otherwise same component.

This works because with sorted nums, reachability is purely contiguous — if i can't reach i+1, it can't reach anything beyond either.
  
```java
class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] component = new int[n];
        int compId = 0;
        component[0] = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                compId++;
            }
            component[i] = compId;
        }

        boolean[] result = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = component[queries[i][0]] == component[queries[i][1]];
        }

        return result;
    }
}
```

Direct translation of the C++ — no changes needed to the logic since it's already optimal. O(n + q) time, O(n) space.
