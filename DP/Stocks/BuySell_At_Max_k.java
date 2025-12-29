1 = Buy
0 = Sell

https://www.geeksforgeeks.org/dsa/maximum-profit-by-buying-and-selling-a-share-at-most-k-times/#expected-approach2-space-optmized-onk-time-and-onk-space

https://www.youtube.com/watch?v=-uQGzhYj8BQ
    ​
    static int maxProfit(int[] prices, int k) {
        int n = prices.length;
​
        int[][] ahead = new int[k + 1][2];
        int[][] curr = new int[k + 1][2];
​
        // Traverse days from end to start
        for (int i = n - 1; i >= 0; i--) {
​
            // Traverse all transactions
            for (int t = 1; t <= k; t++) {
​
                // If we can buy
                curr[t][1] = Math.max(-prices[i] + ahead[t][0], ahead[t][1]);
​
                // If we can sell
                curr[t][0] = Math.max(prices[i] + ahead[t - 1][1], ahead[t][0]);
            }
​
            ahead = curr.clone();
        }
​
        return ahead[k][1];
    }


My Understanding...
  
There r 3 states we need to count on

  1. day
  2. Buy/Sell
  3. No. of transactions done

so we wil have dp[day][B/S][transactions or k]

  Buy = 1
  Sell = 0

  Also we can see B/S and Transactions are constant for a day so
  
  To covert 3d dp array to 2d we have moved dp[day] to curr and dp[day+1] to ahead
  so now we will have

  curr[B/S][k] and ahead[B/S][k]

  Now logic in the code ...
 curr[t][1] = Math.max(-prices[i] + ahead[t][0], ahead[t][1]);

 it says
   for today if we are buying we have 2 options Buy or skip for next day
   if we buy
     -prices[i] = bcz we have invested from our pockert so -ve
   ahead[t][0] = 
     t bcz transaction stil not competed  (it will complete when i will sell)
     0 bcz i have given authrotiy to sell in days ahead

   if we skip
   ahead[t][1]
     t bcz transaction stil not competed  (it will complete when i will sell)
     1 bcz i have not buy so i will buy in days ahead.
    

     -------------------------------
Now SELL
     curr[t][0] = Math.max(prices[i] + ahead[t - 1][1], ahead[t][0]);

 if we sell
     prices[i] = bcz we have recieved in our pockert so +ve
   ahead[t][1] = 
     t-1 bcz transaction competed (now i have 1 less transaction to think of)
     1 bcz i have given authrotiy to buy in days ahead for next transaction

   if we skip
   ahead[t][0]
     t bcz transaction stil not competed  (it will complete only when i will sell)
     0 bcz i have not sell, so i will sell in days ahead.
   

​
