// https://www.youtube.com/watch?v=zmc_FoPw_WQ

https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii/

You are given a string s of length m consisting of digits. You are also given a 2D integer array queries, where queries[i] = [li, ri].

For each queries[i], extract the substring s[li..ri]. Then, perform the following:

Form a new integer x by concatenating all the non-zero digits from the substring in their original order. If there are no non-zero digits, x = 0.
Let sum be the sum of digits in x. The answer is x * sum.
Return an array of integers answer where answer[i] is the answer to the ith query.

Since the answers may be very large, return them modulo 109 + 7.

 

Example 1:

Input: s = "10203004", queries = [[0,7],[1,3],[4,6]]

Output: [12340, 4, 9]
  

Concatenate Non-Zero Digits and Multiply by Sum II  | Leetcode 3756 


  To solve this in 
 time, the creator implements four primary data structures 

Digit Sum Up To i: A prefix array capturing the cumulative sum of digits up to index i 
  
Number Up To i: An array that stores the concatenated number formed by non-zero digits up to index i 

Non-Zero Digit Count: An array storing the count of non-zero digits up to index i 

Powers of 10: A precomputed array to facilitate constant-time access to 


How it Works (The Math)
Sum Calculation: Uses the prefix sum technique: Sum(L, R) = PrefixSum[R] - PrefixSum[L-1] (0:08:11).
Finding X: Uses a logic similar to rolling hashes. To isolate the range [L, R], 
  the creator takes the number up to R and subtracts the number formed up to L-1, shifted by 
  the number of non-zero digits within the target range (0:24:45 - 0:26:17).

  
import java.util.*;


class Solution {
    static final long MOD = 1_000_000_007L;

    public int[] findProductOfNonZeroDigits(String s, int[][] queries) {
        int n = s.length();

        // 1. Prefix digit sum (sum of non-zero digits up to index i)
        long[] digitSum = new long[n + 1];

        // 2. Number formed by concatenating non-zero digits up to index i
        long[] numUpTo = new long[n + 1];

        // 3. Count of non-zero digits up to index i
        int[] nonZeroCount = new int[n + 1];

        // 4. Powers of 10 (precomputed mod)
        long[] pow10 = new long[n + 1];
        pow10[0] = 1L;
        for (int i = 1; i <= n; i++) {
            pow10[i] = pow10[i - 1] * 10 % MOD;
        }

        // Build prefix arrays
        for (int i = 1; i <= n; i++) {
            int d = s.charAt(i - 1) - '0';
            digitSum[i] = digitSum[i - 1];
            numUpTo[i] = numUpTo[i - 1];
            nonZeroCount[i] = nonZeroCount[i - 1];

            if (d != 0) {
                digitSum[i] = (digitSum[i - 1] + d) % MOD;
                // Append digit d to the end: shift existing number left by 1 decimal place
                numUpTo[i] = (numUpTo[i - 1] * 10 + d) % MOD;
                nonZeroCount[i] = nonZeroCount[i - 1] + 1;
            }
        }

        // Answer queries
        int[] result = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int L = queries[q][0] + 1; // convert to 1-indexed
            int R = queries[q][1] + 1;

            // Sum of non-zero digits in [L, R]
            long sum = (digitSum[R] - digitSum[L - 1] + MOD) % MOD;

            // Number of non-zero digits strictly in [L, R]
            int k = nonZeroCount[R] - nonZeroCount[L - 1];

            // X = numUpTo[R] - numUpTo[L-1] * 10^k
            // (remove the prefix contribution by shifting it out)
            long X = (numUpTo[R] - numUpTo[L - 1] * pow10[k] % MOD + MOD) % MOD;

            result[q] = (int) (X * sum % MOD);
        }

        return result;
    }
}
