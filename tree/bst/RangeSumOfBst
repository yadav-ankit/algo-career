🧩 Problem Statement 

Given a BST and a range [low, high], return the sum of values of all nodes where
low ≤ node.val ≤ high.


Example:

        10
       /  \
      5    15
     / \     \
    3   7     18


Range [7, 15]

Visited nodes:

7 → 10 → 15
Ans = 32

class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;

        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }

        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }

        return root.val
             + rangeSumBST(root.left, low, high)
             + rangeSumBST(root.right, low, high);
    }
}


🌳 Key Insight (THIS IS THE WHOLE GAME)

Because it’s a BST:

If node.val < low → ignore left subtree

If node.val > high → ignore right subtree

Otherwise → include node + explore both sides

👉 This is pruning, not full traversal.

🌳 Visual Intuition

Example:

        10
       /  \
      5    15
     / \     \
    3   7     18


Range [7, 15]

Visited nodes:

7 → 10 → 15


🧠 Mental Execution (Important)

For node 10:

In range → include

Go left & right

For node 5:

< low → skip left subtree

For node 15:

In range → include

📌 You never visit unnecessary nodes

⏱️ Complexity
Metric	Value
Time	O(N) worst case
Time	O(log N) average (balanced + pruning)
Space	O(H) recursion stack
