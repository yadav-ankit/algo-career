

---

## 🌳 Depth vs Height

### 🔹 **Depth of a node**

👉 Distance **from root → that node**

* Root depth = **0**
* Count edges downward

---

### 🔹 **Height of a node**

👉 Distance **from that node → deepest leaf**

* Leaf height = **0**
* Count edges downward

---

## 🧩 Visual Example

```
        A
       / \
      B   C
     /
    D
```

---

### 📍 Depth

| Node | Depth |
| ---- | ----- |
| A    | 0     |
| B, C | 1     |
| D    | 2     |

---

### 📍 Height

| Node | Height |
| ---- | ------ |
| D    | 0      |
| B    | 1      |
| C    | 0      |
| A    | 2      |

---

## 🧠 One-line memory trick

👉 **Depth = how far from root**
👉 **Height = how far to leaf**

---

## 🔥 Direction matters

| Concept | Direction   |
| ------- | ----------- |
| Depth   | Top → Down  |
| Height  | Bottom → Up |

---

## ⚡ Coding perspective

### Depth → easy in traversal

```java

        Max depth == same as height
        
public int maxDepth(TreeNode root) {
    if (root == null) return 0;

    int left = maxDepth(root.left);
    int right = maxDepth(root.right);

    return 1 + Math.max(left, right);
}


        Min

                in mindepth why can't we just u
                se min instead of max
                Because min() will happily pick a path that doesn’t actually reach a leaf 


                In max depth, both sides are always valid:

1 + Math.max(left, right) // ✅ always safe

But in min depth, one side might be null → which gives depth 0.

If you do:

1 + Math.min(left, right) // ❌ can pick invalid path

you might accidentally choose a path that doesn’t lead to a leaf at all.
        
        
public int minDepth(TreeNode root) {
    if (root == null) return 0;

    // If left subtree is null, ignore it
    if (root.left == null) {
        return 1 + minDepth(root.right);
    }

    // If right subtree is null, ignore it
    if (root.right == null) {
        return 1 + minDepth(root.left);
    }

    // Both children exist
    return 1 + Math.min(minDepth(root.left), minDepth(root.right));
}
        
```

### Height → requires postorder

```java
public int maxHeight(TreeNode root) {
    if (root == null) return 0;

    int left = maxHeight(root.left);
    int right = maxHeight(root.right);

    return 1 + Math.max(left, right);
}
```

👉 That’s why:

* **Find Leaves problem → height based**
* **Level order / Add row → depth based**



## 🧠 Interview cheat sheet

| Problem Type               | Use    |
| -------------------------- | ------ |
| Level / distance from root | Depth  |
| Remove leaves / bottom-up  | Height |
| Tree diameter              | Height |
| BFS levels                 | Depth  |

---

## 💡 Final intuition

If you’re:

* **Going down → depth**
* **Coming up → height**

---

