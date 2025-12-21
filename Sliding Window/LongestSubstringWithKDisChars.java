// https://www.geeksforgeeks.org/dsa/find-the-longest-substring-with-k-unique-characters-in-a-given-string/#expected-approach-sliding-window-with-array-on-time-and-o1-space


public int longestKSubstr(String s, int k) {
    Map<Character, Integer> map = new HashMap<>();
    int i = 0, max = 0;

    for (int j = 0; j < s.length(); j++) {
        map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);

        while (map.size() > k) {
            map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
            if (map.get(s.charAt(i)) == 0) {
                map.remove(s.charAt(i));
            }
            i++;
        }

        if (map.size() == k) {
            max = Math.max(max, j - i + 1);
        }
    }

    return max;
}
