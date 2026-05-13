

https://leetcode.com/problems/minimum-cost-for-tickets/description/

class Solution {

    public int mincostTickets(int[] days, int[] costs) {

        int lastDay = days[days.length - 1];

        int[] dp = new int[lastDay + 1];

        boolean[] travel = new boolean[lastDay + 1];

        for (int day : days) {
            travel[day] = true;
        }

        dp[0] = 0;

        for (int day = 1; day <= lastDay; day++) {

            // no travel on this day
            if (!travel[day]) {
                dp[day] = dp[day - 1];
                continue;
            }

            int oneDayPass =
                costs[0] + dp[Math.max(0, day - 1)];

            int sevenDayPass =
                costs[1] + dp[Math.max(0, day - 7)];

            int thirtyDayPass =
                costs[2] + dp[Math.max(0, day - 30)];

            dp[day] = Math.min(
                oneDayPass,
                Math.min(sevenDayPass, thirtyDayPass)
            );
        }

        return dp[lastDay];
    }
}


if (!travel[day])

=> "today is NOT a travel day"

So no need to buy any ticket.

Hence:

dp[day] = dp[day - 1];

because cost remains same as yesterday.
