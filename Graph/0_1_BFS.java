0-1 BFS is a shortest path algorithm used when every edge weight is either 0 or 1.
  
Instead of a Priority Queue (like Dijkstra), it uses a Deque.
When to Use
Graph edges have only two possible weights:
0 or 1

  Examples:
Minimum obstacles to remove
Minimum direction changes
Teleport (cost 0) vs Walk (cost 1)
Binary weighted graphs
  
Time Complexity:
O(V + E)

  Better than Dijkstra:
O((V + E) log V)

  Key Idea
Maintain a deque.
  
If edge weight is 0
Insert at front

  If edge weight is 1
Insert at back

  Thus vertices are processed in increasing distance order without a priority queue.
  
class Edge {
    int to, wt;

    Edge(int t, int w) {
        to = t;
        wt = w;
    }
}

public int[] zeroOneBFS(List<List<Edge>> graph, int src) {

    int n = graph.size();

    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);

    Deque<Integer> dq = new ArrayDeque<>();

    dist[src] = 0;
    dq.offerFirst(src);

    while (!dq.isEmpty()) {

        int u = dq.pollFirst();

        for (Edge e : graph.get(u)) {

            int v = e.to;
            int w = e.wt;

            if (dist[u] + w < dist[v]) {

                dist[v] = dist[u] + w;

                if (w == 0)
                    dq.offerFirst(v);
                else
                    dq.offerLast(v);
            }
        }
    }

    return dist;
}
