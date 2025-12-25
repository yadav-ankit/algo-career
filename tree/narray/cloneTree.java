Node cloneTree(Node root) {
    if (root == null) return null;

    // Step 1: clone current node
    Node clone = new Node(root.val);

    // Step 2: clone all children
    for (Node child : root.children) {
        clone.children.add(cloneTree(child));
    }

    return clone;
}
