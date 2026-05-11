https://leetcode.com/problems/jump-game-iv/description/

O(n) overall.

  what are we adding in map

  In the map, we store:

value -> all indices having that value

Example:

arr = [100,-23,-23,404,100,23,23,23,3,404]

Map becomes:

100 -> [0, 4]
-23 -> [1, 2]
404 -> [3, 9]
23 -> [5, 6, 7]
3 -> [8]


  when we are doing  visited[next] = true; then why do we need map.get(arr[idx]).clear();

Great question. visited[next] = true prevents revisiting indices, but map.get(arr[idx]).clear() prevents repeatedly iterating the same list again and again.

Example:

arr = [7,7,7,7,7]

Map:

7 -> [0,1,2,3,4]

Start BFS from index 0.

We process:

map.get(7)

which is:

[0,1,2,3,4]

All indices become visited.

Good so far.

Now later BFS reaches index 1.

Again:

for (int next : map.get(arr[idx]))

still loops over:

[0,1,2,3,4]

even though all are already visited.

Then index 2 does it again.

Then 3.

Then 4.

So although visited[] prevents adding duplicates into queue, we still waste time repeatedly traversing the same huge list.

  
class Solution {

    public int minJumps(int[] arr) {

        int n = arr.length;

        if (n == 1) return 0;

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        Queue<Integer> q = new LinkedList<>();

        boolean[] visited = new boolean[n];

        q.offer(0);

        visited[0] = true;

        int steps = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int idx = q.poll();

                if (idx == n - 1) return steps;

                // forward
                if (idx + 1 < n && !visited[idx + 1]) {

                    visited[idx + 1] = true;

                    q.offer(idx + 1);
                }

                // backward
                if (idx - 1 >= 0 && !visited[idx - 1]) {

                    visited[idx - 1] = true;

                    q.offer(idx - 1);
                }

                // same value jumps
                for (int next : map.get(arr[idx])) {

                    if (!visited[next]) {

                        visited[next] = true;

                        q.offer(next);
                    }
                }

                // IMPORTANT optimization
                map.get(arr[idx]).clear();
            }

            steps++;
        }

        return -1;
    }
}
