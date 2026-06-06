
House robber - i 
DP for Linear House Robber

For a range [l, r]:

dp[i] = max(
    dp[i - 1],          // skip current house
    dp[i - 2] + nums[i] // rob current house
)

  Any valid solution must belong to one of these cases:

------------------------------------------------
House robber - ii
Rob from houses 0 to n-2 (exclude last)
Rob from houses 1 to n-1 (exclude first)

Then take the maximum of the two results. This reduces the problem to two standard House Robber runs.


------------------------------------------------
House robber - ii
  
https://github.com/yadav-ankit/algo-career/blob/main/tree/HouseRobber_III.java

