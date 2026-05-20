https://leetcode.com/problems/largest-rectangle-in-histogram/


class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int curr = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && curr < heights[stack.peek()]) {
                int height = heights[stack.pop()];

                int right = i;
                int left = stack.isEmpty() ? -1 : stack.peek();

                int width = right - left - 1;

                max = Math.max(max, height * width);
            }

            stack.push(i);
        }

        return max;
    }
}


why does poping means we have to find area now ?

This is the exact insight behind Largest Rectangle in Histogram. If this clicks, the problem becomes easy.

Take:

heights = [2,1,5,6,2,3]

Suppose stack currently:

indices:
[1,2,3]

heights:
[1,5,6]

Meaning:

1 < 5 < 6

We haven't popped 5 or 6 because we still don't know how far they can extend to the right.

Think of bar 6:

      6
      |
1  5  6

Maybe future heights:

[1,5,6,8,9]

Then rectangle with height 6 could continue.

We wait.

Now current element:

curr = 2

Array:

[2,1,5,6,2,3]
         ↑
       current

Compare:

2 < 6

Boom.

This means:

height 6
cannot extend beyond index 4

because at index 4:

height = 2

which is smaller than 6.

So we finally know:

For bar 6:

right boundary = current index (4)
left boundary = previous smaller in stack (2)
width known
area computable

-----------------------------------------------------------------------------
  
Ques -> why -1 in 
Think of it as:

(smaller) [valid valid valid] (smaller)
          ^-----------------^
                 width

We want only the bars inside the boundaries.

Hence:

width=rightBoundary−leftBoundary−1

The -1 means:

"Remove the two boundaries and count only what's in between."
