https://leetcode.com/problems/jump-game-iii/description/

Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.

Notice that you can not jump outside of the array at any time.

⏱ Complexity

Each index processed once:

O(n) time

O(n) space


class Solution {

    public boolean canReach(int[] arr, int start) {

        int n = arr.length;

        Queue<Integer> q = new LinkedList<>();

        boolean[] visited = new boolean[n];

        q.offer(start);

        visited[start] = true;

        while (!q.isEmpty()) {

            int idx = q.poll();

            if (arr[idx] == 0) return true;

            int forward = idx + arr[idx];

            int backward = idx - arr[idx];

            if (forward < n && !visited[forward]) {

                visited[forward] = true;

                q.offer(forward);
            }

            if (backward >= 0 && !visited[backward]) {

                visited[backward] = true;

                q.offer(backward);
            }
        }

        return false;
    }
}


dfs

    class Solution {

    public boolean canReach(int[] arr, int start) {

        return dfs(arr, start, new boolean[arr.length]);
    }

    private boolean dfs(int[] arr, int idx, boolean[] visited) {

        if (idx < 0 || idx >= arr.length || visited[idx]) return false;

        if (arr[idx] == 0) return true;

        visited[idx] = true;

        return dfs(arr, idx + arr[idx], visited)
            || dfs(arr, idx - arr[idx], visited);
    }
}
