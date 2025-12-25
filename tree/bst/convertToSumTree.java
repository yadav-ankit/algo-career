🧩 Problem Statement
Replace every node’s value with the sum of all node values greater than or equal to it in the BST.
  
class Solution {
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        reverseInorder(root);
        return root;
    }

    private void reverseInorder(TreeNode node) {
        if (node == null) return;

        reverseInorder(node.right);

        sum += node.val;
        node.val = sum;

        reverseInorder(node.left);
    }
}

⏱️ Complexity
Metric	Value

Time	O(N)
  
Space	O(H) recursion stack

  
BST property
Inorder traversal → sorted (ascending)
Reverse inorder → sorted (descending)


So if we traverse:

RIGHT → NODE → LEFT


We will:

Visit larger values first

Maintain a running sum

Update each node in place


  Example BST:

        5
       / \
      2   13


Reverse inorder visit order:

13 → 5 → 2


Running sum:

13 → 18 → 20


Final tree:

        18
       /  \
     20    13
