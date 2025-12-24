

Swap
a = a ^ b;
b = a ^ b;
a = a ^ b;

Simple (overflow risk)
a = a + b;
b = a - b;
a = a - b;


Count number of bits to be flipped to convert A to B
class Solution {
    public int countBitsFlip(int A, int B) {
        int xor = A ^ B;
        int count = 0;

        while (xor != 0) {
            xor = xor & (xor - 1);
            count++;
        }
        return count;
    }
}

