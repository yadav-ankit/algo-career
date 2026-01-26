import java.util.*;

https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/

🧠 Best Simple Idea (Topo Sort on DAG)

Treat each cell as a node.
Make a directed edge:

👉 from lower → higher (strictly increasing)

That makes the graph a DAG (can’t have cycles if values strictly increase).

Key trick:

Instead of indegree, we use outdegree:

Outdegree(cell) = how many higher neighbors it can go to

Cells with outdegree = 0 are peaks (can’t go higher)

Then do BFS in “layers” from peaks backwards.
✅ Number of BFS levels = longest increasing path

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] outdeg = new int[m][n];
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        Queue<int[]> q = new LinkedList<>();

        // 1) Compute outdegree: count higher neighbors
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                for (int[] d : dirs) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nc >= 0 && nr < m && nc < n &&
                        matrix[nr][nc] > matrix[r][c]) {
                        outdeg[r][c]++;
                    }
                }
            }
        }

        // 2) Start from peaks (outdegree = 0)
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (outdeg[r][c] == 0) {
                    q.offer(new int[]{r, c});
                }
            }
        }

        // 3) BFS levels
        int levels = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            levels++;

            while (size-- > 0) {
                int[] cell = q.poll();
                int r = cell[0], c = cell[1];

                // go to smaller neighbors (reverse direction)
                for (int[] d : dirs) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nc >= 0 && nr < m && nc < n &&
                        matrix[nr][nc] < matrix[r][c]) {

                        outdeg[nr][nc]--;
                        if (outdeg[nr][nc] == 0) {
                            q.offer(new int[]{nr, nc});
                        }
                    }
                }
            }
        }

        return levels;
    }
}

Totally fair 😄 this “BFS levels = answer” feels magical the first time. Let me make it *click* with a simple mental model + tiny example.

---

# ✅ Core idea in 1 sentence

Each BFS “level” removes **one step** of the longest increasing path (from the end/back).

So counting levels = counting how many steps a path can have.

---

# 🧠 Think of the matrix as a graph (strictly increasing edges)

We create arrows like this:

✅ **low → high** (because path must increase)

So if `1 next to 2`, we have:
`1 → 2`

Now the **longest increasing path length** is just:
✅ **Longest path length in this DAG**

---

# 🔥 Why start from peaks (outdegree=0)?

A **peak** is a cell that cannot go to any bigger number.

So for a peak:
✅ outdegree = 0

Example peak = “end of increasing path”.

---

# ✅ Why BFS levels = path length?

We do BFS from peaks **backwards**:
From peak → to smaller neighbors.

So we are basically counting:

### Level 1 = all peaks (path length at least 1)

Every cell alone is a path of length 1, but the “ends” are peaks.

### Level 2 = cells that can reach a peak in 1 step

Those cells have a path of length 2.

### Level 3 = cells that can reach a peak in 2 steps

Those cells have a path of length 3.

…and so on.

✅ So the number of layers we peel = longest chain possible.

---

# ✅ Tiny Example (super clear)

Matrix (just 1 row):

```
1  2  3  4
```

Edges:

```
1 → 2 → 3 → 4
```

Outdegree:

* 1 has outdegree 1 (to 2)
* 2 has outdegree 1 (to 3)
* 3 has outdegree 1 (to 4)
* 4 has outdegree 0 ✅ peak

### BFS starts with peaks:

Queue = [4]

Now count levels:

### Level 1:

pop 4
remove edge 3→4 (means outdegree(3) becomes 0)
Queue becomes [3]

### Level 2:

pop 3
remove edge 2→3 → outdegree(2)=0
Queue [2]

### Level 3:

pop 2
remove edge 1→2 → outdegree(1)=0
Queue [1]

### Level 4:

pop 1
Queue empty

✅ Total BFS levels = 4
✅ Longest increasing path = 4
Perfect match.

---

# ✅ Another example with branching

```
   1
  / \
 2   3
      \
       4
```

Longest path is: `1 → 3 → 4` length = 3.

Peaks = nodes with outdegree 0 → only `{4}`

Level peeling:

* Level 1: {4}
* Level 2: {3}
* Level 3: {1,2}  (2 can’t go further, 1 now becomes free)

✅ Levels = 3 ✅

---

# 🧠 The “peeling onions” analogy (best one)

Imagine you delete all peaks first.
Then new peaks appear.
Then delete them.
Repeat.

How many rounds until nothing remains?
That number of rounds = longest increasing path.

Because the longest chain survives the most rounds.

