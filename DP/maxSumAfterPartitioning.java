https://leetcode.com/problems/partition-array-for-maximum-sum/description/


```java
class Solution {

    public int maxSumAfterPartitioning(int[] arr, int k) {

        int n = arr.length;

        // t[i] = max sum for first i elements
        int[] t = new int[n + 1];

        for (int size = 1; size <= n; size++) {

            int currMax = -1;

            for (int j = 1; j <= k && size - j >= 0; j++) {

                currMax = Math.max(currMax, arr[size - j]);

                t[size] = Math.max(
                    t[size],
                    (j * currMax) + t[size - j]
                );
            }
        }

        return t[n];
    }
}
```




Let’s dry run:

arr = [1,15,7,9,2,5,10]
k = 3

DP meaning:

t[i] = best answer for first i elements

So:

t[0] = 0

Initial DP:

[0,0,0,0,0,0,0,0]
size = 1

We are solving for:

[1]
j = 1

Partition:

[1]
currMax = 1
value = 1 * 1 = 1

Remaining:

t[0]

So:

t[1] = max(0, 1 + 0)
     = 1

DP:

[0,1,0,0,0,0,0,0]
size = 2

Subarray:

[1,15]
j = 1

Last partition:

[15]
currMax = 15
value = 15 * 1 = 15

Remaining:

t[1] = 1
15 + 1 = 16
t[2] = 16
j = 2

Last partition:

[1,15]
currMax = 15
value = 15 * 2 = 30

Remaining:

t[0] = 0
30 + 0 = 30
t[2] = max(16,30)
     = 30

DP:

[0,1,30,0,0,0,0,0]
size = 3

Subarray:

[1,15,7]
j = 1

Partition:

[7]
7 + t[2]
= 7 + 30
= 37
t[3] = 37
j = 2

Partition:

[15,7]
currMax = 15
value = 15 * 2 = 30

Remaining:

t[1] = 1
30 + 1 = 31

No improvement.

j = 3

Partition:

[1,15,7]
currMax = 15
value = 15 * 3 = 45

Remaining:

t[0] = 0
45 + 0 = 45
t[3] = 45

DP:

[0,1,30,45,0,0,0,0]
size = 4

Subarray:

[1,15,7,9]
j = 1

Partition:

[9]
9 + t[3]
= 9 + 45
= 54
t[4] = 54
j = 2

Partition:

[7,9]
currMax = 9
value = 18

Remaining:

t[2] = 30
18 + 30 = 48

No improvement.

j = 3

Partition:

[15,7,9]
currMax = 15
value = 45

Remaining:

t[1] = 1
45 + 1 = 46

No improvement.

DP now:

[0,1,30,45,54,0,0,0]

Eventually final DP becomes:

[0,1,30,45,54,63,72,84]

Answer:

84
Important Insight

At every size:

We try every possible LAST partition.

And combine:

current partition value
+
best answer before it

That’s the entire DP idea
