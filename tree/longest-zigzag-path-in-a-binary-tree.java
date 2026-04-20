https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/


You are given the root of a binary tree.

A ZigZag path for a binary tree is defined as follow:

Choose any node in the binary tree and a direction (right or left).
If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
Change the direction from right to left or from left to right.
Repeat the second and third steps until you can't move in the tree.
Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

Return the longest ZigZag path contained in that tree.

🧠 Intuition

At every node, you track:

If you came from left, next must go right
If you came from right, next must go left

So for each node, you maintain two values:

leftZig: longest path if you go left next
rightZig: longest path if you go right next


class Solution {
    int max = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root);
        return max;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) return new int[]{-1, -1};

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int leftZig = 1 + left[1];   // came from right
        int rightZig = 1 + right[0]; // came from left

        max = Math.max(max, Math.max(leftZig, rightZig));

        return new int[]{leftZig, rightZig};
    }
}
