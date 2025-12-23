Given an array of integers, rearrange it into the next lexicographically greater permutation.

If such a permutation is not possible (array is in descending order), rearrange it to the lowest possible order (sorted ascending).

Examples
Input:  [1, 2, 3]
Output: [1, 3, 2]

Input: arr[] = [2, 4, 1, 7, 5, 0]
Output: [2, 4, 5, 0, 1, 7]


Input:  [1, 1, 5]
Output: [1, 5, 1]

⚠️ Constraints:

Modify array in-place
O(1) extra space


    ✅ The 4-Step Algorithm (MUST MEMORIZE)
1️⃣ Find the breakpoint

Traverse from right and find the first index i such that:
nums[i] < nums[i + 1]


If no such index → array is last permutation.

2️⃣ If no breakpoint → reverse whole array
[3,2,1] → [1,2,3]


Done.

3️⃣ Find element just greater than nums[i]

From right, find index j such that:
nums[j] > nums[i]

4️⃣ Swap & Reverse suffix
Swap nums[i] and nums[j]

Reverse array from i + 1 to end
This ensures the next smallest permutation.
    
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // Step 1: find breakpoint
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: if breakpoint exists
        if (i >= 0) {
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // Step 3: reverse suffix
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
}
