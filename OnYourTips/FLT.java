Fermats little theorem..

If we want to do Mod p on division  like we want to do (a / b) % p

then we have to use this --> (a * b^(p-2)) % p

  ----------------

  
  Now this power is also do be done like this --> modPow(b, p - 2, p);


  static long modPow(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

