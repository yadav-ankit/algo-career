https://leetcode.com/problems/create-binary-tree-from-descriptions/description/

💡 Core Insight (this is the whole problem)

Two things:

Build all nodes + connections
Find the root = node that is NEVER a child

That’s it.

Why?
Every node appears as a child except root


  Use a Map<Integer, TreeNode>
→ so you don’t recreate nodes

Step 2: Track children

Use a Set<Integer> children

Every time you see a child → add it

  
class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] d : descriptions) {
            int parent = d[0];
            int child = d[1];
            int isLeft = d[2];

            map.putIfAbsent(parent, new TreeNode(parent));
            map.putIfAbsent(child, new TreeNode(child));

            if (isLeft == 1) {
                map.get(parent).left = map.get(child);
            } else {
                map.get(parent).right = map.get(child);
            }

            children.add(child);
        }

        // find root
        for (int node : map.keySet()) {
            if (!children.contains(node)) {
                return map.get(node);
            }
        }

        return null;
    }
}
