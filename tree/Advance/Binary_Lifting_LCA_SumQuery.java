import java.util.*;

public class Main {

    static int N = 100005;
    static int LOG = 17;

    static List<Integer>[] tree = new ArrayList[N];
    static int[][] up = new int[N][LOG];
    static int[] depth = new int[N];
    static int[] value = new int[N];
    static int[] dist = new int[N]; // root → node sum

    // DFS to build depth, dist, and binary lifting table
    static void dfs(int node, int parent) {
        up[node][0] = parent;

        for (int j = 1; j < LOG; j++) {
            if (up[node][j - 1] != -1)
                up[node][j] = up[up[node][j - 1]][j - 1];
            else
                up[node][j] = -1;
        }

        for (int child : tree[node]) {
            if (child != parent) {
                depth[child] = depth[node] + 1;
                dist[child] = dist[node] + value[child];
                dfs(child, node);
            }
        }
    }

    // LCA using binary lifting
    static int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // bring u to same depth as v
        int diff = depth[u] - depth[v];
        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[u][j];
            }
        }

        if (u == v) return u;

        // lift both up
        for (int j = LOG - 1; j >= 0; j--) {
            if (up[u][j] != -1 && up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }

        return up[u][0];
    }

    // Path sum query
    static int pathSum(int u, int v) {
        int lca = lca(u, v);
        return dist[u] + dist[v] - 2 * dist[lca] + value[lca];
    }

    public static void main(String[] args) {

        int n = 5;

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        // Tree edges
        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(2, 4);
        addEdge(2, 5);

        // Node values
        value[1] = 10;
        value[2] = 5;
        value[3] = 8;
        value[4] = 2;
        value[5] = 3;

        // Initialize root
        depth[1] = 0;
        dist[1] = value[1];

        dfs(1, -1);

        // Queries
        System.out.println(pathSum(4, 5)); // 10
        System.out.println(pathSum(4, 3)); // 2+5+10+8 = 25
    }

    static void addEdge(int u, int v) {
        tree[u].add(v);
        tree[v].add(u);
    }
}
