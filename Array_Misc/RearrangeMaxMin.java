import java.util.Arrays;

✅ Step-by-Step Strategy

Sort the array

Use two pointers:

maxIdx = n - 1

minIdx = 0

Choose:

M = arr[n - 1] + 1


For each index:

Even index → take max

Odd index → take min

Decode the final values
  
public class RearrangeMaxMin {

    static void rearrange(int[] arr) {
        int n = arr.length;

        Arrays.sort(arr);

        int maxIdx = n - 1;
        int minIdx = 0;
        int M = arr[n - 1] + 1; // greater than max element

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                // place max element
                arr[i] = arr[i] + (arr[maxIdx] % M) * M;
                maxIdx--;
            } else {
                // place min element
                arr[i] = arr[i] + (arr[minIdx] % M) * M;
                minIdx++;
            }
        }

        // Decode final values
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] / M;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        rearrange(arr);
        System.out.println(Arrays.toString(arr));
    }
}
