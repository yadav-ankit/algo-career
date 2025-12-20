/*

 duplicates case add after Line no. 19
 
        if (arr[l] == arr[mid] && arr[mid] == arr[r]) {
            l++;
            r--;
        }

*/


static int search(int[] arr, int target) {
    int l = 0, r = arr.length - 1;

    while (l <= r) {
        int mid = l + (r - l) / 2;

        if (arr[mid] == target) return mid;

        // left half sorted
        if (arr[l] <= arr[mid]) {
            if (arr[l] <= target && target < arr[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        // right half sorted
        else {
            if (arr[mid] < target && target <= arr[r]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
    }
    return -1;
}
