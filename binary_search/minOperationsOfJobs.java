You are given n jobs that run in parallel on a processor.



Each job i has a required execution time executionTime[i].

 

The processor runs in operations. In each operation:

You choose one job as the major job.
The major job runs for x seconds.
Every other unfinished job runs for y seconds, where y < x.
A job is considered complete once its total executed time reaches or exceeds its required execution time and it leaves the pool.
 

You need to find the minimum number of operations needed for all jobs to complete.

 

Example
Suppose n = 5, executionTime = [3, 4, 1, 7, 6], x = 4, and y = 2.

Output: 3

Explanation:

Choose job 4 as the major job:
Reduce the execution time of job 4 by x and other jobs by y.
executionTime becomes [1, 2, -1, 3, 4]
Job 3 completes.
Choose job 4 again: executionTime becomes [-1, 0, -, -1, 2]. Jobs 1, 2, and 4 complete.
Choose job 5: executionTime becomes [-, -, -, -, -2]. Job 5 completes.



class Solution {

    public int minOperations(int[] executionTime, int x, int y) {
        int low = 0;
        int high = 1_000_000_000;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (canFinish(executionTime, x, y, mid))
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }

    private boolean canFinish(int[] jobs, int x, int y, int ops) {
        long needed = 0;
        long extra = x - y;

        for (int job : jobs) {
            long remaining = job - (long) ops * y;

            if (remaining > 0) {
                needed += (remaining + extra - 1) / extra;

                if (needed > ops)
                    return false;
            }
        }

        return needed <= ops;
    }
}


This is a binary search on answer problem.

Think:

If we do k operations total:

Every job automatically gets k * y execution time (because in each operation, non-major unfinished jobs run for y, and making a job major only increases its gain).
A major selection gives an extra (x - y) to that job.

For a job with required time t:

After k*y, remaining work:

remaining = t - k*y

If remaining <= 0, no need to make it major.

Else, number of times it must be chosen as major:

ceil(remaining / (x-y))

Sum this for all jobs:

totalMajorNeeded <= k

Then k operations are feasible.

So:

Binary search on answer k
Check feasibility using above logic
