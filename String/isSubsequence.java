/*
  t is the longer string & we are checking if s is a subsequence or not. 
  https://www.geeksforgeeks.org/dsa/find-largest-word-dictionary-deleting-characters-given-string/#expected-approach-using-nested-loop-on2-time-and-o1-space
  
*/

public boolean isSubsequence(String s, String t) {
    int i = 0, j = 0;

    while (i < s.length() && j < t.length()) {
        if (s.charAt(i) == t.charAt(j)) {
            i++;
        }
        j++;
    }

    return i == s.length();
}
