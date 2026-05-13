https://leetcode.com/problems/maximum-value-of-k-coins-from-piles/description/

class Solution {

    public int maxValueOfCoins(List<List<Integer>> piles, int k) {

        int n = piles.size();

        int[][] dp = new int[n + 1][k + 1];

        // dp[i][coins]
        // maximum value using first i piles
        // taking at most coins total coins

        for (int i = 1; i <= n; i++) {

            List<Integer> pile = piles.get(i - 1);

            for (int coins = 0; coins <= k; coins++) {

                int sum = 0;

                // take currCoins from current pile
                for (int currCoins = 0;currCoins <= Math.min(pile.size(), coins); currCoins++) {

                    if (currCoins > 0) {
                        sum += pile.get(currCoins - 1);
                    }

                    dp[i][coins] = Math.max(
                        dp[i][coins],
                        sum + dp[i - 1][coins - currCoins]
                    );
                }
            }
        }

        return dp[n][k];
    }
}

why Then remaining coins come from previous piles.  sum + dp[i - 1][coins - currCoins]

Because we already decided:

currCoins

coins are taken from current pile.

So remaining coins must come from earlier piles.

Suppose:

coins = 5
currCoins = 2

Meaning:

2 coins taken from current pile

Then we still need:

5 - 2 = 3 coins

Those 3 coins can only come from:

previous piles

Hence:

dp[i - 1][coins - currCoins]
Full Meaning
sum + dp[i - 1][coins - currCoins]

means:

value from current pile
+
best value from previous piles
Example

Suppose:

piles = [[1,100,3], [7,8,9]]

k = 2

At:

i = 2
coins = 2

Current pile:

[7,8,9]
Case 1 → take 0 from current pile
sum = 0

Remaining:

2 coins from previous piles

Value:

0 + dp[1][2]
Case 2 → take 1 from current pile
sum = 7

Remaining:

1 coin from previous piles

Value:

7 + dp[1][1]
Case 3 → take 2 from current pile
sum = 15

Remaining:

0 coins from previous piles

Value:

15 + dp[1][0]

Take maximum of all choices.

--------------------------------------------------------------------

also why do we need a third loop ? isnt first 2 suffices?

No — first 2 loops are NOT enough because for every pile you have multiple choices.

The third loop represents:

```
how many coins to take from current pile
```

Without that loop, you cannot explore all possibilities.

---

# Why 2 loops fail

Suppose:

```
pile = [1, 100, 3]
coins = 2
```

From this pile you can take:

```
0 coins -> value = 0
1 coin  -> value = 1
2 coins -> value = 101
3 coins -> value = 104
```

These are DIFFERENT choices.

So for every state:

```
(i, coins)
```

we must try all valid:

```
currCoins
```

That is exactly the third loop.

---

# Analogy with Knapsack

Normal knapsack:

```
take item
or
skip item
```

Only 2 choices.

But here:

```
take 0
take 1
take 2
take 3
...
```

So this becomes:

```
multi-choice knapsack
```

Hence extra loop.

---

# What each loop means

```
for (int i = 1; i <= n; i++)
```

→ current pile

---

```
for (int coins = 0; coins <= k; coins++)
```

→ total coins allowed

---

```java 
for (int currCoins = 0; ...)
```

→ how many coins taken from current pile

---

# Think of transition

We are computing:

```
dp[i][coins]
```

To compute this state, we need ALL possibilities:

```
take 0 from current pile
take 1 from current pile
take 2 from current pile
...
```

and choose best.

That is why third loop is mandatory.
