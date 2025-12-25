class Node {
    int val;
    List<Node> children;

    Node(int val) {
        this.val = val;
        children = new ArrayList<>();
    }
}

public class DiameterNAryTree {

    int diameter = 0;

    public int diameter(Node root) {
        dfs(root);
        return diameter;
    }

    private int dfs(Node node) {
        if (node == null) return 0;

        int max1 = 0; // largest height
        int max2 = 0; // second largest height

        for (Node child : node.children) {
            int h = dfs(child);

            if (h > max1) {
                max2 = max1;
                max1 = h;
            } else if (h > max2) {
                max2 = h;
            }
        }

        // update diameter at this node
        diameter = Math.max(diameter, max1 + max2);

        // return height to parent
        return max1 + 1;
    }
}

🧪 Example Walkthrough

Tree:

        1
     /  |  \
    2   3   4
       / \
      5   6


Heights:

Node 5,6 → 0

Node 3 → max1=1, max2=1 → diameter=2

Root → max1=2 (via 3), max2=1 → diameter=3

✅ Longest path: 5 → 3 → 1 → 2 (3 edges)

⏱️ Complexity
Metric	Value
Time	O(N)
Space	O(H) recursion stack
🎯 Interview One-Liner (USE THIS)

“At each node, I compute the two maximum child heights.
Their sum gives the longest path passing through that node, and I track the global maximum as the diameter.”

