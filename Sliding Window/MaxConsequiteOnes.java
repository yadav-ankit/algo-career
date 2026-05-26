Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most k 0's. 

 https://leetcode.com/problems/max-consecutive-ones-iii/description/


class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0, zeroCount = 0, max = 0;

        for(int right = 0; right < nums.length; right++) {
            if(nums[right] == 0) zeroCount++;

            while(zeroCount > k) {
                if(nums[left] == 0) zeroCount--;
                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}

