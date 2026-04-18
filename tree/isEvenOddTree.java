A binary tree is named Even-Odd if it meets the following conditions:

The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.


public boolean isEvenOddTree(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    int level = 0;

    while (!q.isEmpty()) {
        int size = q.size();
        
        int prev = (level % 2 == 0) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < size; i++) {
            TreeNode node = q.poll();
            int val = node.val;

            // Check parity
            if (level % 2 == 0) {
                // even level → odd values
                if (val % 2 == 0) return false;
                
                // strictly increasing
                if (val <= prev) return false;
            } else {
                // odd level → even values
                if (val % 2 != 0) return false;
                
                // strictly decreasing
                if (val >= prev) return false;
            }

            prev = val;

            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }

        level++;
    }

    return true;
}
