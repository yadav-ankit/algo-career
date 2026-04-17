https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/description/

You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.

Each minute, a node becomes infected if:

The node is currently uninfected.
The node is adjacent to an infected node.
Return the number of minutes needed for the entire tree to be infected.


  binary tree doesn’t have parent pointers → so first convert it into a graph (adjacency list).

Then:

Start BFS from the start node
Each level = 1 minute
Return total time taken to infect all nodes
⚡ Approach
Build graph using DFS
Run BFS from start
Track visited nodes
Count levels (time)
  
  
class Solution {
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        // Step 1: Build graph
        buildGraph(root, null, graph);
        
        // Step 2: BFS
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        
        queue.offer(start);
        visited.add(start);
        
        int time = -1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;
            
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                
                for (int nei : graph.get(node)) {
                    if (!visited.contains(nei)) {
                        visited.add(nei);
                        queue.offer(nei);
                    }
                }
            }
        }
        
        return time;
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
