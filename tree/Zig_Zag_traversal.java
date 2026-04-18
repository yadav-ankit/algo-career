public void printZigzag(TreeNode root) {
    if (root == null) return;

    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    boolean leftToRight = true;

    while (!q.isEmpty()) {
        int size = q.size();
        LinkedList<Integer> level = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            TreeNode node = q.poll();

            if (leftToRight) {
                level.addLast(node.val);
            } else {
                level.addFirst(node.val);
            }

            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }

        // 🔥 print current level
        for (int val : level) {
            System.out.print(val + " ");
        }
        System.out.println(); // move to next line

        leftToRight = !leftToRight;
    }
}
