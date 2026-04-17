https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/

Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.


USE https://github.com/yadav-ankit/algo-career/blob/main/tree/Tree_To_Graph.java

why are you not checking for k every time ..why only once ?

What’s really happening

In BFS:

Level 0 → target
Level 1 → nodes at distance 1
Level 2 → nodes at distance 2
...

And we track this using:

int size = queue.size();

So each outer loop iteration = one full level = one distance



class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        // Step 1: Build graph
        buildGraph(root, null, graph);
        
        // Step 2: BFS
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        
        queue.offer(target.val);
        visited.add(target.val);
        
        int dist = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            if (dist == k) {
                return new ArrayList<>(queue);
            }
            
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                
                for (int nei : graph.get(node)) {
                    if (!visited.contains(nei)) {
                        visited.add(nei);
                        queue.offer(nei);
                    }
                }
            }
            
            dist++;
        }
        
        return new ArrayList<>();
    }
    
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
}
