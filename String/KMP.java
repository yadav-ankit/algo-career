

import java.util.*;

How LPS is Built (Important Interview Question)
Pattern:
A B A B C A B A B
0 1 2 3 4 5 6 7 8
LPS:
0 0 1 2 0 1 2 3 4
For index 8 (ABABCABAB):
Prefixes: A, AB, ABA, ABAB
Suffixes: B, AB, BAB, ABAB
Longest common = ABAB
So:
lps[8] = 4;


public class KMP {

    private static int[] buildLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];

        int len = 0;
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i] = ++len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    public static List<Integer> search(String text, String pattern) {
        List<Integer> result = new ArrayList<>();

        int n = text.length();
        int m = pattern.length();

        int[] lps = buildLPS(pattern);

        int i = 0; // text pointer
        int j = 0; // pattern pointer

        while (i < n) {

            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == m) {
                result.add(i - j); // result will add starting index'es of all matching pattern...if we want the first index just return (i-j);
                j = lps[j - 1];
            }
            else if (i < n && text.charAt(i) != pattern.charAt(j)) {

                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        System.out.println(search(text, pattern));
    }
}
