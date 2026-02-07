import java.util.*;

// https://leetcode.com/problems/most-profit-assigning-work/description/

Java Solution (Greedy + Sorting)
Idea Recap

Sort jobs by difficulty

Sort workers by ability

Sweep jobs and keep track of the best profit so far

For each worker, assign the best possible job

Time: O(n log n)
Space: O(n)

class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        
        // Pair difficulty and profit
        int[][] jobs = new int[n][2];
        for (int i = 0; i < n; i++) {
            jobs[i][0] = difficulty[i];
            jobs[i][1] = profit[i];
        }
        
        // Sort jobs by difficulty
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        // Sort workers by ability
        Arrays.sort(worker);
        
        int totalProfit = 0;
        int bestProfit = 0;
        int i = 0;
        
        // Two pointer sweep
        for (int w : worker) {
            while (i < n && jobs[i][0] <= w) {
                bestProfit = Math.max(bestProfit, jobs[i][1]);
                i++;
            }
            totalProfit += bestProfit;
        }
        
        return totalProfit;
    }
}
