Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.

A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.

class Solution {

    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }

    private int dfs(TreeNode node, int min, int max) {
        if (node == null) return max - min;

        // update min & max
        min = Math.min(min, node.val);
        max = Math.max(max, node.val);

        int left = dfs(node.left, min, max);
        int right = dfs(node.right, min, max);

        return Math.max(left, right);
    }
}
