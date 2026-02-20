
https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0286.Walls%20and%20Gates/README_EN.md

You are given an m x n grid rooms initialized with these three possible values.

-1 A wall or an obstacle.
0 A gate.
INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.



import java.util.*;


✅ Strategy

Push all gates (0) into the queue.

Run BFS outward.

For each neighboring cell:

If it's INF, update it with currentDistance + 1

Add it to queue.

Ignore walls -1.

class Solution {
    private static final int INF = 2147483647;
    private static final int[][] DIRS = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;

        int m = rooms.length;
        int n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();

        // 1️⃣ Add all gates to queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // 2️⃣ Multi-source BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];

            for (int[] d : DIRS) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr < 0 || nc < 0 || nr >= m || nc >= n)
                    continue;

                if (rooms[nr][nc] != INF)
                    continue;

                rooms[nr][nc] = rooms[r][c] + 1;
                queue.offer(new int[]{nr, nc});
            }
        }
    }
}
