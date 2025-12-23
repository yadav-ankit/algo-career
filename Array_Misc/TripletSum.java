import java.util.Arrays;

🧠 Idea

Sort the array

Fix one element i

Use two pointers (left, right) to find the remaining two numbers

⏱️ Complexity

Time: O(n²)

Space: O(1) 
  
arr = [1, 4, 45, 6, 10, 8]
k = 22

  
public class TripletSum {

import java.util.*;

class Solution {
    public static boolean tripletSum(int[] arr, int k) {
        int n = arr.length;

        for (int i = 0; i < n - 2; i++) {
            Set<Integer> set = new HashSet<>();
            int target = k - arr[i];

            for (int j = i + 1; j < n; j++) {
                if (set.contains(target - arr[j])) {
                    return true;
                }
                set.add(arr[j]);
            }
        }
        return false;
    }
}


    public static void main(String[] args) {
        int[] arr = {1, 4, 45, 6, 10, 8};
        int k = 22;

        System.out.println(findTriplet(arr, k)); // true
    }
}
