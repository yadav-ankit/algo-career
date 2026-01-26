import java.util.*;

https://leetcode.com/problems/find-eventual-safe-states/description/

✅ Trick / Key Idea (Reverse Graph + Topo)

Instead of finding cycles directly, we do this:

Step 1: Reverse edges

Original: u -> v
Reverse: v -> u

Step 2: Track outdegree (in original graph)

Because “terminal nodes” (outdegree = 0) are automatically safe.

Step 3: Topo BFS starting from terminal nodes

Push all nodes with outdegree = 0

Pop node x (safe)

For every predecessor p in reverse graph:

reduce outdegree[p]--

if outdegree[p] == 0 → it becomes safe too

✅ Whatever becomes 0 outdegree after removing unsafe edges = safe

Finally sort safe nodes.


class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;

        // reverse graph
        List<List<Integer>> rev = new ArrayList<>();
        for (int i = 0; i < n; i++) rev.add(new ArrayList<>());

        // outdegree of each node in original graph
        int[] outdegree = new int[n];

        for (int u = 0; u < n; u++) {
            outdegree[u] = graph[u].length;
            for (int v : graph[u]) {
                rev.get(v).add(u); // reverse edge v -> u
            }
        }

        // queue starts with terminal nodes (outdegree = 0)
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (outdegree[i] == 0) q.offer(i);
        }

        boolean[] safe = new boolean[n];

        while (!q.isEmpty()) {
            int node = q.poll();
            safe[node] = true;

            // all nodes that point to 'node' in original graph
            for (int prev : rev.get(node)) {
                outdegree[prev]--;
                if (outdegree[prev] == 0) {
                    q.offer(prev);
                }
            }
        }

        // collect safe nodes in sorted order
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (safe[i]) ans.add(i);
        }
        return ans;
    }
}

HINT
  JINKI OUTGEGREE > 1 WILL NEVER BE SAFE NODES


Perfect 😄 Let’s do a super clear **dry run** on the classic example:

### ✅ Input

```txt
graph = [
  [1,2],   // 0 -> 1,2
  [2,3],   // 1 -> 2,3
  [5],     // 2 -> 5
  [0],     // 3 -> 0
  [5],     // 4 -> 5
  [],      // 5 -> terminal
  []       // 6 -> terminal
]
```

---

# ✅ Step 1: Outdegree (original graph)

Outdegree = number of outgoing edges

| node | edges | outdegree |
| ---- | ----- | --------- |
| 0    | 1,2   | 2         |
| 1    | 2,3   | 2         |
| 2    | 5     | 1         |
| 3    | 0     | 1         |
| 4    | 5     | 1         |
| 5    | none  | 0 ✅       |
| 6    | none  | 0 ✅       |

So queue starts with terminal nodes:
✅ `Q = [5, 6]`

---

# ✅ Step 2: Build Reverse Graph (important)

Reverse edges means:
If `u -> v` then `v -> u` in `rev`

Let’s list who points to each node:

* rev[0] = [3]  (because 3 -> 0)
* rev[1] = [0]  (0 -> 1)
* rev[2] = [0,1] (0 -> 2, 1 -> 2)
* rev[3] = [1]  (1 -> 3)
* rev[5] = [2,4] (2 -> 5, 4 -> 5)
* rev[6] = []

---

# ✅ Step 3: BFS Topo from terminals

### Pop 5 (safe)

Mark safe(5)=true ✅
Look at rev[5] = [2,4]

Reduce their outdegree:

* outdegree(2): 1 → 0 ✅ push 2
* outdegree(4): 1 → 0 ✅ push 4

Now:
✅ Q = [6, 2, 4]

---

### Pop 6 (safe)

safe(6)=true ✅
rev[6] is empty → nothing changes

✅ Q = [2, 4]

---

### Pop 2 (safe)

safe(2)=true ✅
rev[2] = [0,1]

* outdegree(0): 2 → 1 (not zero)
* outdegree(1): 2 → 1 (not zero)

✅ Q = [4]

---

### Pop 4 (safe)

safe(4)=true ✅
rev[4] is empty (nobody points to 4)

✅ Q = []

---

# ✅ Final Safe Nodes

Safe = `{2,4,5,6}`
Sorted answer:

```txt
[2,4,5,6]
```

---

# 🔥 Why 0,1,3 are NOT safe?

Because they form a cycle reachable loop:

* 0 → 1 → 3 → 0 ✅ cycle

So:

* Their outdegree never becomes 0
* They never enter queue
* So they never become safe

