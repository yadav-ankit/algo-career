🧪 Example Walkthrough
Number: 534976
Digits: [5,3,4,9,7,6]

Step 1: Pivot at 4 (index 2)
        because 4 < 9

Step 2: Successor = 6

Swap → [5,3,6,9,7,4]

Reverse suffix → [5,3,6,4,7,9]

Answer = 536479

  

public class NextGreaterNumber {

    public static long nextGreaterElement(long n) {
        char[] digits = String.valueOf(n).toCharArray();
        int len = digits.length;

        // Step 1: Find pivot
        int i = len - 2;
        while (i >= 0 && digits[i] >= digits[i + 1]) {
            i--;
        }

        // If no pivot found, no greater permutation
        if (i < 0) {
            return -1;
        }

        // Step 2: Find successor
        int j = len - 1;
        while (digits[j] <= digits[i]) {
            j--;
        }

        // Step 3: Swap
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;

        // Step 4: Reverse suffix
        reverse(digits, i + 1, len - 1);

        return Long.parseLong(new String(digits));
    }

    private static void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // Test
    public static void main(String[] args) {
        System.out.println(nextGreaterElement(534976)); // 536479
        System.out.println(nextGreaterElement(1234));   // 1243
        System.out.println(nextGreaterElement(4321));   // -1
    }
}
