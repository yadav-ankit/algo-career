/*
What “floor” really means

  Floor = greatest element ≤ x
  
  So once you find any value ≤ x, you don’t stop — you try to find a bigger one that is still ≤ x.
  
  That bigger candidate can only exist on the RIGHT side. hence l = mid + 1;
*/
static int floor(int[] arr, int x) {
    int l = 0, r = arr.length - 1;
    int ans = -1;

    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (arr[mid] <= x) {
            ans = arr[mid];
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }
    return ans;
}

static int ceil(int[] arr, int x) {
    int l = 0, r = arr.length - 1;
    int ans = -1;

    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (arr[mid] >= x) {
            ans = arr[mid];
            r = mid - 1;
        } else {
            l = mid + 1;
        }
    }
    return ans;
}
