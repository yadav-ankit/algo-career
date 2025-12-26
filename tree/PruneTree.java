## 🧠 Problem (plain English)

Given a **binary tree** where each node value is `0` or `1`:

👉 **Remove every subtree that does NOT contain a `1`**
👉 Return the **pruned tree**

## 🔑 Core Insight (the whole problem)

> A node should be **deleted** if:
>
> * its value is `0`
> * **AND** its left subtree is gone
> * **AND** its right subtree is gone

So the decision is made **bottom-up** (postorder).

---

## ✅ Strategy (always works)

1. Recurse into left subtree
2. Recurse into right subtree
3. After pruning children:

   * if `node.val == 0` **and** both children are `null`
     → return `null`
4. Otherwise → keep the node

---

## ✅ Full Java Code (Clean & Readable)


class Solution {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;

        // Prune left and right subtrees first
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        // If this node is 0 and has no children, prune it
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }

        return root;
    }
}


---

## 🌳 TreeNode definition (for completeness)

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}
```

---

## 🧪 Example Walkthrough

```
      1
     / \
    0   1
   / \
  0   0
```

* Left subtree → all zeros → pruned
* Right subtree → contains `1` → kept

Final tree:

```
  1
   \
    1
```

---

## ⏱️ Complexity

* **Time:** `O(n)` (visit every node once)
* **Space:** `O(h)` recursion stack

---

## 🎯 Interview One-liner (gold)

> “I prune the tree using postorder traversal; a node is removed if it’s zero and both its subtrees are empty.”


