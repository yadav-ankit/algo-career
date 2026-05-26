Given n islands, each islands has some value. Connections are there between some islands.
Select a group of 3 islands A, B, C such that they are directly connected (no other island in between) and the sum of values of these 3 islands is maximum.


Solved this using graph traversal (store top 3 connected islands at the beginning of adjacency list,
and travel 3 level of bfs from each island)


Build graph
For each node, store top 3 neighbors by island value
From each island A, traverse:
A -> B
B -> C
Ensure all 3 islands are distinct
Track maximum sum


  int n = 4;
int[] values = {5,10,8,7};

int[][] edges = {
    {0,1},
    {1,2},
    {1,3}
};

Solution s = new Solution();

System.out.println(s.maxTripletValue(n, values, edges));

import java.util.*;

class Solution {

    public int maxTripletValue(int n, int[] values, int[][] edges) {

        List<Integer>[] topNeighbors = new ArrayList[n];
        for(int i = 0; i < n; i++) topNeighbors[i] = new ArrayList<>();

        // Store top 3 neighbors by value
        for(int[] edge : edges) {
            addNeighbor(topNeighbors[edge[0]], edge[1], values);
            addNeighbor(topNeighbors[edge[1]], edge[0], values);
        }

        int max = -1;

        // Traverse A -> B -> C
        for(int a = 0; a < n; a++) {

            for(int b : topNeighbors[a]) {

                if(b == a) continue;

                for(int c : topNeighbors[b]) {

                    if(c == a || c == b) continue;

                    max = Math.max(max, values[a] + values[b] + values[c]);
                }
            }
        }

        return max;
    }

  // basically top 3(highest node value) hi add krene h 
    private void addNeighbor(List<Integer> neighbors, int node, int[] values) {
      
        neighbors.add(node);
      
        int i = neighbors.size() - 1;
      
        while(i > 0 && values[neighbors.get(i)] > values[neighbors.get(i - 1)]) {
            Collections.swap(neighbors, i, i - 1);
            i--;
        }
      
        if(neighbors.size() > 3) {
            neighbors.remove(3);
        }
      }
}
