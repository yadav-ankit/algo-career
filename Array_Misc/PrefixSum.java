/*
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

If:

currentSum - K = previousSum


then the subarray between them sums to K.
*/

public int subarraySum(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1); // base case

    int sum = 0;
    int count = 0;

    for (int num : nums) {
        sum += num;

        if (map.containsKey(sum - k)) {
            count += map.get(sum - k);
        }

        map.put(sum, map.getOrDefault(sum, 0) + 1);
    }

    return count;
}
