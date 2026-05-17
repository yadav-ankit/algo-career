
Time Complexity: O(n2) in worst case (O(n) on average).
Auxiliary Space: O(n)
  
class Solution {

    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;

        while (true) {
            int pivotIndex = partition(nums, left, right);

            if (pivotIndex == k - 1)
                return nums[pivotIndex];

            else if (pivotIndex > k - 1)
                right = pivotIndex - 1;

            else
                left = pivotIndex + 1;
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left+1;
        int j = right;

        while (i <= j) {

            if (nums[i] < pivot && nums[j] > pivot) {
                swap(nums, i, j);
                i++;
                j--;
            }

            if (nums[i] >= pivot)
                i++;

            if (nums[j] <= pivot)
                j--;
        }

        swap(nums, left, j);
        return nums[j]; // pivot element
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
