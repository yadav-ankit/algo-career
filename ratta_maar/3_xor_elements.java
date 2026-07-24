https://leetcode.com/problems/number-of-unique-xor-triplets-i/

Why does this work?
Because:
a ^ a ^ b = b, so repeated indices let you obtain every original number.
Once n ≥ 3, the set {1,2,...,n} spans all bit positions up to the most significant bit of n.
XOR behaves like vector addition over GF(2). With enough basis elements available, 
  three-element XORs (allowing repeated indices) generate every bit pattern of that width. 
  Thus the answer is simply the size of that bit space: the next power of two.
  
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if (n <= 2)
            return n;

        int ans = 1;

        while (ans < n)
            ans = ans * 2;

        return ans;
    }
}
