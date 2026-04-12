import java.util.*;

Includes:

✅ Path Sum
✅ Path XOR
✅ Distance
✅ LCA
✅ K-th Ancestor

    
public class Main {

    static int N = 100005;
    static int LOG = 17;

    static List<Integer>[] tree = new ArrayList[N];
    static int[][] up = new int[N][LOG];
    static int[] depth = new int[N];

    static int[] value = new int[N];
    static int[] dist = new int[N];
    static int[] xor = new int[N];

    // DFS
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
                xor[child] = xor[node] ^ value[child];
                dfs(child, node);
            }
        }
    }

    // LCA
    static int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[u][j];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[u][j] != -1 && up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }

        return up[u][0];
    }

    // K-th ancestor
    static int kthAncestor(int node, int k) {
        for (int j = 0; j < LOG; j++) {
            if ((k & (1 << j)) != 0) {
                node = up[node][j];
                if (node == -1) return -1;
            }
        }
        return node;
    }

    // Path SUM
    static int pathSum(int u, int v) {
        int lca = lca(u, v);
        return dist[u] + dist[v] - 2 * dist[lca] + value[lca];
    }

    // Path XOR
    static int pathXor(int u, int v) {
        int lca = lca(u, v);
        return xor[u] ^ xor[v] ^ value[lca];
    }

    // Distance
    static int distance(int u, int v) {
        int lca = lca(u, v);
        return depth[u] + depth[v] - 2 * depth[lca];
    }

    public static void main(String[] args) {

        int n = 5;

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        // Tree
        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(2, 4);
        addEdge(2, 5);

        // Values
        value[1] = 10;
        value[2] = 5;
        value[3] = 8;
        value[4] = 2;
        value[5] = 3;

        // Root setup
        depth[1] = 0;
        dist[1] = value[1];
        xor[1] = value[1];

        dfs(1, -1);

        // 🔥 Queries

        System.out.println("Path Sum (4,5): " + pathSum(4, 5)); // 10
        System.out.println("Path XOR (4,5): " + pathXor(4, 5)); // 4
        System.out.println("Distance (4,5): " + distance(4, 5)); // 2

        System.out.println();

        System.out.println("Path Sum (4,3): " + pathSum(4, 3)); // 25
        System.out.println("Distance (4,3): " + distance(4, 3)); // 3

        System.out.println();

        // 🔥 K-th Ancestor
        System.out.println("1st ancestor of 5: " + kthAncestor(5, 1)); // 2
        System.out.println("2nd ancestor of 5: " + kthAncestor(5, 2)); // 1
        System.out.println("3rd ancestor of 5: " + kthAncestor(5, 3)); // -1
    }

    static void addEdge(int u, int v) {
        tree[u].add(v);
        tree[v].add(u);
    }
}
