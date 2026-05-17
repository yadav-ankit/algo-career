
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

  // objective is aftet this method pivot is placed at right index ..so all left elements are smaller and right r bigger
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left+1;
        int j = right;

        while (i <= j) {

          // saying: if nums[i] is smaller then pivot and nums[j[ is bigger then please swap
          // i want pivot to correctly placed
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


--------


  class Solution {

    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right)
            return;

        int pivotIndex = partition(nums, left, right);

        quickSort(nums, left, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left;
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

        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
