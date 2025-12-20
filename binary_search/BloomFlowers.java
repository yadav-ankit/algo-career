/*  Time = O(nlogn)
One-line mental model (very important 🧠)

  flowers counts consecutive bloomed flowers
  bouquets counts how many times we reach k
*/

// Returns the minimum day needed to make 'm' bouquets
// Each bouquet needs 'k' adjacent flowers
static int minDays(int[] bloomDay, int m, int k) {

    // STEP 1: Impossible case
    // If total flowers needed > total flowers available
    // then it's impossible no matter how many days we wait
    if ((long) m * k > bloomDay.length)
        return -1;

    int low = Integer.MAX_VALUE;
    int high = Integer.MIN_VALUE;

    for (int day : bloomDay) {
        low = Math.min(low, day);
        high = Math.max(high, day);
    }

    // This will store the best (minimum) valid day found
    int ans = -1;

    // STEP 3: Binary search on days
    while (low <= high) {

        // Pick a middle day to check feasibility
        int mid = low + (high - low) / 2;

        // STEP 4: Check if we can make m bouquets by 'mid' day
        if (canMake(bloomDay, m, k, mid)) {

            // If possible, record this day as an answer
            ans = mid;

            // Try to find an even smaller (earlier) day
            high = mid - 1;

        } else {
            // If not possible, we need to wait more days
            low = mid + 1;
        }
    }

    // STEP 5: Return the minimum day found (or -1 if none)
    return ans;
}


// Returns true if we can make at least 'm' bouquets
 // using 'k' adjacent flowers by 'day'

static boolean canMake(int[] bloomDay, int m, int k, int day) {

    int bouquets = 0;   // how many bouquets we have made so far
    int flowers = 0;    // count of consecutive bloomed flowers

    // traverse each flower from left to right
    for (int i = 0; i < bloomDay.length; i++) {

        // if flower is bloomed by 'day'
        if (bloomDay[i] <= day) {
            flowers++;                 // extend the adjacent streak

            // if we collected k adjacent flowers
            if (flowers == k) {
                bouquets++;            // make one bouquet
                flowers = 0;           // reset for next bouquet
            }

        } else {
            // flower not bloomed → adjacency breaks
            flowers = 0;
        }
    }

    // check if we made enough bouquets
    return bouquets >= m;
}
