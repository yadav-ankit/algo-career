class Solution {

    int total = 0;

    public int subsetXORSum(int[] nums) {
        dfs(nums, 0, 0);
        return total;
    }

    private void dfs(int[] nums, int index, int currentXor) {

        // base case: one subset formed
        if (index == nums.length) {
            total += currentXor;
            return;
        }

        //  exclude nums[index]
        dfs(nums, index + 1, currentXor);

        // include nums[index]
        dfs(nums, index + 1, currentXor ^ nums[index]);
    }
}

⏱ Complexity

Time: O(2^n)
Space: O(n) (recursion stack)
-----------------

Solution 2 

✨ Formula
answer = (bitwise OR of nums) × 2^(n - 1)

class Solution {
    public int subsetXORSum(int[] nums) {
        int or = 0;

        for (int num : nums) {
            or |= num;
        }

        return or * (1 << (nums.length - 1));
    }
}

Time: O(n)

Space: O(1)

