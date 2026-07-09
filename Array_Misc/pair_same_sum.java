https://www.geeksforgeeks.org/dsa/find-a-pair-swapping-which-makes-sum-of-two-arrays-same/

Given two arrays of integers, find a pair of values (one value from each array) that you can swap to give the two arrays the same sum.

Examples:  

Input: A[] = {4, 1, 2, 1, 1, 2}, B[] = (3, 6, 3, 3) 
Output: 1 3 
Explanation: Sum of elements in A[] = 11 and Sum of elements in B[] = 15. To get same sum from both arrays, we can swap 1 from A[] with 3 from B[].

Input: A[] = {5, 7, 4, 6}, B[] = {1, 2, 3, 8} 
Output: 6 2
Explanation: Sum of elements in A[] = 22 and Sum of elements in B[] = 14. To get same sum from both arrays, we can swap 6 from A[] and 2 from B[].

  Suppose the sum of array A[] is sumA and sum of array B[] us sumB, then we need to find a value in A[], say X and a value in B[], say Y, such that: 

sumA - X + Y = sumB - Y + X
2X - 2Y  = sumA - sumB
X - Y  = (sumA - sumB) / 2



   we need to find an element in A[], say X and an element in B[], say Y such that X - Y = (SumA - SumB)/2, 


In order to find such a pair, we can use a hash set to store all the values of array A[]. 
  Then, we can iterate on array B[], and for each value in B[] check if ((sumA - sumB)/2 + Y) is present in the hash set or not. 
  
  If it is present, then print the current element as Y and the element present in the hashset as X.
  
import java.util.*;

class GFG {
    // Function to find the values to swap
    public static void findSwapValues(int[] A, int n,
                                      int[] B, int m)
    {
        // Find the sum of both the arrays
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();

        // Check if the difference between the sum of both
        // the arrays is even or not
        if ((sumA - sumB) % 2 != 0) {
            System.out.println("No Possible Pair exists");
            return;
        }

        // Set to store all the elements of A
        Set<Integer> possibleX = new HashSet<>();
        for (int i = 0; i < n; i++) {
            possibleX.add(A[i]);
        }

        // Iterate over all the elements of B and check if
        // an element with the value = X is present in A or
        // not
        for (int i = 0; i < m; i++) {
            int X = (sumA - sumB) / 2 + B[i];
            if (possibleX.contains(X)) {
                System.out.println(X + " " + B[i]);
                return;
            }
        }

        System.out.println("No Possible Pair exists");
    }

    public static void main(String[] args)
    {
        // Sample Input
        int[] A = { 4, 1, 2, 1, 1, 2 };
        int n = A.length;

        int[] B = { 3, 6, 3, 3 };
        int m = B.length;

        // Function call to print a valid pair
        findSwapValues(A, n, B, m);
    }
}
