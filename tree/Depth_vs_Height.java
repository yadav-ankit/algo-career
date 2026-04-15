

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
dfs(node, depth + 1);
```

### Height → requires postorder

```java
height = 1 + max(left, right);
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

