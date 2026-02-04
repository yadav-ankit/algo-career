https://www.geeksforgeeks.org/dsa/minimum-swaps-required-to-make-a-binary-string-alternating/

Core idea 

An alternating binary string means:

Either all 1s sit on odd indices and 0s on even
→ 101010...

Or all 1s sit on even indices and 0s on odd
→ 010101...

So instead of building patterns, we just ask:

Are the 1’s sitting on the “wrong” parity positions?
  

static int countMinSwaps(String st)
    {
        // counts number of ones at odd
        // and even positions
        int odd_1 = 0, even_1 = 0;

        for (int i = 0; i < st.length(); i++) {
            if (st.charAt(i) == '1') {
                if (i % 2 == 0)
                    even_1++;
                else
                    odd_1++;
            }
        }

        // calculates the minimum number of swaps
        return Math.min(odd_1, even_1);
    }
