import java.util.*;


Given a string, we can "shift" each of its letter to its successive letter, for
example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all
strings that belong to the same shifting sequence.
Example:
Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output:
[
["abc","bcd","xyz"],
["az","ba"],
["acef"],
["a","z"]
]

🧠 Key Insight

Two strings belong to the same shifting sequence if:

The difference between consecutive characters is the same (cyclically).

Example:

"abc"
b - a = 1
c - b = 1
Pattern = [1,1]

"bcd"
c - b = 1
d - c = 1
Pattern = [1,1]


They match ✅

⚠ Important: Wrap Around

For:

"az"
z - a = 25


But "ba":

a - b = -1 → +26 = 25


So we normalize:

diff = (curr - prev + 26) % 26

🚀 Strategy

For each string:

Build a "pattern key"

Example:

"abc" → "1#1#"
"bcd" → "1#1#"


Use HashMap:

key → list of strings


Return grouped values


⏱ Complexity

Time: O(n * k)
(n strings, each length k)

Space: O(nk)


class Solution {
    public List<List<String>> groupStrings(String[] strings) {

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strings) {

            String key = getKey(s);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }

    private String getKey(String s) {

        if (s.length() == 1) return "single";

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < s.length(); i++) {
            int diff = (s.charAt(i) - s.charAt(i - 1) + 26) % 26;
            sb.append(diff).append("#");
        }

        return sb.toString();
    }
}
