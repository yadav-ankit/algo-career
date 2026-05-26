
https://leetcode.com/problems/longest-mountain-in-array/description/


class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int max = 0;
        int i = 1;

        while(i < n - 1) {

            boolean isPeak = arr[i] > arr[i - 1] && arr[i] > arr[i + 1];

            if(isPeak) {
                int left = i;
                int right = i;

                while(left > 0 && arr[left] > arr[left - 1]) left--;
                while(right < n - 1 && arr[right] > arr[right + 1]) right++;

                max = Math.max(max, right - left + 1);

                i = right; // skip processed mountain
            }

            i++;
        }

        return max;
    }
}
