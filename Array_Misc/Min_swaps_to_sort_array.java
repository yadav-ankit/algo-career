// Java program to find the minimum no. of swaps required to  
// sort an array by swapping elements to correct positions

// https://www.geeksforgeeks.org/dsa/minimum-number-swaps-required-sort-array/


import java.util.*;

class GfG {
    static int minSwaps(int[] arr) {
  
        // Temporary array to store elements in sorted order
        int[] temp = arr.clone();
        Arrays.sort(temp);
		
      	// Hashing elements with their correct positions
      	HashMap<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < arr.length; i++)
            pos.put(arr[i], i);
		
      	int swaps = 0;
        for (int i = 0; i < arr.length; i++) {
            if (temp[i] != arr[i]) {

                // Index of the element that should be at index i.
                int ind = pos.get(temp[i]);
              
              	// Swapping element to its correct position
                int tempValue = arr[i];
                arr[i] = arr[ind];
                arr[ind] = tempValue;

                // Update the indices in the hashmap
                pos.put(arr[i], i);
                pos.put(arr[ind], ind);

                swaps++;
            }
        }
        return swaps;
    }

    public static void main(String[] args) {
        int[] arr = {10, 19, 6, 3, 5};
        System.out.println(minSwaps(arr));
    }
}
