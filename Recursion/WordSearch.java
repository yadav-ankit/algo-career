https://leetcode.com/problems/word-search/description/

class Solution {

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int idx) {

        // base case: entire word matched
        if (idx == word.length()) {
            return true;
        }

        // boundary + mismatch check
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length
                || board[i][j] != word.charAt(idx)) {
            return false;
        }

        // mark visited
        char temp = board[i][j];
        board[i][j] = '#';

        // explore all 4 directions
        boolean found =
                dfs(board, word, i + 1, j, idx + 1) ||
                dfs(board, word, i - 1, j, idx + 1) ||
                dfs(board, word, i, j + 1, idx + 1) ||
                dfs(board, word, i, j - 1, idx + 1);

        // backtrack
        board[i][j] = temp;

        return found;
    }
}

⏱ Complexity

Time: O(m × n × 4^L)
Space : O(L) // stack
(L = word length)


At each step of DFS we can move in 4 directions, 
  and for a word of length L the recursion tree can grow up to 4^L in the worst case.

Space: O(L) recursion stack
