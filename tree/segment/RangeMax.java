
// https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/

1. First Greater Element in Range
💡 Problem

Given array arr, for a query (l, r, x):

Find the first index in [l, r] where value > x

🧠 Idea
Segment Tree stores max
If max in range ≤ x → ❌ no answer
Else → go down the tree and binary search inside segment tree



Here
    🔹 Your Building Problem

“Find first index j > b such that height[j] > threshold”

👉 This is NOT [l, r]
👉 It is effectively:

search in range [b+1, n-1]

    and value > max(arr[l], arr[r])


    
import java.util.*;

class Solution {

    class SegmentTree {
        int[] tree;
        int[] arr;
        int n;

        SegmentTree(int[] arr) {
            this.arr = arr;
            this.n = arr.length;
            tree = new int[4 * n];
            build(0, 0, n - 1);
        }

        private void build(int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }

            int mid = (start + end) / 2;
            build(2 * node + 1, start, mid);
            build(2 * node + 2, mid + 1, end);

            tree[node] = Math.max(tree[2 * node + 1], tree[2 * node + 2]);
        }

        // Find first index >= l where value > x
        int queryFirstGreater(int node, int start, int end, int l, int x) {
            if (end < l || tree[node] <= x) return -1;

            if (start == end) return start;

            int mid = (start + end) / 2;

            int left = queryFirstGreater(2 * node + 1, start, mid, l, x);
            if (left != -1) return left;

            return queryFirstGreater(2 * node + 2, mid + 1, end, l, x);
        }
    }

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        int q = queries.length;

        SegmentTree st = new SegmentTree(heights);

        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int a = queries[i][0];
            int b = queries[i][1];

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            if (a == b) {
                ans[i] = a;
            } else if (heights[a] < heights[b]) {
                ans[i] = b;
            } else {
                int threshold = Math.max(heights[a], heights[b]);

                int res = st.queryFirstGreater(0, 0, n - 1, b + 1, threshold);
                ans[i] = res;
            }
        }

        return ans;
    }
}
