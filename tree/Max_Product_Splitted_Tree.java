https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/description/

Approach (2 DFS)
Step 1: Compute total sum

Traverse entire tree once.

Step 2: Try every possible split

For each subtree:

compute subtreeSum = s
candidate = s * (total - s)
track max

  
Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.

Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.

Note that you need to maximize the answer before taking the mod and not after taking it.



class Solution {

    long totalSum = 0;
    long maxProduct = 0;
    int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        // Step 1: total sum
        totalSum = getSum(root);

        // Step 2: compute max product
        getSubtreeSum(root);

        return (int)(maxProduct % MOD);
    }

    private long getSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + getSum(node.left) + getSum(node.right);
    }

    private long getSubtreeSum(TreeNode node) {
        if (node == null) return 0;

        long left = getSubtreeSum(node.left);
        long right = getSubtreeSum(node.right);

        long currSum = node.val + left + right;

        // try splitting here
        long product = currSum * (totalSum - currSum);
        maxProduct = Math.max(maxProduct, product);

        return currSum;
    }
}
