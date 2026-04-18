https://leetcode.com/problems/smallest-string-starting-from-leaf/description/

You are given the root of a binary tree where each node has a value in the range [0, 25] representing the letters 'a' to 'z'.

Return the lexicographically smallest string that starts at a leaf of this tree and ends at the root.

As a reminder, any shorter prefix of a string is lexicographically smaller.

For example, "ab" is lexicographically smaller than "aba".



  Solution
   same question --> print all node to leaf path using BACKTRACKING

  To check lexigrpahical order use compareTo( )

String result = null;

public String smallestFromLeaf(TreeNode root) {
    dfs(root, new StringBuilder());
    return result;
}

private void dfs(TreeNode node, StringBuilder sb) {
    if (node == null) return;

    sb.insert(0, (char)('a' + node.val)); // add at front

    if (node.left == null && node.right == null) {
        String curr = sb.toString();
        if (result == null || curr.compareTo(result) < 0) {
            result = curr;
        }
    }

    dfs(node.left, sb);
    dfs(node.right, sb);

    sb.deleteCharAt(0); // backtrack
}
