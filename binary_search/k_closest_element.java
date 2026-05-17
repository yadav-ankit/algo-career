https://leetcode.com/problems/find-k-closest-elements/description/

class Solution {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;

      // objective is to find 'left' element
        while (left < right) {
            int mid = left + (right - left) / 2;

          // jo chota h vha jaao...
            if (x - arr[mid] > arr[mid + k] - x)
                left = mid + 1;
            else
                right = mid;
        }

        List<Integer> ans = new ArrayList<>();

  
        for (int i = left; i < left + k; i++)
            ans.add(arr[i]);

        return ans;
    }
}


Since arr is already sorted, the answer will always be a contiguous window of size K.

Example:

arr = [1,2,3,4,5]
k = 4
x = 3

Possible windows:

[1,2,3,4]
[2,3,4,5]

Need to decide:

Should window start at index 0 or 1?

Compare:

x - arr[mid]

vs

arr[mid + k] - x

Interpretation:

left edge of window     vs      element just outside right edge

If:

x - arr[mid] > arr[mid+k] - x

then:

right-side candidate is closer → shift window right

Otherwise:

current window is better → move left

That gives binary search.
