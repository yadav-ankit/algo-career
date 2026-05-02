
// https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/description/

public class Solution {

    // directions: up, down, left, right
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

    // allowed directions for each street type
    // index = street type
    int[][][] allowed = {
        {}, // 0 unused
        {{0,-1},{0,1}},       // 1: left, right
        {{-1,0},{1,0}},       // 2: up, down
        {{0,-1},{1,0}},       // 3: left, down
        {{0,1},{1,0}},        // 4: right, down
        {{0,-1},{-1,0}},      // 5: left, up
        {{0,1},{-1,0}}        // 6: right, up
    };

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        return dfs(grid, 0, 0, visited);
    }

    private boolean dfs(int[][] grid, int r, int c, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;

        if (r == m - 1 && c == n - 1) return true;

        visited[r][c] = true;

        int type = grid[r][c];

        for (int[] d : allowed[type]) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (nr < 0 || nc < 0 || nr >= m || nc >= n || visited[nr][nc])
                continue;

            // check reverse connection
            int nextType = grid[nr][nc];
            for (int[] back : allowed[nextType]) {
                if (nr + back[0] == r && nc + back[1] == c) {
                    if (dfs(grid, nr, nc, visited)) return true;
                }
            }
        }

        return false;
    }
}


Why do we check reverse connection?

Because roads must connect from BOTH sides, not just one.

Think of each cell as a pipe piece.

If you move from A → B:

A must have an opening towards B
B must have an opening back towards A

Otherwise, the path is broken.
