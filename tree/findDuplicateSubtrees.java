https://leetcode.com/problems/find-duplicate-subtrees/description/

Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.

  
Approach (DFS + HashMap)
Traverse the tree using postorder DFS (left → right → root).

For each node, build a string representation:

subtree = left_serial + "," + right_serial + "," + node.val
Use a HashMap<String, Integer> to count occurrences.
If count becomes 2, add the node to result (only once).


                                            
class Solution {
    Map<String, Integer> map = new HashMap<>();
    List<TreeNode> result = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        serialize(root);
        return result;
    }

    private String serialize(TreeNode node) {
        if (node == null) return "#";

        String left = serialize(node.left);
        String right = serialize(node.right);

        String curr = left + "," + right + "," + node.val;

        int count = map.getOrDefault(curr, 0);
        if (count == 1) { // second time seen → duplicate
            result.add(node);
        }

        map.put(curr, count + 1);
        return curr;
    }
}
