
https://www.geeksforgeeks.org/dsa/simplify-directory-path-unix-like/

class Solution {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();

        for (String part : path.split("/")) {
            if (part.equals("") || part.equals(".")) {
                continue;
            } else if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } else {
                stack.addLast(part);
            }
        }

        return "/" + String.join("/", stack);
    }
}


Input
/a/./b/../../c/

Step 1️⃣ Split by /
path.split("/")


Tokens:

["", "a", ".", "b", "..", "..", "c", ""]

Step 2️⃣ Process each token

We’ll use a Deque (addLast / removeLast).

Token	Meaning	Action	Stack
""	empty	ignore	[]
"a"	directory	push	[a]
"."	current dir	ignore	[a]
"b"	directory	push	[a, b]
".."	parent dir	pop	[a]
".."	parent dir	pop	[]
"c"	directory	push	[c]
""	empty	ignore	[c]
Step 3️⃣ Build final path

Stack:

[c]


Final path:

/c
