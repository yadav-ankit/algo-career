/*
A sequence {X1, X2, .. Xn} is an alternating sequence if its elements satisfy one of the following relations : 



  X1 < X2 > X3 < X4 > X5 < …. xn or 
  X1 > X2 < X3 > X4 < X5 > …. xn

Input: arr[] = {1, 5, 4}
Output: 3
Explanation: The whole arrays is of the form  x1 < x2 > x3 





Input: arr[] = {10, 22, 9, 33, 49, 50, 31, 60}
Output: 6
Explanation: The subsequences {10, 22, 9, 33, 31, 60} or
{10, 22, 9, 49, 31, 60} or {10, 22, 9, 50, 31, 60}
are longest subsequence of length 6

*/

// Java Program for above approach
public class GFG {

    // Function for finding
    // longest alternating
    // subsequence
    static int LAS(int[] arr, int n)
    {

        // "inc" and "dec" initialized as 1,
        // as single element is still LAS
        int inc = 1;
        int dec = 1;

        // Iterate from second element
        for (int i = 1; i < n; i++) {

            if (arr[i] > arr[i - 1]) {
                // "inc" changes if "dec"
                // changes
                inc = dec + 1;
            }
            else if (arr[i] < arr[i - 1]) {

                // "dec" changes if "inc"
                // changes
                dec = inc + 1;
            }
        }

        // Return the maximum length
        return Math.max(inc, dec);
    }

    // Driver Code
    public static void main(String[] args)
    {
        int[] arr = { 10, 22, 9, 33, 49, 50, 31, 60 };
        int n = arr.length;

        // Function Call
        System.out.println(LAS(arr, n));
    }
}
