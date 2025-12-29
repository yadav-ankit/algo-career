https://www.youtube.com/watch?v=IGIe46xw3YY

1 = Buy
0 = Sell
  
Extension of problem unlimited transactions allowed but with a cooldown

  So our logic becomes
if (buy) {
    return max(-prices[ind] + f(ind + 1, 0),0 + f(ind + 1, 1));
} else {
  // since there is cooldown to u buy only on ind + 2 instead of ind + 1
    return max(prices[ind] + f(ind + 2, 1),  0 + f(ind + 1, 0));
}



lets understand what it means

  Sell
  prices[ind] + f(ind + 2, 1),  0 + f(ind + 1, 0)


  if i allowed to sell i have 2 options to sell today or skip

  if sell today
  profit = prices[ind] + i will buy on (today + 2nd day) i.e f(ind + 2, 1)
  also f(ind +2 ,1) here 1 bcz i will buy in next function call
  
  OR
  skip
    f(ind + 1, 0)
   also f(ind +1 ,0) here 0 bcz i had already skipped selling so i will sell in next function call
