Problem

Given an array of positive integers and an integer k, find the minimum length of a contiguous subarray whose sum ≥ k.

If no such subarray exists, return 0.

Example
nums = [2, 3, 1, 2, 4, 3]
k = 7
Output = 2
Explanation: [4, 3] → sum = 7

⭐ Optimal Solution — Prefix Sum + HashMap
Key Insight (This is the magic)

If: currentSum - K = previousSum

then the subarray between them sums to K.

 int smallestSubarraySumK(int arr[], int n, int K)
{
    // Use map here to store the prefixSum ending
    // at ith index.
    HashMap<Integer,Integer> mp = new HashMap<Integer,Integer>();

    // Store the current Prefix sum till ith
    // index;
    int currPrefixSum = 0;

    // Store the minimum size subarray whose
    // sum is K
    int result = Integer.MAX_VALUE;

    for(int i = 0; i < n; i++){
        currPrefixSum += arr[i];

        // Check if the current prefix sum is
        // equals to K
        if(currPrefixSum == K){
            int currLen = i + 1;
            result = Math.min(result, currLen);
        }

        // Required PrefixSum
        int requirePrefixSum = currPrefixSum - K;

        // Check if there exist any required Prefix Sum or not
        if(mp.containsKey(requirePrefixSum)){
            int foundIdx = mp.get(requirePrefixSum);
            int currIdx = i;

            result = Math.min(result, currIdx  - foundIdx);
        }
       
        // Store the current prefix sum ending at i
        mp.put(currPrefixSum, i);
    }

    if(result >= Integer.MAX_VALUE) return -1;
    
    // return the result
    return result;
}


}
