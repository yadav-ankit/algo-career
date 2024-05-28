https://leetcode.com/problems/subarray-product-less-than-k 


public int numSubarrayProductLessThanK(int[] arr, int k) {   
   int count = 0; 
    int product =1; 
    int i = 0; 

    for (int j = 0; j < arr.length; j++) { 
        product *= arr[j]; 
        while (product >= k) { 
            product /= arr[i]; 
            i++; 
        } 
        count += j - i + 1; 
    } 
    return count; 
} 
