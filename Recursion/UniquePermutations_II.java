https://leetcode.com/problems/permutations-ii/description/

class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // VERY important
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used,
                           List<Integer> path,
                           List<List<Integer>> result) {

        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            // already used in current permutation
            if (used[i]) continue;

            // skip duplicates
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            path.add(nums[i]);

            backtrack(nums, used, path, result);

            // backtrack
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}

⏱ Complexity

Time: O(n × n!) (all permutations)
Space: O(n) recursion + used[]

  
The Skip Condition (MOST IMPORTANT LINE)
if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

What it means in one line:

Only allow duplicates if the previous identical number has already been used in this permutation path.
  

We skip nums[i] when it equals nums[i-1] and nums[i-1] is unused because choosing 
  nums[i] would generate the same permutations as choosing nums[i-1] at the same recursion level.

My understanding...

basically agar hame 1(idx = 2), 2 (idx=3) , 2 (idx = 4) mila recursion me 

i=3
  and i hamara 3 h to koi problem nhi h ...we will generate ....1,2,2... and add in our List<Answer>

i=4 (think here)
  and i ab 4 so we are again seeing value as 2 to agar ham nums[i-1] 2 phir se le lenge to
  ....1,2,2.... dobara add ho jaayega which we dont want.
  to hame skip krna pdega ...kse by leveraging used[] array
  agar used[i-1] = true to koi problem nhi (we will anyway NOT add) ...basics of backtracking
   pr agar used[i-1] = false to hame skip krna pdega
