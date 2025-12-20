/*
🔹 Optimal approach: Binary Search (O(log n))
🔹 Example
arr = [1, 3, 20, 4, 1, 0]


Peak = 20

Key idea 🧠

Edge cases:

arr[0] is peak if arr[0] >= arr[1]

arr[n-1] is peak if arr[n-1] >= arr[n-2]

If:

  arr[mid] < arr[mid + 1] → peak exists on the right
  
  arr[mid] > arr[mid + 1] → peak exists on the left (including mid)

*/


static int findPeak(int[] arr) {
    int l = 0, r = arr.length - 1;

    while (l < r) {
        int mid = l + (r - l) / 2;

        if (arr[mid] < arr[mid + 1]) {
            l = mid + 1;   // move right
        } else {
            r = mid;       // move left (mid can be peak)
        }
    }
    return l; // index of a peak
}
