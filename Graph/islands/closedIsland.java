https://github.com/doocs/leetcode/blob/main/solution/1200-1299/1254.Number%20of%20Closed%20Islands/README_EN.md


For closed islands, a void dfs() alone is not enough, because besides visiting cells, you also need to know:

Did this island touch the boundary?

Your void dfs() loses that information.
    
class Solution {

    int rows;
    int cols;
    int[][] grid;

    public int closedIsland(int[][] grid) {

        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;

        int count = 0;

        for(int i = 0; i < rows; i++) {

            for(int j = 0; j < cols; j++) {

                if(grid[i][j] == 0 && dfs(i, j)) count++;
            }
        }

        return count;
    }


    boolean dfs(int i, int j) {

        grid[i][j] = 1;

        boolean isClosed = !(i == 0 || j == 0 || i == rows - 1 || j == cols - 1);

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        for(int[] dir : dirs) {

            int ni = i + dir[0];
            int nj = j + dir[1];

            if(ni >= 0 && nj >= 0 && ni < rows && nj < cols && grid[ni][nj] == 0) {

                isClosed = dfs(ni, nj) && isClosed;
            }
        }

        return isClosed;
    }
}
