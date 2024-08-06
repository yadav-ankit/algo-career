import java.util.Arrays;

public class CountSmallerElements {
    public static void main(String[] args) {
        int[] arr = {8, 1, 2, 2, 3};
        int[] result = countSmallerElements(arr);

        // Print the result
        System.out.println(Arrays.toString(result));
    }

    public static int[] countSmallerElements(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            temp[i] = i;
        }

        mergeSort(arr, temp, 0, n, result);
        return result;
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right, int[] result) {
        if (right - left <= 1) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid, result);
        mergeSort(arr, temp, mid, right, result);

        merge(arr, temp, left, mid, right, result);
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right, int[] result) {
        int[] tempIndexes = new int[right - left];
        int i = left, j = mid, k = 0, rightCount = 0;

        while (i < mid && j < right) {
            if (arr[temp[i]] <= arr[temp[j]]) {
                tempIndexes[k++] = temp[i];
                result[temp[i]] += rightCount;
                i++;
            } else {
                tempIndexes[k++] = temp[j];
                rightCount++;
                j++;
            }
        }

        while (i < mid) {
            tempIndexes[k++] = temp[i];
            result[temp[i]] += rightCount;
            i++;
        }

        while (j < right) {
            tempIndexes[k++] = temp[j];
            j++;
        }

        System.arraycopy(tempIndexes, 0, temp, left, right - left);
    }
}
