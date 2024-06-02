// https://www.youtube.com/watch?v=Z0R2u6gd3GU&list=PLgUwDviBIf0rENwdL0nEH0uGom9no0nyB&index=15



only 2 steps


  // Transpose the maxtrix
  // make every column to a row starting from left...diognal untouched
  for (int i = 0; i < n - 1; i++) {
    for (int j = i + 1; j < n; j++) {
        swap(mat[i][j], mat[j][i]);
    }
}


// reverse every row
for (int i = 0; i < n; i++) {
    reverse(mat[i].begin(), mat[i].end());
}


private static void reverse(int arr[] , int n){
    int l = 0 , r = n-1;
     while(l<r){
        swap(arr[l],arr[r]);
       l++; r-;
     }
}
