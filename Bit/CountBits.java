
class Solution {
    
    //  xor & 1 → checks last bit

//     xor >>= 1 → moves to the next bit
    
    public int countBitsFlip(int A, int B) {
        int xor = A ^ B;
        int count = 0;

      while (xor != 0) {
            count += xor & 1;
            xor >>= 1;
        }
        return count;
    }
}
