S = "agbcba"

LPS:
abcba

Length:
5


LPS(S) =LCS(S,reverse(S))


  Main intuition

Palindrome means:

same forward and backward

So:

Reverse string.

Example:

S = "agbcba"

Reverse:

abcbga

Find:

LCS(
S,
reverse(S)
)

Result:

abcba

Length:

5

That is the trick.
