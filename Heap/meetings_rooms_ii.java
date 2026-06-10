Problem: Given meeting intervals, find the minimum number of conference rooms required.

Example
[[0,30],[5,10],[15,20]]

Answer: 2


Approach: Min Heap
Sort meetings by start time.
Keep a min heap of meeting end times.
For each meeting:
If the earliest ending meeting has ended (heap.peek() <= start), reuse that room.
Otherwise, allocate a new room.
Heap size = rooms currently in use.
Maximum heap size = minimum rooms required.


  why cant we do simply after sorting that if curr.end < prev.start then room++
  
  The fundamental issue is that you are comparing against one meeting, while the real problem is:
  "Among ALL meetings currently running, has any room become free?"

  
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] interval : intervals) {
            if (!pq.isEmpty() && pq.peek() <= interval[0]) {
                pq.poll();
            }

            pq.offer(interval[1]);
        }

        return pq.size();
    }
}


this can also be done by difference array 

  min number of platforms problem

  https://algo.monster/liteproblems/253

  class Solution {
    public int minMeetingRooms(int[][] intervals) {
        // Find the maximum end time among all intervals
        int maxEndTime = 0;
        for (int[] interval : intervals) {
            maxEndTime = Math.max(maxEndTime, interval[1]);
        }

        // Create a difference array to track room usage changes at each time point
        // Index represents time, value represents change in number of rooms needed
        int[] differenceArray = new int[maxEndTime + 1];

        // Mark the start and end of each meeting in the difference array
        // +1 at start time means one more room is needed
        // -1 at end time means one room is freed
        for (int[] interval : intervals) {
            differenceArray[interval[0]]++;
            differenceArray[interval[1]]--;
        }

        // Calculate the maximum number of rooms needed at any point in time
        int maxRooms = 0;
        int currentRooms = 0;

        // Iterate through time and accumulate the room count
        for (int roomChange : differenceArray) {
            currentRooms += roomChange;  // Update current rooms in use
            maxRooms = Math.max(maxRooms, currentRooms);  // Track maximum rooms needed
        }

        return maxRooms;
    }
}
