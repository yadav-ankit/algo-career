https://leetcode.com/problems/ones-and-zeroes/description/

“I have budget of zeros and ones…
Which strings can I afford to pick to maximize count?”

Every string:

costs (zeros, ones)
gives +1

👉 That’s literally knapsack.

⏱ Complexity
Time: O(len(strs) × m × n)
Space: O(m × n)

  
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String s : strs) {
            int zeros = 0, ones = 0;

            for (char c : s.toCharArray()) {
                if (c == '0') zeros++;
                else ones++;
            }

            // reverse iteration (IMPORTANT)
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(
                        dp[i][j],
                        1 + dp[i - zeros][j - ones]
                    );
                }
            }
        }

        return dp[m][n];
    }
}


Why do we loop backwards?

Because each string can be used at most once (0/1 knapsack).

If you loop forward, you accidentally allow the same string to be reused in the same iteration.


  Now the REAL question: why backward?

Let’s simulate 👇

🔥 Example
m = 3, n = 3
String = "01" → zeros = 1, ones = 1

Initial dp:

dp all = 0
❌ Forward Loop (WRONG)
for (i = 1 → 3)
for (j = 1 → 3)
Step-by-step
i=1, j=1
dp[1][1] = 1
i=2, j=2
Now you do:
dp[2][2] = 1 + dp[1][1]
         = 1 + 1 = 2   ❌

👉 But we only have ONE string 😵

💥 You reused it!

✅ Backward Loop (CORRECT)
for (i = 3 → 1)
for (j = 3 → 1)
Step-by-step
i=3, j=3
dp[3][3] = 1 + dp[2][2] = 1
i=2, j=2
dp[2][2] = 1 + dp[1][1] = 1
i=1, j=1
dp[1][1] = 1

👉 All values are 1 ✅ (correct)

🧠 Key Intuition (This will click)

When you go:

❌ Forward

You use:

dp[i - zeros][j - ones]

👉 which may have been just updated in this iteration

✅ Backward

You use:

dp[i - zeros][j - ones]

👉 which is still from previous iteration (old state)

🎯 One-line understanding

Backward loop ensures current string doesn't see its own updates.

🔥 Super Simple Analogy

Think of dp as a table.

Forward loop → you're writing and reading from same updated row → cheating
Backward loop → you're reading old values → safe


  Ask:

Condition	Loop
Use item once ---->	backward
Use item multiple times	 ---> forward
