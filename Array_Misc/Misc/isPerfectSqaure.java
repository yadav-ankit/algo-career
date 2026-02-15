Given a positive integer num, write a function which returns True if num is a
perfect square else False.
Note: Do not use any built-in library function such as sqrt.
Example 1:
Input: 16
Output: true

  
class Solution {
    public boolean isPerfectSquare(int num) {

        if (num < 2) return true;

        long left = 1, right = num;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long sq = mid * mid;

            if (sq == num) return true;
            else if (sq < num) left = mid + 1;
            else right = mid - 1;
        }

        return false;
    }
}
