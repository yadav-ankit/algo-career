https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/description/

https://www.youtube.com/watch?v=AW-gpXiR5DQ&list=PLpIkg8OmuX-K23LhcamOcDlTBisiNJy5E&index=48


You are given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.

Return the number of good leaf node pairs in the tree.


  Solution

  1. Every node will store two arrays Node(5) --> left = {1,2} , right = {2,2,3} which says node(5) has 2 leaf nodes at distances 1 and 2 respecitvely at left subtrees.
  and 3 leaf nodes at distances 2,2 & 3 respectively.

  2. We are sening this list of arrays to our parent. so POST order DFS.

  3. Have global variable count which is ans.

  4. After doing DFS we are doing 2 things
   4.1 --> calculating are ans based on left and right
   4.2 --> giving our parent the updated distances.(merging left and right)


  each node has 2 sortof lists (left anf right) but we are returning only 1


  At each node we temporarily have:

left list → distances to leaves in left subtree
right list → distances to leaves in right subtree

So yeah, it feels like we should return both.

💡 But here’s the trick

We don’t care which side a leaf came from anymore once we move up.

Why?

Because at the parent, this node itself will become either:

a left child, or
a right child

So its parent will again split things into:

(parent.left) and (parent.right)
🔄 What we actually need to return

We only need:

“All leaf distances from this node downward”

Not:

“Which side they came from”

🔥 Think of it like merging streams

At current node:

left  = [1,2]
right = [1]

We:

✅ Use both to count pairs
✅ Merge them into one list (after +1)

Result:

res = [2,3,2]

Now this node tells its parent:

“Hey, I have leaves at these distances from me”

class Solution {
    int count = 0;

    public int countPairs(TreeNode root, int distance) {
        dfs(root, distance);
        return count;
    }

    private List<Integer> dfs(TreeNode node, int distance) {
        if (node == null) return new ArrayList<>();

        // Leaf node
        if (node.left == null && node.right == null) {
            List<Integer> res = new ArrayList<>();
            res.add(1);
            return res;
        }

        List<Integer> left = dfs(node.left, distance);
        List<Integer> right = dfs(node.right, distance);

        // Count valid pairs
        for (int l : left) {
            for (int r : right) {
                if (l + r <= distance) {
                    count++;
                }
            }
        }

        // Return distances +1
        List<Integer> res = new ArrayList<>();
        for (int l : left) {
            if (l + 1 < distance) res.add(l + 1);
        }
        for (int r : right) {
            if (r + 1 < distance) res.add(r + 1);
        }

        return res;
    }
}
