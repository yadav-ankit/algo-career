
🧠 Key Insight (this is the whole trick)

XOR (^) properties:

a ^ a = 0

a ^ 0 = a

XOR is commutative and associative

👉 When you XOR all numbers:

All pairs cancel out

The unique number remains

class Solution {
    public int findUnique(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }
        return ans;
    }
}
