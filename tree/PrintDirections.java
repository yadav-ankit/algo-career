https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/description/

Core Idea (the trick that makes it easy)

You don’t actually move from start to dest directly.

Instead:

Find path from root → start
Find path from root → dest
Remove the common prefix (this is the LCA part implicitly)
For remaining:
Convert leftover path of start into "U" moves
Append remaining path of dest (L / R)
🧠 Why this works

The common prefix represents the path until the Lowest Common Ancestor (LCA).

So:

From start → LCA = all U
From LCA → dest = remaining L/R path
✨ Example

Tree:

        5
       / \
      1   2
         / \
        3   6

start = 3, dest = 6

root → 3 = R → L
root → 6 = R → R

Common = R

Remaining:

start: L → becomes U
dest: R

👉 Answer = "UR"


class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder pathToStart = new StringBuilder();
        StringBuilder pathToDest = new StringBuilder();

        findPath(root, startValue, pathToStart);
        findPath(root, destValue, pathToDest);

        // Remove common prefix
        int i = 0;
        while (i < pathToStart.length() && i < pathToDest.length() &&
               pathToStart.charAt(i) == pathToDest.charAt(i)) {
            i++;
        }

        // Build result
        StringBuilder result = new StringBuilder();

        // Step up from start node to LCA
        for (int j = i; j < pathToStart.length(); j++) {
            result.append('U');
        }

        // Then go from LCA to destination
        result.append(pathToDest.substring(i));

        return result.toString();
    }

    private boolean findPath(TreeNode root, int target, StringBuilder path) {
        if (root == null) return false;

        if (root.val == target) return true;

        path.append('L');
        if (findPath(root.left, target, path)) return true;
        path.deleteCharAt(path.length() - 1);

        path.append('R');
        if (findPath(root.right, target, path)) return true;
        path.deleteCharAt(path.length() - 1);

        return false;
    }
}
