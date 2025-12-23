import java.util.Arrays;

🧠 Idea

Sort the array

Fix one element i

Use two pointers (left, right) to find the remaining two numbers

⏱️ Complexity

Time: O(n²)

Space: O(1) (excluding sorting)

💡 Step-by-Step Example
arr = [1, 4, 45, 6, 10, 8]
k = 22


Sorted array:

[1, 4, 6, 8, 10, 45]


Fix 1 → need 21
10 + 8 = 18 ❌
6 + 10 = 16 ❌
8 + 10 = 18 ❌

Fix 4 → need 18
8 + 10 = 18 ✅ 🎯
  
public class TripletSum {

    static boolean findTriplet(int[] arr, int k) {
        int n = arr.length;
        Arrays.sort(arr);

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (sum == k)
                    return true;
                else if (sum < k)
                    left++;
                else
                    right--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 45, 6, 10, 8};
        int k = 22;

        System.out.println(findTriplet(arr, k)); // true
    }
}
