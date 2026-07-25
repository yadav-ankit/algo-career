Greatest common divisor 
  for 10,15 gcd = 5

int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
}


gcd(n , n+1) = 1


  https://leetcode.com/problems/gcd-of-odd-and-even-sums/
sumOdd: the sum of the smallest n positive odd numbers = n * n

sumEven: the sum of the smallest n positive even numbers = n* ( n + 1)

  that means
  if n = 3 then first 3 odd numbers are 1 , 3 , 5 and thier sum = 9 = 3*3
  similarly first 3 even numbers are 2,4,6 , thier sum = 12 = 3 * (3+1) 

  so gcd(n*n , n*(n+1) 

  = n * gcd( n , n+1)

  = n * 1 = n

