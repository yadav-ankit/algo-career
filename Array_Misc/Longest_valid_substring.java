Given a string s consisting of opening and closing parenthesis '(' and ')', find the length of the longest valid parenthesis substring.
A valid parenthesis substring is one where every opening bracket '(' has a corresponding closing bracket ')' in the correct order.

Examples: 

Input: s = "())"
Output: 2
Explanation: The longest valid parentheses substring is "()".

Input: s = "(()())"
Output: 6
Explanation: The entire string "(()())" is a valid parentheses substring of length 6.


import java.util.Stack;

O(n) and O(n)

class GfG {
    
    static int maxLength(String s) {
        Stack<Integer> st = new Stack<>();

        // Push -1 as the initial index to
      	// handle the edge case
        st.push(-1);
        int maxLen = 0;

        // Traverse the string
        for (int i = 0; i < s.length(); i++) {

            // If we encounter an opening parenthesis, 
          	// push its index
            if (s.charAt(i) == '(') {
                st.push(i);
            } 
            else {

                // If we encounter a closing parenthesis, 
              	// pop the stack
                st.pop();

                // If stack is empty, push the current index 
                // as a base for the next valid substring
                if (st.isEmpty()) {
                    st.push(i);
                } else {

                    // Update maxLength with the current length 
                    // of the valid parentheses substring
                    maxLen = Math.max(maxLen, i - st.peek());
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        String s = "(()())";
        System.out.println(maxLength(s));
    }
}

---------------------


O(n) and O(1)
class GfG {
    static int maxLength(String s) {
        int maxLen = 0;
        
        // Left to Right Traversal
        int open = 0, close = 0;
        for (int i=0; i<s.length(); i++) {
            char ch=s.charAt(i);
            if (ch == '(') {
                open++;
            } else if (ch == ')') {
                close++;
            }

            if (open == close) {
                maxLen = Math.max(maxLen, 2 * close);
            } else if (close > open) {
                open = close = 0;
            }
        }

        // Right to Left Traversal
        open = close = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                open++;
            } else if (s.charAt(i) == ')') {
                close++;
            }

            if (open == close) {
                maxLen = Math.max(maxLen, 2 * open);
            } else if (open > close) {
                open = close = 0;
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        String s = "(()())";
        System.out.println(maxLength(s));
    }
}
