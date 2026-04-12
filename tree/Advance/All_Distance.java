import java.util.*;

/*
    YOUTUBE VIDEO ON THIS Qn : https://www.youtube.com/watch?v=b6DrMMHFiL0
    Company Tags                : Google
    Leetcode Link               : https://leetcode.com/problems/sum-of-distances-in-tree/
*/

class Solution {

    long result_base_node = 0;
    int[] count;
    int N;

    // DFS 1: calculate subtree sizes + base result (node 0 as root)
    int dfsBase(Map<Integer, List<Integer>> adj, int curr, int parent, int depth) {
        int totalNodes = 1;

        result_base_node += depth;

        for (int child : adj.get(curr)) {
            if (child == parent) continue;

            totalNodes += dfsBase(adj, child, curr, depth + 1);
        }

        count[curr] = totalNodes;
        return totalNodes;
    }

    // DFS 2: rerooting
    void dfs(Map<Integer, List<Integer>> adj, int parent, int prev, int[] result) {
        for (int child : adj.get(parent)) {
            if (child == prev) continue;

            result[child] = result[parent]
                    - count[child]
                    + (N - count[child]);

            dfs(adj, child, parent, result);
        }
    }

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        N = n;

        // adjacency list
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        count = new int[n];

        // Step 1: base calculation from root (0)
        dfsBase(adj, 0, -1, 0);

        int[] result = new int[n];
        result[0] = (int) result_base_node;

        // Step 2: rerooting
        dfs(adj, 0, -1, result);

        return result;
    }
}
