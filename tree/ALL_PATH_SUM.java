## 🌳 Problems we’ll solve

We’ll cover:

**2️⃣ All Root → Leaf Paths with Given Sum
**3️⃣ Path Sum III (ANY → ANY, downward)
**4️⃣ Maximum Path Sum (ANY → ANY)
**6️⃣ Leaf → Leaf Maximum Path Sum
** Maximum Path Sum (root → ANY) (LAST)

---

# 2️⃣ All Root → Leaf Paths with Given Sum

### Problem

Return **all root-to-leaf paths** whose sum equals `target`.

### Key rules

* Must start at root
* Must end at a leaf
* No branching
* Need to keep the **path itself**

---

### 🧠 Technique

👉 DFS + **backtracking**

------------------------------------------------------------------------------------

### ✅ Java Solution

```java
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), res);
        return res;
    }

    private void dfs(TreeNode node, int sum, List<Integer> path, List<List<Integer>> res) {
        if (node == null) return;

        path.add(node.val);

        if (node.left == null && node.right == null && node.val == sum) {
            res.add(new ArrayList<>(path));
        } else {
            dfs(node.left, sum - node.val, path, res);
            dfs(node.right, sum - node.val, path, res);
        }

        path.remove(path.size() - 1); // backtrack
    }
}
```
------------------------------------------------------------------------------------
🌿 Problem

Check if there exists at least one root-to-leaf path such that:

sum of nodes = target

👉 Return true as soon as you find one.

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        // leaf node
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        int remaining = targetSum - root.val;

        return hasPathSum(root.left, remaining) ||
               hasPathSum(root.right, remaining);
    }
}
------------------------------------------------------------------------------------

Now print this path...... again backtracking with dfs

    class Solution {
    public boolean hasPathSumPrint(TreeNode root, int targetSum) {
        List<Integer> path = new ArrayList<>();
        return dfs(root, targetSum, path);
    }

    private boolean dfs(TreeNode node, int target, List<Integer> path) {
        if (node == null) return false;

        // choose
        path.add(node.val);

        // leaf check
        if (node.left == null && node.right == null) {
            if (target == node.val) {
                System.out.println(path); // 🔥 print path
                return true;
            }
        }

        int remaining = target - node.val;

        if (dfs(node.left, remaining, path) ||
            dfs(node.right, remaining, path)) {
            return true; // stop once one path found
        }

        // backtrack
        path.remove(path.size() - 1);

        return false;
    }
}

------------------------------------------------------------------------------------
    
# 3️⃣ Path Sum III (ANY → ANY, downward)

### Problem

Count number of **downward paths** whose sum equals `target`.

Paths:

* can start anywhere
* can end anywhere
* must go downward

---

### 🧠 Core Insight (this is huge)

This is **Subarray Sum = K** applied to a tree.

👉 Use **prefix sum + hashmap**

---

### ✅ Java Solution (Optimal)

```java
class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        return dfs(root, 0, targetSum, prefix);
    }

    private int dfs(TreeNode node, long currSum, int target, Map<Long, Integer> prefix) {
        if (node == null) return 0;

        currSum += node.val;

        int count = prefix.getOrDefault(currSum - target, 0);

        prefix.put(currSum, prefix.getOrDefault(currSum, 0) + 1);

        count += dfs(node.left, currSum, target, prefix);
        count += dfs(node.right, currSum, target, prefix);

        prefix.put(currSum, prefix.get(currSum) - 1); // backtrack

        return count;
    }
}
```

⏱ `O(n)`
🔥 Interview favorite
🔥 Same idea as array prefix sums

------------------------------------------------------------------------------------

# 4️⃣ Maximum Path Sum (ANY → ANY)

### Problem

Find maximum sum of **any path**.

* start/end anywhere
* may split at **one node**

---

### 🧠 Core Rule

* Global answer can use **left + node + right**
* Returned value can use **only one branch**

---

### ✅ Java Solution (Canonical)

```java
class Solution {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int left = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));

        maxSum = Math.max(maxSum, node.val + left + right);

        return node.val + Math.max(left, right);
    }
}
```

⏱ `O(n)`

------------------------------------------------------------------------------------

# 6️⃣ Leaf → Leaf Maximum Path Sum

### Problem

Path must:

* start at one leaf
* end at another leaf

⚠️ Very important constraint.

---

### 🧠 Key Difference

You can only update the answer if **both children exist**.

---

### ✅ Java Solution

```java
class Solution {
    int maxSum = Integer.MIN_VALUE;

    public int maxLeafToLeaf(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) return Integer.MIN_VALUE;

        if (node.left == null && node.right == null)
            return node.val;

        int left = dfs(node.left);
        int right = dfs(node.right);

        if (node.left != null && node.right != null) {
            maxSum = Math.max(maxSum, left + right + node.val);
            return node.val + Math.max(left, right);
        }

        // If only one child exists
        return node.val + (node.left != null ? left : right);
    }
}
```

⏱ `O(n)`

------------------------------------------------------------------------------------

## 🧠 One Table to Rule Them All

| Problem | Path Start | Path End | Split Allowed | Technique          |
| ------- | ---------- | -------- | ------------- | ------------------ |
| 2️⃣     | Root       | Leaf     | ❌             | DFS + backtracking |
| 3️⃣     | Any        | Any      | ❌             | Prefix sum         |
| 4️⃣     | Any        | Any      | ✅             | Post-order DP      |
| 6️⃣     | Leaf       | Leaf     | ✅             | Post-order DP      |



------------------------------------------------------------------------------------

## 🌳 Maximum Path Sum (**root → ANY**)


### What the path means here

* Path **must start at the root**
* Path can end at **any node**
* Path goes **downward only**
* ❌ No splitting (no left + right)

This single rule change makes the problem *much simpler* than max path sum (ANY → ANY).

------------------------------------------------------------------------------------

## 🧠 Key Insight (this is the whole problem)

Since the path **must start at root**:

* You never compare siblings
* You never need a global variable
* You just keep taking the **best child**

👉 This is just a **max downward path** problem.

------------------------------------------------------------------------------------

## 🪜 Simple DFS Logic

At every node:

1. Compute max path sum from left child
2. Compute max path sum from right child
3. Take the **larger one**
4. Add current node’s value

That’s it.

------------------------------------------------------------------------------------

## ✅ Java Solution (Clean & Optimal)

```java
class Solution {
    public int maxPathSumRootToAny(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return dfs(root);
    }

    private int dfs(TreeNode node) {
        if (node == null) return Integer.MIN_VALUE;

        int left = dfs(node.left);
        int right = dfs(node.right);

        // Best downward path starting at this node
        return node.val + Math.max(0, Math.max(left, right));
    }
}
```

------------------------------------------------------------------------------------

## 🧩 Why `Math.max(0, …)` is still needed

Even though path must start at root:

* You are allowed to **stop early**
* You are NOT forced to include a negative child

Example:

```
    5
   /
 -10
```

Correct answer: `5`, not `-5`

So we drop negative branches.

---

## 🧠 What if all values are negative?

Example:

```
   -3
   /
 -5
```

Result: `-3` (root itself)

Why it works:

* `Math.max(0, child)` ensures we don’t extend into worse paths
* Root is always included (path must start there)



