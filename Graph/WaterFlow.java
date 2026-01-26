import java.util.*;

https://leetcode.com/problems/pacific-atlantic-water-flow/

nstead of starting from every cell and trying to reach oceans (slow), do the reverse:

Start from Pacific ocean borders and go inwards

Start from Atlantic ocean borders and go inwards

You can move from cell A → cell B in reverse if
height[B] >= height[A]
(because in real flow, water would go downhill)

Finally, answer = cells reachable from both Pacific + Atlantic.
  
class Solution {
    int m, n;
    int[][] heights;
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        if (heights == null || heights.length == 0) return res;

        this.heights = heights;
        m = heights.length;
        n = heights[0].length;

        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];

        // Pacific: top row + left col
        for (int c = 0; c < n; c++) dfs(0, c, pac);
        for (int r = 0; r < m; r++) dfs(r, 0, pac);

        // Atlantic: bottom row + right col
        for (int c = 0; c < n; c++) dfs(m - 1, c, atl);
        for (int r = 0; r < m; r++) dfs(r, n - 1, atl);

        // cells reachable by both
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (pac[r][c] && atl[r][c]) {
                    res.add(Arrays.asList(r, c));
                }
            }
        }

        return res;
    }

    private void dfs(int r, int c, boolean[][] ocean) {
        ocean[r][c] = true;

        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (nr < 0 || nc < 0 || nr >= m || nc >= n) continue;
            if (ocean[nr][nc]) continue;

            // reverse flow condition
            if (heights[nr][nc] >= heights[r][c]) {
                dfs(nr, nc, ocean);
            }
        }
    }
}

✅ Time & Space

Time: O(m*n) (each cell visited at most twice)

Space: O(m*n) for visited arrays + recursion stack
