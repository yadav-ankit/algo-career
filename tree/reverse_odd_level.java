https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/description/

Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.

For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become [18,29,11,7,4,3,1,2].
Return the root of the reversed tree.

A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.

The level of a node is the number of edges along the path between it and the root node.


class Solution {
    public TreeNode reverseOddLevels(TreeNode root) {
        if (root == null) return null;
        dfs(root.left, root.right, 1);
        return root;
    }

    private void dfs(TreeNode left, TreeNode right, int level) {
        if (left == null || right == null) return;

        // If level is odd → swap values
        if (level % 2 == 1) {
            int temp = left.val;
            left.val = right.val;
            right.val = temp;
        }

        // Traverse mirror nodes
        dfs(left.left, right.right, level + 1);
        dfs(left.right, right.left, level + 1);
    }
}
