
https://leetcode.com/problems/remove-k-digits/description/

Complexity	Value
Time	O(N)
Space	O(N)
  
class Solution {
    public String removeKdigits(String num, int k) {

        Stack<Character> stack = new Stack<>();

        for(char digit : num.toCharArray()) {

            while(!stack.isEmpty() && k > 0 && stack.peek() > digit){
                stack.pop();
                k--;
            }

            stack.push(digit);
        }

        while(k > 0){
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();

        for(char c : stack)
            sb.append(c);

        while( sb.length() > 0 && sb.charAt(0) == '0'){
            sb.deleteCharAt(0);
        }

        return sb.length() == 0?  "0" : sb.toString();
    }
}


To make a number smaller:

Remove larger digits appearing before smaller digits.

Example:

143...

4 > 3

Keeping:

143...

is worse than:

13...

So remove 4.

This greedy choice is optimal.

Why monotonic increasing stack?

Maintain digits:

1 4

Current:

3

Since:

4 > 3

Pop:

1

Push:

1 3

Stack remains increasing:

1 <= 3

Let's dry run:

num="1432219"
k=3

Initial:

stack=[]
k=3
digit = 1

Push:

stack=[1]
digit = 4
4 > 1

Push:

stack=[1,4]
digit = 3

Current:

3 < 4

Pop:

remove 4

stack=[1]

k=2

Push:

stack=[1,3]
digit = 2

Current:

2 < 3

Pop:

remove 3

stack=[1]

k=1

Push:

stack=[1,2]
digit = 2

Equal:

Push:

stack=[1,2,2]
digit = 1

Current:

1 < 2

Pop:

remove 2

k=0

Push:

stack=[1,2,1]

Remaining:

stack=[1,2,1,9]

Result:

1219

Done.
