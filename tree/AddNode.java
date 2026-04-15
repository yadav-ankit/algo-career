🌿 Problem: Add One Row to Tree (LeetCode 623)

Given:

root of binary tree
val → value to insert
depth → level where new row should be added

👉 Insert a full row of nodes with value = val at given depth

🧠 Key Cases
1️⃣ Special Case: depth == 1
New node becomes root
Old tree becomes its left child

2️⃣ General Case
Go to level depth - 1
For each node at that level:
Create new left & right nodes
Attach original children under them
🧩 Visual

Before:

        4
       / \
      2   6

Add val = 1, depth = 2

After:

        4
       / \
      1   1
     /     \
    2       6

class Solution {

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }

        add(root, val, depth, 1);
        return root;
    }

    private TreeNode add(TreeNode root, int val, int depth, int curr) {
        if (root == null) return null;

        if (curr == depth - 1) {
            TreeNode leftTemp = root.left;
            TreeNode rightTemp = root.right;

            root.left = new TreeNode(val);
            root.right = new TreeNode(val);

            root.left.left = leftTemp;
            root.right.right = rightTemp;

            return root;
        }

        root.left = add(root.left, val, depth, curr + 1);
        root.right = add(root.right, val, depth, curr + 1);

        return root;
    }
}
