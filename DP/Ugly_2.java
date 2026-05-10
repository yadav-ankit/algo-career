Complexity
Time  : O(n)
Space : O(n)

ok but why are we multiplying and taking min


Because every ugly number must come from a previous ugly number multiplied by:

2 or 3 or 5

That is the entire foundation of the problem.

Suppose we already generated:

1, 2, 3, 4, 5

Now what can be the NEXT ugly number?

Possible candidates:

2 × previous ugly
3 × previous ugly
5 × previous ugly

So:

2*1 = 2
2*2 = 4
2*3 = 6
...

3*1 = 3
3*2 = 6
3*3 = 9
...

5*1 = 5
5*2 = 10
...

These form 3 sorted streams.

We want the next smallest ugly number.

So we take:

min(next2, next3, next5)

because the next ugly number must be the smallest unused valid candidate.



class Solution {

    public int nthUglyNumber(int n) {

        int[] ugly = new int[n];

        ugly[0] = 1;

        int i2 = 0;
        int i3 = 0;
        int i5 = 0;

        for (int i = 1; i < n; i++) {

            int next2 = ugly[i2] * 2;
            int next3 = ugly[i3] * 3;
            int next5 = ugly[i5] * 5;

            int nextUgly = Math.min(next2,
                            Math.min(next3, next5));

            ugly[i] = nextUgly;

            if (nextUgly == next2) {
                i2++;
            }

            if (nextUgly == next3) {
                i3++;
            }

            if (nextUgly == next5) {
                i5++;
            }
        }

        return ugly[n - 1];
    }
}
