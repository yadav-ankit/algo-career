Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one
sorted array.
Note:
The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m +
n) to hold additional elements from nums2.
Example:
Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]


Since nums1 already has empty space at the end, we merge from the back.

Why?

If we merge from front, we overwrite useful data.

From back = safe.

🎯 Three Pointers

i = m - 1 → last real element of nums1

j = n - 1 → last element of nums2

k = m + n - 1 → last position in nums1

  
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1;       // nums1 pointer
        int j = n - 1;       // nums2 pointer
        int k = m + n - 1;   // merge position

        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
    }
}
