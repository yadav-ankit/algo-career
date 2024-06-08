// print all subsequence ...sum == k


import java.util.ArrayList;
import java.util.List;

public class SubsetSum {

    // Function to find and print all subsets that sum up to a given value
    public static void printS(int ind, List<Integer> ds, int s, int sum, int[] arr, int n) {
        if (ind == n) {
            if (s == sum) {
                for (int num : ds) {
                    System.out.print(num + " ");
                }
            }
            return;
        }

        // Pick the current element
        ds.add(arr[ind]);
        s += arr[ind];
        printS(ind + 1, ds, s, sum, arr, n);

        // Backtrack by removing the current element
        s -= arr[ind];
        ds.remove(ds.size() - 1);

        // Do not pick the current element
        printS(ind + 1, ds, s, sum, arr, n);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        int n = arr.length;
        int sum = 2;
        List<Integer> ds = new ArrayList<>();
        printS(0, ds, 0, sum, arr, n);
    }
}


-----------------------
// print any one  use T / F    

public class SubsetSum {

    // Function to find and print all subsets that sum up to a given value
    public static boolean printS(int ind, List<Integer> ds, int s, int sum, int[] arr, int n) {
        if (ind == n) {
            if (s == sum) {
                for (int num : ds) {
                    System.out.print(num + " ");
                }
                return true; // Condition satisfied
            }
            return false; // Condition not satisfied
        }

        // Pick the current element
        ds.add(arr[ind]);
        s += arr[ind];
        if (printS(ind + 1, ds, s, sum, arr, n)) {
            return true;
        }

        // Backtrack by removing the current element
        s -= arr[ind];
        ds.remove(ds.size() - 1);

        // Do not pick the current element
        if (printS(ind + 1, ds, s, sum, arr, n)) {
            return true;
        }

        return false; // No valid subset found
    }
}

--------------------


// count all subseqnece whose sum == k



