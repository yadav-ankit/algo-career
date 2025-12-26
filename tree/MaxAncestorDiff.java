https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/

class Solution {

    int ans = 0;

    public int maxAncestorDiff(TreeNode root) {
        if (root == null) return 0;

        // Start DFS with root as both min and max
        dfs(root, root.val, root.val);
        return ans;
    }

    private void dfs(TreeNode node, int minVal, int maxVal) {
        if (node == null) return;

        // 1️⃣ Difference with smallest ancestor so far
        int diffWithMin = Math.abs(node.val - minVal);

        // 2️⃣ Difference with largest ancestor so far
        int diffWithMax = Math.abs(node.val - maxVal);

        // 3️⃣ Best difference at this node
        int bestAtThisNode = Math.max(diffWithMin, diffWithMax);

        // 4️⃣ Update global answer
        ans = Math.max(ans, bestAtThisNode);

        // 5️⃣ Update min & max for children
        int newMin = Math.min(minVal, node.val);
        int newMax = Math.max(maxVal, node.val);

        // 6️⃣ Go deeper
        dfs(node.left, newMin, newMax);
        dfs(node.right, newMin, newMax);
    }
}


Think like this:

While going down the tree

Keep track of:

minimum value seen so far

maximum value seen so far

At each node, the worst difference it can create is with either:

the smallest ancestor

or the largest ancestor
  
