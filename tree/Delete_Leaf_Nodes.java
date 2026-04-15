🌿 Problem: Find Leaves of Binary Tree

Given a binary tree, collect nodes in rounds of removal:

First remove all leaf nodes
Then new leaves are formed → remove them
Keep going until tree is empty
Example
        1
       / \
      2   3
     / \
    4   5

Output:

[[4,5,3], [2], [1]]
💡 Key Insight (VERY IMPORTANT)

Instead of actually deleting nodes, think like this:

👉 Height of node = round in which it will be removed

Leaf nodes → height = 0
Parent of leaves → height = 1
Root → highest height

So if we group nodes by height → we get the answer.

  
class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> findLeaves(TreeNode root) {
        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null) return -1;

        int left = dfs(node.left);
        int right = dfs(node.right);

        int height = 1 + Math.max(left, right);

        // ensure list exists
        if (res.size() == height) {
            res.add(new ArrayList<>());
        }

        res.get(height).add(node.val);

        return height;
    }
}
