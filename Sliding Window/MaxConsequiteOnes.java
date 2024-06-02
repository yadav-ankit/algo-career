Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0. 

Example 1: 
 

Input: [1,0,1,1,0] 
Output: 4 

public int findMaxConsecutiveOnes(int[] arr) { 
    int maxCount = 0; 
    int zeroCount = 0; 
    int i = 0; 

    for (int j = 0; j < arr.length; j++) { 
        if (arr[j] == 0) { 
            zeroCount++; 
        } 

        while (zeroCount > 1) { 
            if (arr[i] == 0) { 
                zeroCount--; 
            } 
            i++; 
        } 
        maxCount = Math.max(maxCount, j - i + 1); 
    } 
    return maxCount; 
} 
