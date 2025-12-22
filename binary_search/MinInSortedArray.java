public static int findMinWithoutDuplicates(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid element is greater than rightmost,
            // the minimum lies in the right half
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } 
            // Otherwise, the minimum lies in the left half (including mid)
            else {
                right = mid;
            }
        }
        // left == right pointing to minimum element
        return nums[left];
    }


public static int findMinWithDuplicates(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Case 1: right half is unsorted → min is there
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
            // Case 2: left half (including mid) contains min
            else if (nums[mid] < nums[right]) {
                right = mid;
            }
            // Case 3: nums[mid] == nums[right] → cannot decide
            else {
                right--; // shrink search space
            }
        }
        return nums[left];
    }
