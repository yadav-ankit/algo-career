https://leetcode.com/problems/course-schedule-ii/description/ (true-false)

https://leetcode.com/problems/course-schedule-ii/description/ (order of tasks)


O(v+E)
  
import java.util.*;

class Solution {

    public int[] topoSort(int numCourses, int[][] prerequisites) {

        List<Integer>[] adj = new ArrayList[numCourses];

        for(int i = 0; i < numCourses; i++) adj[i] = new ArrayList<>();


        int[] indegree = new int[numCourses];


        for(int[] pre : prerequisites) {

            int course = pre[0];
            int prerequisite = pre[1];

            adj[prerequisite].add(course);

            indegree[course]++;
        }


        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < numCourses; i++) {

            if(indegree[i] == 0) queue.offer(i);
        }


        int[] order = new int[numCourses];

        int index = 0;


        while(!queue.isEmpty()) {

            int node = queue.poll();

            order[index++] = node;


            for(int neighbor : adj[node]) {

                indegree[neighbor]--;

                if(indegree[neighbor] == 0) queue.offer(neighbor);
            }
        }


        return index == numCourses ? order : new int[0];
    }



    // Course Schedule I
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        return topoSort(numCourses, prerequisites).length == numCourses;
    }



    // Course Schedule II
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        return topoSort(numCourses, prerequisites);
    }



    public static void main(String[] args) {

        Solution s = new Solution();

        int[][] prerequisites = {
                {1,0},
                {2,0},
                {3,1},
                {3,2}
        };


        System.out.println(
                s.canFinish(4, prerequisites)
        );


        System.out.println(
                Arrays.toString(
                        s.findOrder(4, prerequisites)
                )
        );
    }
}
