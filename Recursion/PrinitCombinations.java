> Given `n` and `k`, return **all possible combinations of k numbers chosen from 1 to n**.

---

## ✅ Example

```
n = 4, k = 2

Output:
[
 [1,2],
 [1,3],
 [1,4],
 [2,3],
 [2,4],
 [3,4]
]
```

Order inside a combination doesn’t matter, and duplicates are not allowed.

---

# 🧠 Core Idea — Backtracking

We build combinations step-by-step:

1. Start from a number
2. Choose it
3. Recurse to pick next numbers
4. When size becomes `k`, store result
5. Backtrack (remove last number)

Think of it as a decision tree.

---

# 🔁 Backtracking Template

```
choose number
recurse
un-choose number
```

---

# 🚀 Java Solution (Backtracking)

```java
import java.util.*;

class Solution {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int n, int k,
                           List<Integer> path,
                           List<List<Integer>> result) {

        // if combination size == k, store it
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        // try all possible next numbers
        for (int i = start; i <= n; i++) {
            path.add(i);                 // choose
            backtrack(i + 1, n, k, path, result); // explore
            path.remove(path.size() - 1); // un-choose
        }
    }
}
```

---

# 🧪 Dry Run (n=4, k=2)

```
start=1
pick 1 → [1]
   pick 2 → [1,2] ✔
   pick 3 → [1,3] ✔
   pick 4 → [1,4] ✔
pick 2 → [2]
   pick 3 → [2,3] ✔
   pick 4 → [2,4] ✔
pick 3 → [3]
   pick 4 → [3,4] ✔
```

---

# ⏱ Complexity

Number of combinations:

```
C(n, k) = n! / (k! * (n-k)!)
```

* **Time:** O(C(n,k) * k)
* **Space:** O(k) recursion stack

---


