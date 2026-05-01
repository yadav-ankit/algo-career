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


https://leetcode.com/problems/flip-equivalent-binary-trees/description/
Core Idea (THIS is everything)

At every node, only 2 possibilities exist:

✅ No flip

left1 ↔ left2  AND  right1 ↔ right2

🔄 Flip

left1 ↔ right2  AND  right1 ↔ left2

👉 If either works → trees are equivalent

🌳 Recursive Thinking (Super Important)

At each node:

If both null → ✅ true
If one null → ❌ false
If values differ → ❌ false
Else → check both possibilities

This works because flip equivalence is a local property at each node
    
class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        // base cases
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;

        // check both cases
        boolean noFlip =
            flipEquiv(root1.left, root2.left) &&
            flipEquiv(root1.right, root2.right);

        boolean flip =
            flipEquiv(root1.left, root2.right) &&
            flipEquiv(root1.right, root2.left);

        return noFlip || flip;
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
