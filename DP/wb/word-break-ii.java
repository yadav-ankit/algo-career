https://leetcode.com/problems/word-break-ii/description/


class Solution {

    Map<Integer, List<String>> memo = new HashMap<>();
    Set<String> dict;

    public List<String> wordBreak(String s, List<String> wordDict) {

        dict = new HashSet<>(wordDict);

        return solve(0, s);
    }

    private List<String> solve(int idx, String s) {

        if (memo.containsKey(idx)) {
            return memo.get(idx);
        }

        List<String> ans = new ArrayList<>();

        if (idx == s.length()) {

            ans.add("");
            return ans;
        }

        for (int j = idx; j < s.length(); j++) {

            String word = s.substring(idx, j + 1);

            if (dict.contains(word)) {

                List<String> suffixes = solve(j + 1, s);

                for (String suffix : suffixes) {

                    if (suffix.isEmpty()) {
                        ans.add(word); // last word h kya ..to dont add space
                    } else {
                        ans.add(word + " " + suffix);
                    }
                }
            }
        }

        memo.put(idx, ans);

        return ans;
    }
}
