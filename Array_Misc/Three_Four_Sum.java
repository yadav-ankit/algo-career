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





    static boolean hasTripletSum(int[] arr, int target) {
        int n = arr.length;
        Arrays.sort(arr);
        
        // Fix the first element as arr[i]
        for (int i = 0; i < n - 2; i++) {
            
            // Initialize left and right pointers with 
            // start and end of remaining subarray
            int l = i + 1, r = n - 1;
            
            int requiredSum = target - arr[i];
            while (l < r) {
                if (arr[l] + arr[r] == requiredSum)
                    return true;
                if (arr[l] + arr[r] < requiredSum)
                    l++;
                else if (arr[l] + arr[r] > requiredSum)
                    r--;
            }
        }

        return false;
    }



    static ArrayList<ArrayList<Integer>> fourSum(int[] arr, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int n = arr.length;

        // Sort the array to apply two-pointer approach
        Arrays.sort(arr);

        // Fix the first two elements
        for (int i = 0; i < n; i++) {
            // Skip duplicates for i
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            for (int j = i + 1; j < n; j++) {
                // Skip duplicates for j
                if (j > i + 1 && arr[j] == arr[j - 1]) continue;

                int k = j + 1;
                int l = n - 1;

                // Use two-pointer technique for remaining two elements
                while (k < l) {
                    int sum = arr[i] + arr[j] + arr[k] + arr[l];

                    if (sum == target) {
                        ArrayList<Integer> quad = new ArrayList<>();
                        quad.add(arr[i]);
                        quad.add(arr[j]);
                        quad.add(arr[k]);
                        quad.add(arr[l]);
                        res.add(quad);

                        k++;
                        l--;

                        // Skip duplicates for k
                        while (k < l && arr[k] == arr[k - 1]) k++;

                        // Skip duplicates for l
                        while (k < l && arr[l] == arr[l + 1]) l--;
                    }
                    else if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }

        return res;
    }



