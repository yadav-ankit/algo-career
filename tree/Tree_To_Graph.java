
 Map<Integer, List<Integer>> graph = new HashMap<>();
        
        // Step 1: Build graph
        buildGraph(root, null, graph);


private void buildGraph(TreeNode node, TreeNode parent, 
                            Map<Integer, List<Integer>> graph) {
        if (node == null) return;
        
        graph.putIfAbsent(node.val, new ArrayList<>());
        
        if (parent != null) {
            graph.get(node.val).add(parent.val);
            graph.get(parent.val).add(node.val);
        }
        
        buildGraph(node.left, node, graph);
        buildGraph(node.right, node, graph);
    }
