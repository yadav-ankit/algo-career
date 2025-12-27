https://leetcode.com/problems/increment-submatrices-by-one/
https://www.youtube.com/watch?v=GluIvp5NaGw&list=PLpIkg8OmuX-Kqkb8DqDe_4-Tiav6ilS_L&index=7

class Solution {

    public int[][] rangeAddQueries(int n, int[][] queries) {

        // Result matrix initialized with 0
        int[][] mat = new int[n][n];

        // ==============================
        // STEP 1: Process each query
        // Same idea as 1D difference array, applied row-wise
        // ==============================
        for (int[] query : queries) {
            int row1 = query[0];
            int col1 = query[1];
            int row2 = query[2];
            int col2 = query[3];

            // Apply difference updates for each affected row
            for (int i = row1; i <= row2; i++) {
                mat[i][col1] += 1;          // range start

                if (col2 + 1 < n) {
                    mat[i][col2 + 1] -= 1;  // range end
                }
            }
        }

        // ==============================
        // STEP 2: Prefix sum on each row
        // ==============================
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                mat[i][j] += mat[i][j - 1];
            }
        }

        return mat;
    }
}


## 🧠 How to understand this (very simply)

### What each query means

```
[row1, col1, row2, col2]
→ add +1 to every cell inside this rectangle
```

### Trick used

* Instead of updating every cell (slow),
* We:

  * mark **start** of range with `+1`
  * mark **end** with `-1`
  * later do **prefix sum** to apply it

### Why row-wise loop?

Because:

* We’re using **1D difference per row**
* Much simpler than full 2D diff array
* Still efficient for this problem

---

## ⏱️ Complexity

* **Time:** `O(q * n + n²)`
* **Space:** `O(n²)`

(Exactly what the problem expects.)

---

## Interview one-liner (strong 💪)

> “I apply a 1D difference array on each row of the matrix and then take row-wise prefix sums to materialize the rectangle updates.”


