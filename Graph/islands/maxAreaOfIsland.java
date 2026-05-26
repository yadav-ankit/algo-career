

class Solution {

    int rows;
    int cols;
    int[][] grid;

    public int maxAreaOfIsland(int[][] grid) {

        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;

        int maxArea = 0;

        for(int i = 0; i < rows; i++) {

            for(int j = 0; j < cols; j++) {

                if(grid[i][j] == 1) {

                    maxArea = Math.max(maxArea, dfs(i, j));
                }
            }
        }

        return maxArea;
    }


    int dfs(int i, int j) {

        if(i < 0 || j < 0 || i >= rows || j >= cols || grid[i][j] == 0) return 0;

        grid[i][j] = 0;

        int area = 1;

        area += dfs(i+1, j);
        area += dfs(i-1, j);
        area += dfs(i, j+1);
        area += dfs(i, j-1);

        return area;
    }
}
