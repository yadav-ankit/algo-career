⏱ Complexity
Time: O(n)
Space: O(h) (recursion stack, worst case O(n))
🔥 Intuition Example

Consider:

    1
   / \
  2   3
   \
    5

Indexes:

1 → 0
2 → 1
3 → 2
5 → 4

Total nodes = 4
But index 4 ≥ 4 ❌ → Not complete


class Solution {
    public boolean isCompleteTree(TreeNode root) {
        int totalNodes = countNodes(root);
        return dfs(root, 0, totalNodes);
    }

    private int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private boolean dfs(TreeNode node, int index, int totalNodes) {
        if (node == null) return true;

        // If index is out of bounds → not complete
        if (index >= totalNodes) return false;

        return dfs(node.left, 2 * index + 1, totalNodes) &&
               dfs(node.right, 2 * index + 2, totalNodes);
    }
}
