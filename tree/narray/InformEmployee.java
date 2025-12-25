🧩 Problem Summary (In Simple Words)

There are n employees (0 to n-1)

One head employee starts the information

Each employee informs their direct subordinates

informTime[i] = time employee i takes to inform all direct subordinates

Goal: total time to inform everyone



  
class Solution {
    Map<Integer, List<Integer>> map = new HashMap<>();
    int[] informTime;

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        this.informTime = informTime;

        // Build map: manager -> employees
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(i, new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                map.get(manager[i]).add(i);
            }
        }

        return dfs(headID);
    }

    private int dfs(int node) {
        int max = 0;

        for (int child : map.get(node)) {
            max = Math.max(max, dfs(child));
        }

        return informTime[node] + max;
    }
}

⏱️ Complexity
Metric	Value
  
Time	O(N)
  
Space	O(N) (tree + recursion)

        🌳 Key Insight (THIS IS IMPORTANT)

The organization forms a tree
The answer is the maximum time from head to any leaf

This is depth / longest path in a tree with weights.

  
        
🧠 Step-by-Step Strategy

Build the tree using manager[]

Start DFS from headID

For each node:

Ask children how long they take

Take the maximum

Add informTime[current]
