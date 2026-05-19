https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/

same like aggrsive cows problem

for each iteration, canShip() scans the whole array:
O(N)

So total:

O(N * log(sum(weights)))

where:

N = number of packages
sum(weights) = total weight of all packages
    
class Solution {
    public int shipWithinDays(int[] weights, int days) {

        int low = 0;
        int high = 0;

        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (canShip(weights, days, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    boolean canShip(int[] weights, int days, int capacity) {

        int requiredDays = 1;
        int current = 0;

        for (int weight : weights) {

            if (current + weight > capacity) {
                requiredDays++;
                current = 0;
            }

            current += weight;
        }

        return requiredDays <= days;
    }
}


Minimum capacity:

max(weights)

because largest package must fit.

Maximum capacity:

sum(weights)

because ship all in one day.

Search:

[max(weights), sum(weights)]
Binary search intuition

Suppose:

weights = [3,2,2,4,1,4]
days = 3

Search:

low = 4
high = 16

Try:

mid = 10

Can we ship with capacity = 10?

Simulation:

Day1:

3+2+2 = 7
+4 -> exceeds

Day1 = 7

Day2:

4+1+4 = 9

Total:

2 days

Possible → reduce capacity

Binary search left.

Pattern

If capacity works:

try smaller
high = mid

Else:

need larger
low = mid + 1

Because answer = minimum feasible capacity
