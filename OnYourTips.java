Array.fill (0 , arr_name); 


Integer arr[] = {10, 20, 20, 30, 20, 40, 50}; 

List<Integer> list = Arrays.asList(arr);
int freq = Collections.frequency(Arrays.asList(arr), 20); 

Collections.rotate(mylist, 2);  // move everything 2 places to LEFT.   , put -2 for right.


// reverse
private static void reverse(int arr[] , int n){
    int l = 0 , r = n-1;
     while(l<r){
        swap(arr[l],arr[r]);
       l++; r-;
     }
}

// Take ciel
int ceil = len / 2 + (len%2);
int floor = len / 2 - (len%2);


int mid = l + (r-l)/2;


Search in a 2D Matrix - I | Binary Search of 2D 
// https://www.youtube.com/watch?v=JXU4Akft7yk
1 D array index --> can be converted to a 2 d index as  (say we have a mat[m][n) 
Row = index/ m ; 
Col = index % m ;

--------------------------------------------------------

// Rotate an array to right by d places
/*
Reverse the entire array: This will bring the last 𝑘 elements to the front, but in reverse order.
Reverse the first 𝑘 elements: This will put the first 𝑘 elements in the correct order.
Reverse the remaining elements: This will sort the rest of the elements, completing the rotation.

*/

 int n = arr.length;
        k = k % n; // In case k is greater than array length

// Step 1: Reverse the entire array
 reverse(arr, 0, n - 1);

// Step 2: Reverse the first k elements
reverse(arr, 0, k - 1);

// Step 3: Reverse the remaining elements
reverse(arr, k, n - 1);


------------------------------

Grid uniqe path to reach bottom right

    dp[i[j] = dp[i-1][j] + dp[i][j-1] + // if diogonal is allowed dp[i-1][j-1];
------------------------


kadanes algo  largest-sum-contiguous-subarray (for a -ve +ve array)

int max = Integer.MIN_VALUE, sum= 0;

        for (int i = 0; i < size; i++) {
            sum = sum + a[rri];
            if (sum > max)
                max = sum;
            
            if (sum < 0)
                sum = 0;
        }
        return max;
     }

-------------
// reverse of above ...min sum contiguous subarray
    
    smallestSumSubarr(arr, n)
    Initialize sum = INT_MAX
    Initialize min = INT_MAX
    
    for i = 0 to n-1
        if sum > 0
            sum = arr[i]        
        else
            sum += arr[i]
        min = min(min, sum)
    return min
