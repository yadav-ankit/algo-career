https://leetcode.com/problems/surrounded-regions/description/


Key observation 

Instead of finding:

Which O is surrounded?

Think:

Which O is NOT surrounded?

Boundary Os and connected Os are safe.

Steps:

Traverse all boundary cells
DFS all connected Os → mark as safe (#)
Convert remaining:
O → X
# → O

This is the pattern: Boundary DFS/BFS

class Solution {

    int rows;
    int cols;
    char[][] board;

    public void solve(char[][] board) {

        this.board = board;

        rows = board.length;
        cols = board[0].length;


        // first row + last row
        for(int j = 0; j < cols; j++) {

            dfs(0, j);

            dfs(rows - 1, j);
        }


        // first col + last col
        for(int i = 0; i < rows; i++) {

            dfs(i, 0);

            dfs(i, cols - 1);
        }


        for(int i = 0; i < rows; i++) {

            for(int j = 0; j < cols; j++) {

                if(board[i][j] == 'O') board[i][j] = 'X';

                else if(board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }


    void dfs(int i, int j) {

        if(i < 0 || j < 0 || i >= rows || j >= cols || board[i][j] != 'O') return;

        board[i][j] = '#';


        dfs(i + 1, j);
        dfs(i - 1, j);
        dfs(i, j + 1);
        dfs(i, j - 1);
    }
}
