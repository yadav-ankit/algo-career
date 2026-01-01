class Solution {

    public int rob(TreeNode root) {
        int[] result = dfs(root);
        return Math.max(result[0], result[1]);
    }

    // returns int[2]
    // [0] = max money if node NOT robbed
    // [1] = max money if node IS robbed
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int rob = root.val + left[0] + right[0];
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{notRob, rob};
    }
}

⏱ Complexity

Time: O(n) — each node visited once

Space: O(h) — recursion stack (h = tree height)


  

  🤔 Why are we using a pair (2 values) for each node?

Because one value is not enough to represent all possibilities.


❌ Why a single value FAILS

At a tree node, the “best answer” depends on what its parent does.

If parent is robbed → current node cannot be robbed

If parent is NOT robbed → current node may or may not be robbed

So one number like:

dp[node] = max money till this node

is ambiguous — it doesn’t tell us whether the node was robbed or not.

That missing info breaks correctness.

  

✅ Why a PAIR works perfectly

For each node, we explicitly store both states:

pair[0] → max money if this node is NOT robbed
pair[1] → max money if this node IS robbed


Now there is no ambiguity.
