import java.util.*;

https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/description/

ou are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.

You have to perform m independent queries on the tree where in the ith query you do the following:

Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.

Note:

The queries are independent, so the tree returns to its initial state after each query.
The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.

⚡ Complexity
Preprocessing: O(n)
Each query: O(1)
Total: O(n + q)

🔍 Step-by-Step Explanation
👉 Query: remove subtree rooted at 3

Remaining tree:

        1
      /
     2
    / \
   4   5
Longest path = 1 → 2 → 4
Height = 2
👉 Query: remove subtree rooted at 2

Remaining tree:

        1
          \
           3
          / \
         6   7
Longest path = 1 → 3 → 6
Height = 2
👉 Query: remove subtree rooted at 4

Remaining tree:

        1
      /   \
     2     3
      \   / \
       5 6   7
Longest path = 1 → 2 → 5 OR 1 → 3 → 6
Height = 2




💡 What is maxHeightAbove?

Think of it like this:

“What is the maximum height of the tree if I completely ignore (delete) the current node’s subtree?”

So when you're standing on a node:

You are asking: if this whole subtree disappears, what’s the tallest path left elsewhere?

That value is exactly maxHeightAbove.

🧠 Build Intuition with Example

Consider:

        1
      /   \
     2     3
    / \   / \
   4   5 6   7
🔥 At root (node 1)
If we remove subtree of 1 → nothing remains
So:
maxHeightAbove[1] = 0
🔥 Move to node 2

Now think:

👉 If we delete subtree of 2, what remains?

        1
          \
           3
          / \
         6   7
Best path = 1 → 3 → 6
Height = 2

So:

maxHeightAbove[2] = 2
⚙️ How we COMPUTE it

Here’s the key transition:

When going from parent → child, we compute:

maxHeightAbove(child) =
    max(
        maxHeightAbove(parent),                // coming from above
        depth(parent) + 1 + height(sibling)    // going through sibling
    )



class Solution {
    Map<Integer, Integer> height = new HashMap<>();
    Map<Integer, Integer> result = new HashMap<>();

    public int[] treeQueries(TreeNode root, int[] queries) {
        // Step 1: compute subtree heights
        computeHeight(root);

        // Step 2: compute answers using DFS
        dfs(root, 0, 0);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = result.get(queries[i]);
        }
        return ans;
    }

    // Compute height of each node
    private int computeHeight(TreeNode node) {
        if (node == null) return -1;

        int left = computeHeight(node.left);
        int right = computeHeight(node.right);

        int h = Math.max(left, right) + 1;
        height.put(node.val, h);
        return h;
    }

    // DFS to compute result
    private void dfs(TreeNode node, int d, int maxHeightAbove) {
        if (node == null) return;

        result.put(node.val, maxHeightAbove);

        // Left child
        if (node.left != null) {
            int rightHeight = (node.right != null) ? height.get(node.right.val) : -1;
            dfs(node.left, d + 1, Math.max(maxHeightAbove, d + 1 + rightHeight));
        }

        // Right child
        if (node.right != null) {
            int leftHeight = (node.left != null) ? height.get(node.left.val) : -1;
            dfs(node.right, d + 1, Math.max(maxHeightAbove, d + 1 + leftHeight));
        }
    }
}
