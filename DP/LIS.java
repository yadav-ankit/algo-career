import java.util.*;

public class PrintLIS {

    static void printLIS(int[] arr) {
        int n = arr.length;

        int[] dp = new int[n];
        int[] parent = new int[n];

        // Step 1: initialization
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            parent[i] = i;
        }

        // Step 2: DP
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }
        }

        // Step 3: find max LIS index
        int maxLen = 0;
        int lastIndex = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                lastIndex = i;
            }
        }

        // Step 4: backtrack LIS
        List<Integer> lis = new ArrayList<>();
        while (parent[lastIndex] != lastIndex) {
            lis.add(arr[lastIndex]);
            lastIndex = parent[lastIndex];
        }
        lis.add(arr[lastIndex]);

        // reverse to correct order
        Collections.reverse(lis);

        // Print result
        System.out.println("Length of LIS: " + maxLen);
        System.out.println("LIS: " + lis);
    }

    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        printLIS(arr);
    }
}

Input
[10, 9, 2, 5, 3, 7, 101, 18]

dp[]
[1, 1, 1, 2, 2, 3, 4, 4]

parent[]
[0, 1, 2, 2, 2, 4, 5, 5]

Backtracking
101 → 7 → 3 → 2


Reversed:

[2, 3, 7, 101]


✔️ Correct LIS printed.


  Time	O(n²)
Space	O(n)
