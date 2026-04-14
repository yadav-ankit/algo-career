
💡 Key Insight (THIS unlocks it)

👉 When you delete a node:

Its children can become new roots

👉 So you must:

Decide deletion after processing children


    ⚡ Why Post-order here?

Let’s say:

    1
   /
  2   (delete 2)
 /
4

If you delete 2:

You must already know:
4 is processed and ready
Then you add 4 to forest

👉 That’s why:

children first → then decision
    
class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> deleteSet = new HashSet<>();
        for (int val : to_delete) deleteSet.add(val);

        List<TreeNode> forest = new ArrayList<>();
        
        root = dfs(root, deleteSet, forest);
        
        // if root survives, add it
        if (root != null) forest.add(root);
        
        return forest;
    }

    private TreeNode dfs(TreeNode node, Set<Integer> deleteSet, List<TreeNode> forest) {
        if (node == null) return null;

        // 🔥 POST-ORDER → process children first
        node.left = dfs(node.left, deleteSet, forest);
        node.right = dfs(node.right, deleteSet, forest);

        // ❌ delete current node
        if (deleteSet.contains(node.val)) {
            if (node.left != null) forest.add(node.left);
            if (node.right != null) forest.add(node.right);
            return null;
        }

        return node;
    }
}
