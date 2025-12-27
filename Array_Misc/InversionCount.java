
class Solution {

    public static long inversionCount(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int[] temp = new int[arr.length];
        return mergeSort(arr, temp, 0, arr.length - 1);
    }

    private static long mergeSort(int[] arr, int[] temp, int left, int right) {
        long invCount = 0;

        if (left < right) {
            int mid = left + (right - left) / 2;

            invCount += mergeSort(arr, temp, left, mid);
            invCount += mergeSort(arr, temp, mid + 1, right);
            invCount += merge(arr, temp, left, mid, right);
        }
        return invCount;
    }

    private static long merge(int[] arr, int[] temp,
                              int left, int mid, int right) {

        int i = left;     // left subarray pointer
        int j = mid + 1;  // right subarray pointer
        int k = left;     // temp array pointer
        long invCount = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                // all remaining elements in left subarray are inversions
                invCount += (mid - i + 1);
            }
        }

        while (i <= mid)
            temp[k++] = arr[i++];

        while (j <= right)
            temp[k++] = arr[j++];

        // copy back
        for (i = left; i <= right; i++)
            arr[i] = temp[i];

        return invCount;
    }
}



✅ Example
arr = [4, 3, 2, 1]


Inversions:

(4,3) (4,2) (4,1)
(3,2) (3,1)
(2,1)
Total = 6


Swap allowed
Inversion count = 6 Adjacent only (conceptually)
Minimum swaps = 3 Any two



