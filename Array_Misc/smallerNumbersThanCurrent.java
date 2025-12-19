
/*

Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].

Return the answer in an array.

 

Example 1:

Input: nums = [8,1,2,2,3]
Output: [4,0,1,1,3]
*/
public int[] smallerNumbersThanCurrent(int[] nums) {
    int[] count = new int[101]; // value range

    for (int num : nums) {
        count[num]++;
    }

    // prefix sum
    for (int i = 1; i < 101; i++) {
        count[i] += count[i - 1];
    }

    int[] res = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
        res[i] = nums[i] == 0 ? 0 : count[nums[i] - 1];
    }

    return res;
}
