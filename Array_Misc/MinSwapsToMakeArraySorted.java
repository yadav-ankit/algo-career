import java.util.*;

✅ Problem (unchanged, crystal clear)

All elements are distinct

You cannot modify values

You can only swap

Target = strictly increasing → sorted array

Find minimum number of swaps

class Solution {

    public int minSwaps(int[] nums) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        // value -> index
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(nums[i], i);
        }

        int swaps = 0;

        for (int i = 0; i < n; i++) {

            // already correct
            if (nums[i] == sorted[i]) {
                continue;
            }

            swaps++;

            // index where the correct value currently is
            int correctIndex = indexMap.get(sorted[i]);

            // update map BEFORE swap
            indexMap.put(nums[i], correctIndex);
            indexMap.put(sorted[i], i);

            // swap in array
            int temp = nums[i];
            nums[i] = nums[correctIndex];
            nums[correctIndex] = temp;
        }

        return swaps;
    }
}

🧠 Map-based idea (very intuitive)

Create a sorted copy of the array

Create a map: value → current index

Traverse array from left to right

If element is not where it should be:

swap it with the element that should be here

update indices in the map

increment swap count

✨ Example
arr = [4, 3, 2, 1]
sorted = [1, 2, 3, 4]

i = 0 → need 1, have 4
swap(0, 3) → [1, 3, 2, 4]

i = 1 → need 2, have 3
swap(1, 2) → [1, 2, 3, 4]

answer = 2
