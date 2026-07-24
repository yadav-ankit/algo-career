https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/description/

You are given an array of integers arr. The task is to count how many triplets of indices (i, j, k) exist such that a specific condition is met.

For each triplet, you need to select three indices where 0 <= i < j <= k < arr.length. These indices divide the array into two segments:

Segment a: XOR of all elements from index i to j-1 (i.e., arr[i] ^ arr[i+1] ^ ... ^ arr[j-1])
Segment b: XOR of all elements from index j to k (i.e., arr[j] ^ arr[j+1] ^ ... ^ arr[k])
The goal is to find all triplets where a == b.

The key insight is that if a == b, then a ^ b = 0. This means the XOR of all elements from index i to k equals 0. When this happens, we can place j at any position between i+1 and k (inclusive), and the condition a == b will be satisfied.

The solution iterates through each possible starting position i, then for each ending position k, it calculates the XOR sum of elements from i to k. When this XOR sum equals 0, it means we've found k - i valid triplets (since j can be placed at any of the k - i positions between i+1 and k).

For example, if arr = [2, 3, 1, 6, 7] and we find that XOR from index 0 to 2 equals 0 (i.e., 2 ^ 3 ^ 1 = 0), then we have 2 valid triplets: (0, 1, 2) and (0, 2, 2).


  (ANKIT - NOTE THAT J & K can be at same location but they should be not at i)  (0 <= i < j <= k < arr.length).
class Solution {
    public int countTriplets(int[] arr) {
        int tripletCount = 0;
        int arrayLength = arr.length;
      
        // Iterate through each possible starting position i
        for (int i = 0; i < arrayLength; ++i) {
            // Initialize XOR sum starting from index i
            int xorSum = arr[i];
          
            // Iterate through each possible ending position k (where k > i)
            for (int k = i + 1; k < arrayLength; ++k) {
                // Accumulate XOR sum from index i to k
                xorSum ^= arr[k];
              
                // If XOR sum from i to k equals 0, it means arr[i] ^ ... ^ arr[k] = 0
                // This implies arr[i] ^ ... ^ arr[j-1] = arr[j] ^ ... ^ arr[k]
                // There are (k - i) valid values for j, where j can be from i+1 to k
                if (xorSum == 0) {
                    tripletCount += k - i;
                }
            }
        }
      
        return tripletCount;
    }
}
