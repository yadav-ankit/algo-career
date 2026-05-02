public boolean hasPath(int[][] grid) {
    int m = grid.length, n = grid[0].length;

    if (grid[0][0] == 1) return false;

    boolean[][] visited = new boolean[m][n];
    return dfs(grid, 0, 0, visited);
}

private boolean dfs(int[][] grid, int r, int c, boolean[][] visited) {
    int m = grid.length, n = grid[0].length;

    // out of bounds or blocked or already visited
    if (r < 0 || c < 0 || r >= m || c >= n ||
        grid[r][c] == 1 || visited[r][c]) {
        return false;
    }

    // reached destination
    if (r == m - 1 && c == n - 1) return true;

    visited[r][c] = true;

    // explore 4 directions
    int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    for (int[] d : dirs) {
        if (dfs(grid, r + d[0], c + d[1], visited)) {
            return true;
        }
    }

    return false;
}
