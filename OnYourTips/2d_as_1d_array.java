int row = index / n;
int col = index % n;

int low = 0, high = m * n - 1;

while (low <= high) {
    int mid = low + (high - low) / 2;
    int row = mid / n;
    int col = mid % n;

    if (matrix[row][col] == target) return true;
    if (matrix[row][col] < target) low = mid + 1;
    else high = mid - 1;
}

Reverse Mapping
If you already have (row, col) and want its 1D index:
int index = row * cols + col;


Flatten Matrix Traversal
Instead of:
for (int i = 0; i < rows; i++)
    for (int j = 0; j < cols; j++)
      

      you can write:

for (int k = 0; k < rows * cols; k++) {
    int i = k / cols;
    int j = k % cols;
}
This is handy when you want a single loop.

LeetCode 566 - Reshape the Matrix
The idea is that the elements remain in the same row-major order. We simply change how they're grouped into rows and columns.
Example
Original matrix (2 × 3)
1 2 3
4 5 6
Reshape to (3 × 2)
1 2
3 4
5 6
Notice the order is unchanged:
1 2 3 4 5 6

  class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {

        int m = mat.length;
        int n = mat[0].length;

        if (m * n != r * c)
            return mat;

        int[][] ans = new int[r][c];

        for (int k = 0; k < m * n; k++) {

            int oldRow = k / n;
            int oldCol = k % n;

            int newRow = k / c;
            int newCol = k % c;

            ans[newRow][newCol] = mat[oldRow][oldCol];
        }

        return ans;
    }
}
