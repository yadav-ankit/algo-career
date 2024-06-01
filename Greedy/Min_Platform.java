/*
  https://github.com/Seanforfun/Algorithm-and-Leetcode/blob/master/leetcode/253.%20Meeting%20Rooms%20II.md

  https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/


  both are same. First question convert to second 
  then apply sort & two pointer


Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
*/

 class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int len = intervals.length;
        int[] startTime = new int[len];
        int[] endTime = new int[len];
        int index = 0;
        for(int[] interval: intervals){
            startTime[index] = interval[0];
            endTime[index++] = interval[1];
        }
        Arrays.sort(startTime);
        Arrays.sort(endTime);
        int i = 0, j = 0;
        int activate = 0, max = 0;
        while(i < len && j < len){
            if(startTime[i] < endTime[j]){
                activate++;
                i++;
            }else{
                activate--;
                j++;
            }
            max = Math.max(max, activate);
        }
        return max;
    }
}
