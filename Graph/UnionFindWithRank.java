class UnionFind {
    int[] parent;
    int[] rank;

    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return;

        if (rank[pa] < rank[pb]) {
            parent[pa] = pb; // pa will get attached to pb
        } else if (rank[pb] < rank[pa]) {
            parent[pb] = pa;
        } else {
            parent[pb] = pa;
            rank[pa]++;
        }
    }
}


One-line Interview Answer (Perfect)

Time: Amortized O(α(N)) per operation
Space: O(N)


    What does that actually mean?

Grows so slowly that:
α(10¹⁸) ≤ 5

For all real-world inputs: constant time

So in interviews and practice:

find() with path compression ≈ O(1)
