

import java.util.*;

Problem Statement

Given an array price[], for each day find how many consecutive days before it (including today) had price ≤ today’s price.

Example
price = [100, 80, 60, 70, 60, 75, 85]


Output:

span = [1, 1, 1, 2, 1, 4, 6]
  
class StockSpan {
    public static int[] stockSpan(int[] price) {
        int n = price.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // Pop while current price is higher
            while (!stack.isEmpty() && price[stack.peek()] <= price[i]) {
                stack.pop();
            }

            // If empty, no greater on left
            span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();

            // Push current index
            stack.push(i);
        }

        return span;
    }

    public static void main(String[] args) {
        int[] price = {100, 80, 60, 70, 60, 75, 85};
        System.out.println(Arrays.toString(stockSpan(price)));
    }
}
