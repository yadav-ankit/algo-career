https://leetcode.com/problems/cousins-in-binary-tree-ii/


💡 Key idea

Instead of finding cousins directly (messy), do this:

Process tree level by level (BFS)
For each level:
Compute total sum of that level
For each parent:
Compute sum of its children (siblings group)

Update each child:

child.val = levelSum - siblingSum
✨ Step-by-step example
Original tree:
        5
       / \
      4   9
     / \   \
    1  10   7
🔹 Level 0

Nodes: [5]

No cousins → root becomes 0
🔹 Level 1

Nodes: [4, 9]
Level sum = 13

Sibling group:

children of 5 → [4,9] → sum = 13

So:

4 → 13 - 13 = 0
9 → 13 - 13 = 0
🔹 Level 2

Nodes: [1, 10, 7]
Level sum = 18

Sibling groups:

children of 4 → [1,10] → sum = 11
children of 9 → [7] → sum = 7

Now:

1 → 18 - 11 = 7
10 → 18 - 11 = 7
7 → 18 - 7 = 11
✅ Final tree:
        0
       / \
      0   0
     / \   \
    7   7   11


🚀 Clean Java solution (BFS)
class Solution {
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        root.val = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> parents = new ArrayList<>();
            int levelSum = 0;

            // Step 1: calculate next level sum
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                parents.add(node);

                if (node.left != null) {
                    levelSum += node.left.val;
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    levelSum += node.right.val;
                    queue.offer(node.right);
                }
            }

            // Step 2: update children using sibling sum
            for (TreeNode node : parents) {
                int siblingSum = 0;

                if (node.left != null) siblingSum += node.left.val;
                if (node.right != null) siblingSum += node.right.val;

                if (node.left != null) {
                    node.left.val = levelSum - siblingSum;
                }
                if (node.right != null) {
                    node.right.val = levelSum - siblingSum;
                }
            }
        }

        return root;
    }
}
