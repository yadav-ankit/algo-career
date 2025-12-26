# 🧠 The REAL question you should ask

> **“What information does the parent need from its children?”**

The answer to that question **directly decides**
👉 whether your method returns `void`, `int`, or `boolean`.

---

# 1️⃣ When should the method return **void**?

### ✅ Use `void` when:

* You are updating a **global variable**
* Parent does **not** need any value from child
* You’re just *visiting* nodes

### 🧠 Mental model

> “I’m not asking children anything — I’m just doing work.”

---

### Examples

* Level order traversal
* Printing nodes
* Collecting values into a list
* Updating a global `ans`

```java
int ans = 0;

void dfs(TreeNode node) {
    if (node == null) return;
    ans = Math.max(ans, node.val);
    dfs(node.left);
    dfs(node.right);
}
```

✔ No value flows upward
✔ Result stored externally

---

# 2️⃣ When should the method return **boolean**?

### ✅ Use `boolean` when:

* Parent only needs a **YES / NO**
* Validity depends on children
* Early failure matters

### 🧠 Mental model

> “Tell me if your subtree is valid or not.”

---

### Examples

* Is tree balanced?
* Is subtree valid?
* Binary Tree Pruning (contains 1 or not)

```java
boolean containsOne(TreeNode node) {
    if (node == null) return false;

    boolean left = containsOne(node.left);
    boolean right = containsOne(node.right);

    if (!left) node.left = null;
    if (!right) node.right = null;

    return node.val == 1 || left || right;
}
```

✔ Parent only cares if subtree is useful
✔ Perfect use of boolean

---

# 3️⃣ When should the method return **int**?

### ✅ Use `int` when:

* Parent needs a **computed value**
* You are aggregating data bottom-up
* You’re doing tree DP

### 🧠 Mental model

> “Give me a number from your subtree.”

---

### Examples

#### Height of tree

```java
int height(TreeNode node) {
    if (node == null) return 0;
    return 1 + Math.max(height(node.left), height(node.right));
}
```

#### Diameter / Max Path / Subtree Sum

```java
int sum(TreeNode node) {
    if (node == null) return 0;
    return node.val + sum(node.left) + sum(node.right);
}
```

✔ Parent combines children values
✔ Clean bottom-up logic

---

# 4️⃣ Return **TreeNode** (special but important)

### ✅ Use when:

* You might **delete / replace nodes**
* Tree structure changes

### 🧠 Mental model

> “I may give you back a different node than the one you gave me.”

---

### Example: Binary Tree Pruning

```java
TreeNode prune(TreeNode node) {
    if (node == null) return null;

    node.left = prune(node.left);
    node.right = prune(node.right);

    if (node.val == 0 && node.left == null && node.right == null) {
        return null;
    }
    return node;
}
```

✔ Parent must reassign child
✔ `void` would fail here

---

# 🔥 Ultimate Decision Table (Memorize this)

| Problem need                  | Return type |
| ----------------------------- | ----------- |
| Just traverse / update global | `void`      |
| Valid / invalid               | `boolean`   |
| Height / sum / max / count    | `int`       |
| Modify tree structure         | `TreeNode`  |

---

# 🧪 One killer interview trick

If you’re stuck, ask yourself:

> **“If I remove the return value, does the solution still work?”**

* ✅ Yes → `void`
* ❌ No → return something meaningful

