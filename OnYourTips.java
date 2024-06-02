Array.fill (0 , arr_name); 

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

