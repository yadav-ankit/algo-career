A path:

can start and end at any node

must go downward via parent-child links

cannot branch (except at one node)

does not have to pass through the root

🧠 The Core Difficulty (this is the trap)

At each node:

You may take left

Or right

Or left + node + right (this is a full path)

Or just the node

But when returning to the parent:

❌ You are allowed to return only one direction

That’s the key insight.

🧠 Two Values at Every Node

At each node, think in terms of:

1️⃣ maxGain(node) → returned upward

Maximum path sum starting at this node and going UP

This can include:

node alone

node + left

node + right

❌ never both

2️⃣ Global maximum → updated at node

Best path passing through this node

This can include:

left + node + right

🪜 Post-order DFS Strategy

Why post-order?

Because you must know the best contribution from children first.

Steps:

Get left gain

Get right gain

Ignore negative gains (very important)

Update global answer

Return max single-branch gain


  ⏱ Complexity

Time: O(n) — every node once

Space: O(h) — recursion stack
  
class Solution {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int leftSum  = Math.max(0, dfs(node.left));
        int rightSum = Math.max(0, dfs(node.right));

        int pathSum = node.val + leftSum + rightSum;
        maxSum = Math.max(maxSum, pathSum);

        return node.val + Math.max(leftSum, rightSum);
    }
}
