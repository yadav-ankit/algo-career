https://leetcode.com/problems/linked-list-in-binary-tree/description/

Given a binary tree root and a linked list with head as the first node. 

Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.

In this context downward path means a path that starts at some node and goes downwards

  Time = O(Num of nodes in tree * Num of nodes in Linked List)
  

hasPathsum ka bhai ....also same as PathSum_3 which is
  Count number of paths where:

Path can start from ANY node
Path must go downward
Sum = target


  

class Solution {

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) return false;

        // Try starting from this node
        if (match(head, root)) return true; -----> // has pathsum me hum check krte they ki if its leaf node and sum == 0 ..yha pr ek our dfs

        // Otherwise check left and right
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean match(ListNode head, TreeNode node) {
        // If list finished → success
        if (head == null) return true;

        // If tree finished → fail
        if (node == null) return false;

        // Values must match
        if (head.val != node.val) return false;

        // Move to next list node, and go down tree
        return match(head.next, node.left) || match(head.next, node.right);
    }
}
