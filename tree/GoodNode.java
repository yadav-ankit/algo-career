
https://leetcode.com/problems/count-good-nodes-in-binary-tree/


class Solution {
    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    private int dfs(TreeNode node, int maxSoFar) {
        if (node == null) return 0;

        int count = 0;
        if (node.val >= maxSoFar) {
            count = 1;
            maxSoFar = node.val;
        }

        count += dfs(node.left, maxSoFar);
        count += dfs(node.right, maxSoFar);

        return count;
    }
}


A node is GOOD if:

On the path from the root to that node,
no node has a value greater than it.

In other words:

node.val >= max value seen so far on the path

🧠 Key Insight (this solves everything)

Each node only cares about its ancestors, not siblings, not children.

So at every step, we carry:

maxSoFar

🪜 DFS Strategy (Top-down)

Start at root

Track the maximum value so far

If node.val >= maxSoFar → ✅ good node

Update maxSoFar

Recurse left & right
