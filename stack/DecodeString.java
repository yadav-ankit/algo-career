https://leetcode.com/problems/decode-string/description/

Input
"3[a2[c]]"
 "accaccacc"
  
class Solution {
    public String decodeString(String s) {
        Deque<StringBuilder> strStack = new ArrayDeque<>();
        Deque<Integer> numStack = new ArrayDeque<>();

        StringBuilder curr = new StringBuilder();
        int num = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            else if (ch == '[') {
                strStack.push(curr);
                numStack.push(num);
                curr = new StringBuilder();
                num = 0;
            }
            else if (ch == ']') {
                StringBuilder prev = strStack.pop();
                int repeat = numStack.pop();
                prev.append(curr.toString().repeat(repeat));
                curr = prev;
            }
            else {
                curr.append(ch);
            }
        }
        return curr.toString();
    }
}


🪜 Algorithm (step-by-step)

We keep:

currentString → what we’re building now

currentNumber → repetition count

stack → stores (previousString, repeatCount)

Process characters one by one:

Digit → build the number

[ → push (currentString, currentNumber) to stack
→ reset both

Letter → append to currentString

] → pop from stack and expand

🧪 Example Walkthrough
Input
"3[a2[c]]"

Step-by-step
Char	Action	Stack	currentString
3	number	[]	""
[	push	[( "", 3 )]	""
a	append		"a"
2	number		"a"
[	push	[( "",3 ), ( "a",2 )]	""
c	append		"c"
]	pop + repeat	[( "",3 )]	"acc"
]	pop + repeat	[]	"accaccacc"

✅ Final output:

"accaccacc"
