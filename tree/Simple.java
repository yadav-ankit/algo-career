class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode l, TreeNode r) {
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;

        return (l.val == r.val)
            && isMirror(l.left, r.right)
            && isMirror(l.right, r.left);
    }
}


https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/description/

class FindElements {

    private Set<Integer> set = new HashSet<>();

    public FindElements(TreeNode root) {
        recover(root, 0);
    }

    private void recover(TreeNode node, int val) {
        if (node == null) return;

        node.val = val;
        set.add(val);

        recover(node.left, 2 * val + 1);
        recover(node.right, 2 * val + 2);
    }

    public boolean find(int target) {
        return set.contains(target);
    }
}
