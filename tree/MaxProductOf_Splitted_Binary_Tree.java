🧠 Problem

You are given a binary tree.
You can remove exactly one edge, splitting the tree into two subtrees.

  (THIS CAN BE ASKED IN ARRAY AS WELL)
  GIVEN AN ARRAY FIND MAX PRODUCT OF SUM OF 2 SUBARRAYS

👉 Let:

sum1 = sum of nodes in subtree 1

sum2 = sum of nodes in subtree 2

Return the maximum product sum1 * sum2.

(Answer usually modulo 1e9 + 7.)
  
class Solution {

    long totalSum = 0;
    long maxProduct = 0;
    static final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        // 1️⃣ Get total sum of tree
        totalSum = treeSum(root);

        // 2️⃣ Try every possible split
        subtreeSum(root);

        return (int)(maxProduct % MOD);
    }

    // Pass 1: compute total sum
    private long treeSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + treeSum(node.left) + treeSum(node.right);
    }

    // Pass 2: compute subtree sums and update product
    private long subtreeSum(TreeNode node) {
        if (node == null) return 0;

        long left = subtreeSum(node.left);
        long right = subtreeSum(node.right);

        long currSum = left + right + node.val;

        // product if we cut above this subtree
        long product = currSum * (totalSum - currSum);
        maxProduct = Math.max(maxProduct, product);

        return currSum;
    }
}


🔑 Key Insight
If:

totalSum = sum of all nodes

subtreeSum = sum of any subtree

Then cutting above that subtree gives product:

subtreeSum * (totalSum - subtreeSum)


So the problem reduces to:

👉 For every subtree, compute its sum and maximize
subtreeSum * (totalSum - subtreeSum)

🧠 Why POSTORDER traversal?

Because:

Subtree sum = left + right + node

Parent depends on children

This is bottom-up aggregation

👉 Postorder is mandatory

✅ Strategy (2 clean passes)
Pass 1️⃣: Compute total sum of tree
Pass 2️⃣: Compute every subtree sum and update max product
