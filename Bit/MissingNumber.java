class Solution {
    public int missingNumber(int[] arr, int n) {
        int xor = 0;

        // XOR all numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }

        // XOR all array elements
        for (int x : arr) {
            xor ^= x;
        }

        return xor;
    }
}
