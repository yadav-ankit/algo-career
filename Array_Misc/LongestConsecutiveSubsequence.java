import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSubsequence {

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;

        for (int num : set) {
            // Only start counting if num is the beginning
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                longest = Math.max(longest, currentStreak);
            }
        }
        return longest;
    }

    // Driver code
    public static void main(String[] args) {
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        int[] nums2 = {0,3,7,2,5,8,4,6,0,1};

        System.out.println(longestConsecutive(nums1)); // 4
        System.out.println(longestConsecutive(nums2)); // 9
    }
}

⏱️ Complexity

Time: O(n)
(each number processed at most once)

Space: O(n) (HashSet)
