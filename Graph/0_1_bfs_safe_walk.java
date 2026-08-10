
0_1 bfs

https://leetcode.com/problems/find-a-safe-walk-through-a-grid/description/


boolean findSafeWalk(int[][] grid, int health) {
    int m = grid.length, n = grid[0].length;

    int[][] result = new int[m][n];
    for (int[] row : result) Arrays.fill(row, Integer.MAX_VALUE);

    result[0][0] = grid[0][0];

    Deque<int[]> deq = new LinkedList<>();
    deq.addFirst(new int[]{0, 0});

    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

    while (!deq.isEmpty()) {
        int[] cur = deq.pollFirst();
        int r = cur[0], c = cur[1];

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

            if (result[r][c] + grid[nr][nc] < result[nr][nc]) {
                result[nr][nc] = result[r][c] + grid[nr][nc];

                if (grid[nr][nc] == 0) deq.addFirst(new int[]{nr, nc});
                else                   deq.addLast(new int[]{nr, nc});
            }
        }
    }

    return result[m-1][n-1] < health;
}
