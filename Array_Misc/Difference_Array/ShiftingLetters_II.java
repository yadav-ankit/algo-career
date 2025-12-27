https://leetcode.com/problems/shifting-letters-ii/description/

class Solution {
    public String shiftingLetters(String s, int[][] shifts) {

        int n = s.length();

        // diff[i] will store how much shift starts/ends at index i
        int[] diff = new int[n];

        // STEP 1: Build the difference array
        for (int[] query : shifts) {
            int L = query[0];
            int R = query[1];
            int dir = query[2];

            // dir == 0 → left shift (-1), dir == 1 → right shift (+1)
            int x = (dir == 0) ? -1 : 1;

            diff[L] += x;
            if (R + 1 < n) {
                diff[R + 1] -= x;
            }
        }

        // STEP 2: Prefix sum to get net shift at each index
        for (int i = 1; i < n; i++) {
            diff[i] += diff[i - 1];
        }

        // Convert string to char array (Java strings are immutable)
        char[] chars = s.toCharArray();

        // STEP 3: Apply shifts
        for (int i = 0; i < n; i++) {
            int shift = diff[i] % 26;   // wrap around alphabet

            if (shift < 0) {
                shift += 26;           // handle negative shifts
            }

            chars[i] = (char) (
                    (chars[i] - 'a' + shift) % 26 + 'a'
            );
        }

        return new String(chars);
    }
}
