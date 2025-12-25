class Node {
    int val;
    List<Node> children;

    Node(int val) {
        this.val = val;
        children = new ArrayList<>();
    }
}

void postOrder(Node root) {
    if (root == null) return;

    for (Node child : root.children) {
        postOrder(child);
    }

    System.out.print(root.val + " ");
}



void levelOrder(Node root) {
    if (root == null) return;

    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        Node node = queue.poll();
        System.out.print(node.val + " ");

        for (Node child : node.children) {
            queue.offer(child);
        }
    }
}

