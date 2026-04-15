Problem statement (precise)

You’re given a binary tree and an integer target

Delete all leaf nodes whose value == target

After deletion, new leaves may form

Keep deleting until no such leaf exists


class Solution {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) return null;

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }
        return root;
    }
}




⚠️ This cascade effect is the key difficulty.

🧠 Core Insight

A node can only be deleted after its children are processed.

That immediately tells you:

✅ Post-order traversal
left → right → node



🪜 Correct Recursive Strategy

For each node:

Recursively clean left subtree

Recursively clean right subtree

After children are updated:

If node is now a leaf

AND node.val == target
→ delete it (return null)
