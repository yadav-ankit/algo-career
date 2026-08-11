Time
   
logn < n < nlogn < n^2 <n^3 < 2^n < 3^n <n! <n^n


   ----------

   for sliding window always take r - l + 1;  it gives number of elements b/w l,r (both included) provided array elements are starting from index 0;

   for 1 based arrays it changes r-l;


 if u r given in constraint then only lowercase alphabets then u can take

    int map[] = new map[26];

   map[s.charAt(i) - 'a']

      -----------------
   
define 2d array  
   
   int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

   for (int[] d : dirs) {
      int nr = r + d[0], nc = c + d[1];
   }
   
suppose u want to store pair of elements in array / queue
    Queue<int[]> q = new LinkedList<>();

int[] cell = q.poll();
int x = cell[0];
int y = cell[1];
q.add(new int[]{nx, ny});

----------------------------------
Array.fill (0 , arr_name); 

 // Map to store 62 possible characters
 char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();


Supose u r parsing a string and for each character u want to check if its > A 
    if ('A' <= shortURL.charAt(i))

   int MOD = 1_000_000_007;

🔑 Pattern you should remember

If you see:

“minimum number of groups / subsequences”

constraint like max − min ≤ k

reordering or subsequences allowed

👉 Sort first, then greedily extend until condition breaks
 
-----------------


   private boolean isPalindrome(String s,int l,int r) {
        while (l < r) {
            if (s.charAt(l)!= s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }


-----------------
Integer arr[] = {10, 20, 20, 30, 20, 40, 50}; 

List<Integer> list = Arrays.asList(arr);
int freq = Collections.frequency(Arrays.asList(arr), 20); 

Collections.rotate(mylist, 2);  // move everything 2 places to LEFT.   , put -2 for right.


String[] words
// 1. Sort by word length
Arrays.sort(words, Comparator.comparingInt(String::length));


Map<Character, Integer> map = new HashMap<>(); 

 char c = s.charAt(index); 

map.put(c, map.getOrDefault(c, 0) + 1); 

  

int[][] intervals 

Arrays.sort(intervals, (a, b) -> a[0] - b[0]); 

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



 Given a list of pairs ..sort it by sum of first ele + second ele 

List<Pair> pairs = new ArrayList<>();  
pairs.add(new Pair(1, 2)); 
 pairs.add(new Pair(8, 2));  
pairs.add(new Pair(4, 1));  
pairs.add(new Pair(3, 9));  
pairs.sort(Comparator.comparingInt(pair -> pair.first + pair.second));  
-------------------- 

Sort it by first ele 
pairs.sort(Comparator.comparingInt(pair -> pair.first));  


Generic 

 

pairs.sort(new Comparator<Pair>() {  
@Override  

public int compare(Pair p1, Pair p2)  {  
     int sum1 = p1.first + p1.second;  
     int sum2 = p2.first + p2.second;  
   return Integer.compare(sum1, sum2);  
} }); 



// anonymous class style
Comparator<Map.Entry<String, Integer>> byValue = new Comparator<Map.Entry<String, Integer>>() {
    @Override
    public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
        return a.getValue() - b.getValue();  // ascending
    }
};
