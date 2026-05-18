
https://www.geeksforgeeks.org/problems/aggressive-cows/1

import java.util.Arrays;

Complexity:

O(N log N)

where:

N = number of stalls
2. Binary search on answer (minimum distance)

Search space:

1 → max(stalls) - min(stalls)

Suppose:

D = stalls[n-1] - stalls[0]

Binary search iterations:

O(log D)
3. Feasibility check (canPlace())

For each guessed distance:

Traverse stalls once:

O(N)

Total:

Binary search × feasibility:

O(N log D)

Add sorting:

O(N log N + N log D)
  
class Solution {

    public int aggressiveCows(int[] stalls, int cows) {
        Arrays.sort(stalls);

        int left = 1;
        int right = stalls[stalls.length - 1] - stalls[0];

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canPlace(stalls, cows, mid))
                left = mid + 1;   // try bigger distance
            else
                right = mid - 1;  // reduce distance
        }

        return right;
    }

    private boolean canPlace(int[] stalls, int cows, int minDist) {
        int count = 1;
        int lastPos = stalls[0];

        for (int i = 1; i < stalls.length; i++) {

            if (stalls[i] - lastPos >= minDist) {
                count++; //cow placed
                lastPos = stalls[i];
            }

            if (count == cows)
                return true;
        }

        return false;
    }
}


Let’s do this slowly with one example.

Problem:

stalls = [1,2,4,8,9]
cows = 3

Meaning:

There are stalls at positions:

1----2------4-------------8---9

Need to place 3 cows such that the minimum distance between any two cows is as large as possible.

Step 1: Guess an answer

Binary search idea:

Ask:

Can I place 3 cows with at least 3 distance apart?

Try:

minDistance = 3

Place first cow:

Cow1 → stall 1

Need next cow at:

>= 1+3

Possible:

4

Place:

Cow2 → stall 4

Need:

>= 4+3

Possible:

8

Place:

Cow3 → stall 8

Placed all cows:

1 --- 4 ---- 8

Success.

So:

distance=3 works

Maybe bigger distance also works.

Step 2: Try larger distance

Try:

distance=4

Place:

Cow1:

1

Need:

>=5

Next:

8

Place:

Cow2:

8

Need:

>=12

None.

Only:

2 cows

Fail.

So:

distance=4 doesn't work

Notice:

distance=1 ✓
distance=2 ✓
distance=3 ✓
distance=4 ✗
distance=5 ✗

Pattern:

TTTFFF

This monotonic pattern means:

Binary search works

Binary search:

Search space:

left=1
right=9-1=8

Iteration 1:

mid=(1+8)/2=4

Can place cows with distance=4?

No.

So:

right=3

Iteration 2:

left=1
right=3

mid=2

Distance=2 works.

Try larger:

left=3

Iteration 3:

left=3
right=3

mid=3

Distance=3 works.

Try larger:

left=4

Stop:

left=4
right=3

Answer:

right=3
  
