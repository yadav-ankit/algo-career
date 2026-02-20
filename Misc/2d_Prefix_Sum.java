https://leetcode.com/problems/range-sum-query-2d-immutable/description/

class NumMatrix {

    int[][] p;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        p = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                p[i][j] = matrix[i-1][j-1]
                        + p[i-1][j]
                        + p[i][j-1]
                        - p[i-1][j-1];
            }
        }
    }

    public int sumRegion(int r1, int c1, int r2, int c2) {
        return p[r2+1][c2+1]
             - p[r1][c2+1]
             - p[r2+1][c1]
             + p[r1][c1];
    }
}


🧱 Same Original Matrix
1  2  3
4  5  6
7  8  9
🧮 Prefix Matrix
0   0   0   0
0   1   3   6
0   5  12  21
0  12  27  45


🎯 You want this rectangle:
5  6
8  9

That corresponds to:

row1 = 1, col1 = 1
row2 = 2, col2 = 2


✅ Formula 
sum =
p[row2+1][col2+1]
- p[row1][col2+1]
- p[row2+1][col1]
+ p[row1][col1]


🔢 Plug in Values
p[3][3] = 45
p[1][3] = 6
p[3][1] = 12
p[1][1] = 1

So:

45 - 6 - 12 + 1 = 28
🧾 Manual Check
5 + 6 + 8 + 9 = 28 ✅
🧠 What Happened Visually
Take everything till 9        → 45
Remove top row (1 2 3)        → -6
Remove left column (1 4 7)    → -12
Add back overlap (1)          → +1
