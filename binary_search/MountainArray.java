class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length();

        // 1. Find peak
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        int peak = l;

        // 2. Search left side
        int left = binarySearch(mountainArr, target, 0, peak, true);
        if (left != -1) return left;

        // 3. Search right side
        return binarySearch(mountainArr, target, peak + 1, n - 1, false);
    }

    private int binarySearch(MountainArray arr, int target, int l, int r, boolean asc) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int val = arr.get(mid);

            if (val == target) return mid;

            if (asc) {
                if (val < target) l = mid + 1;
                else r = mid - 1;
            } else {
                if (val > target) l = mid + 1;
                else r = mid - 1;
            }
        }
        return -1;
    }
}

What is a Mountain Array?

An array that:

Strictly increases

Reaches one peak

Strictly decreases

Example:

[1, 3, 5, 7, 6, 4, 2]
           ↑ peak


You are given:

target

a MountainArray interface (you can only call get(i) and length())

Goal:
👉 Return index of target, else -1

🧠 Core Insight 

A mountain array is just:

🔼 Increasing sorted array (left side)

🔽 Decreasing sorted array (right side)

So the plan is:

✅ 3 Binary Searches

Find the peak

Binary search left (ascending)

Binary search right (descending)

All in O(log n) ✔️
