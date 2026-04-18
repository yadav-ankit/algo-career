Given the root of a binary tree, return the leftmost value in the last row of the tree.

https://leetcode.com/problems/find-bottom-left-tree-value/description/



public int findBottomLeftValue(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    
    int result = root.val;
    
    while (!q.isEmpty()) {
        int size = q.size();
        
        for (int i = 0; i < size; i++) {
            TreeNode node = q.poll();
            
            if (i == 0) result = node.val; // leftmost
            
            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
    }
    
    return result;
}
