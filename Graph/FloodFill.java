/*
  https://www.geeksforgeeks.org/dsa/flood-fill-algorithm/#approach-2-using-breadthfirst-search-om-n-time-and-om-n-space

  asi,ple BFS but the point is to use 2d array . use q iterate etc ..should be pn fingertips.
*/
import java.util.Queue;
import java.util.LinkedList;
public class GFG {
    public static int[][] floodFill(int[][] img, int sr,
        int sc, int newColor) {

        // If the starting pixel already has the new color
        if (img[sr][sc] == newColor) {
            return img;
        }

        // Direction vectors for traversing 4 directions
        int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Queue<int[]> q = new LinkedList<>();
        int oldColor = img[sr][sc];
        q.add(new int[]{sr, sc});

        // Change the color of the starting pixel
        img[sr][sc] = newColor;

        // Perform BFS
        while (!q.isEmpty()) {
            int[] front = q.poll();
            int x = front[0], y = front[1];

            // Traverse all 4 directions
            for (int[] it : dir) {
                int nx = x + it[0];
                int ny = y + it[1];

                // Check boundary conditions and color match
                if (nx >= 0 && nx < img.length &&ny >= 0 && ny < img[0].length && 
                  img[nx][ny] == oldColor) {
                    img[nx][ny] = newColor;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        return img;
    }

    public static void main(String[] args) {
        int[][] img = {
            {1, 1, 1, 0},
            {0, 1, 1, 1},
            {1, 0, 1, 1}
        };

        int sr = 1, sc = 2;
        int newColor = 2;

        int[][] result = floodFill(img, sr, sc, newColor);

        for (int[] row : result) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }
}
