
---

# ✅ Alien Dictionary (Leetcode 269) — Simple Explanation

You’re given words sorted in **alien language order**.
You must find the **order of characters** (like alien alphabet).

### Example

`["wrt","wrf","er","ett","rftt"]`

From `"wrt"` before `"wrf"`
First different char: `t` vs `f`
So: **t → f** (t comes before f)

This becomes a graph problem:

* characters = nodes
* “comes before” = directed edge

Then we do **Topological Sort** to get a valid ordering.

---

# ⚠️ Important Invalid Case

If:
`["abc", "ab"]`

This is invalid because **prefix longer word comes before shorter prefix**
So answer should be `""`.

---

# ✅ Steps (Super Easy)

1. Put all unique characters into graph
2. Compare adjacent words, build edges
3. If invalid prefix case → return ""
4. Run Topological Sort using **Kahn’s BFS (indegree)**
5. If topo result length != total unique chars → cycle exists → return ""

---

# ✅ Java Code (Clean + Interview Ready)

```java
import java.util.*;

class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        // 1) Add all unique characters
        for (String w : words) {
            for (char ch : w.toCharArray()) {
                graph.putIfAbsent(ch, new HashSet<>());
                indegree.putIfAbsent(ch, 0);
            }
        }

        // 2) Build edges from adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];

            // invalid: prefix case -> ["abc", "ab"]
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return "";
            }

            int len = Math.min(w1.length(), w2.length());
            for (int j = 0; j < len; j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                if (c1 != c2) {
                    // edge: c1 -> c2
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        indegree.put(c2, indegree.get(c2) + 1);
                    }
                    break; // only first mismatch matters
                }
            }
        }

        // 3) Topological sort (BFS)
        Queue<Character> q = new LinkedList<>();
        for (char ch : indegree.keySet()) {
            if (indegree.get(ch) == 0) {
                q.offer(ch);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char cur = q.poll();
            sb.append(cur);

            for (char nei : graph.get(cur)) {
                indegree.put(nei, indegree.get(nei) - 1);
                if (indegree.get(nei) == 0) {
                    q.offer(nei);
                }
            }
        }

        // 4) Cycle check
        if (sb.length() != indegree.size()) return "";

        return sb.toString();
    }
}
```

---

# ✅ Time Complexity

* Building edges: `O(total characters)`
* Toposort: `O(V + E)`
  So overall: **O(N * L)** roughly.

---

```txt
["wrt","wrf","er","ett","rftt"]
```

---

# ✅ Step 1: Compare adjacent words + build edges

### 1) "wrt" vs "wrf"

* w = w ✅
* r = r ✅
* t ≠ f ❌ (first mismatch)
  ➡️ **t → f**

---

### 2) "wrf" vs "er"

* w ≠ e (first mismatch)
  ➡️ **w → e**

---

### 3) "er" vs "ett"

* e = e ✅
* r ≠ t ❌
  ➡️ **r → t**

---

### 4) "ett" vs "rftt"

* e ≠ r ❌
  ➡️ **e → r**

---

# ✅ Final Graph Edges

```
t → f
w → e
r → t
e → r
```

So overall chain becomes:

```
w → e → r → t → f
```

---

# ✅ Step 2: Indegree count

Indegree = “how many arrows coming into a node”

| char | indegree   |
| ---- | ---------- |
| w    | 0          |
| e    | 1 (from w) |
| r    | 1 (from e) |
| t    | 1 (from r) |
| f    | 1 (from t) |

Queue initially = all indegree 0 nodes:
✅ `["w"]`

---

# ✅ Step 3: Kahn’s BFS Topo Sort

### Pop `w`

Result: `"w"`
Remove edge `w→e`
indegree(e) becomes 0 → push `e`

Queue: `[e]`

---

### Pop `e`

Result: `"we"`
Remove edge `e→r`
indegree(r) becomes 0 → push `r`

Queue: `[r]`

---

### Pop `r`

Result: `"wer"`
Remove edge `r→t`
indegree(t) becomes 0 → push `t`

Queue: `[t]`

---

### Pop `t`

Result: `"wert"`
Remove edge `t→f`
indegree(f) becomes 0 → push `f`

Queue: `[f]`

---

### Pop `f`

Result: `"wertf"`

Queue: `[]`

---

# ✅ Final Answer

✅ **"wertf"**

---

# 🔥 Why “only first mismatch matters”?

Example:
`"wrt"` vs `"wrf"`
Once you know `t < f`, anything after doesn’t matter.
That’s the **first point where order is decided**.

