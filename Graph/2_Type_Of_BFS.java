WITH SIZE

Queue<TreeNode> q = new LinkedList<>();
q.add(root);

while (!q.isEmpty()) {

    int size = q.size(); // nodes in this level

    for (int i = 0; i < size; i++) {
        TreeNode node = q.poll();
        System.out.print(node.val + " ");

        if (node.left != null) q.add(node.left);
        if (node.right != null) q.add(node.right);
    }

    System.out.println(); // level finished
}

------

WITHOUT SIZE

JUST TRAVERSE

Queue<Integer> q = new LinkedList<>();
boolean[] visited = new boolean[n];

q.add(0);
visited[0] = true;

while (!q.isEmpty()) {
    int node = q.poll();
    System.out.print(node + " ");

    for (int nei : graph[node]) {
        if (!visited[nei]) {
            visited[nei] = true;
            q.add(nei);
        }
    }
}

One-line rule (easy to remember)

If the problem asks “how many steps / levels / minutes” → use size()
If it just says “traverse” → don’t


  WITH size()

Rotting Oranges 🍊
Time to Inform Employees
Shortest Path in Matrix
Binary Tree Level Order

✔ WITHOUT size()

👉 When to use

You don’t care about levels
Just want to visit everything

Traverse entire graph
Check connectivity
Count islands (DFS/BFS both fine)
Clone graph
