Longest repeating subsequence
This is a very famous LCS variation.

Problem:

Find the longest subsequence that appears at least twice in the same string.

The repeated subsequences must come from different indices.


Another example:

A = "axxxy"

Repeated subsequence:

xx

Answer:

2

because:

a x x x y
  ↑ ↑
    ↑ ↑

Different positions.

Main intuition

Looks like LCS:

Normally:

LCS(A,B)

Here:

Use:

LCS(A,A)

BUT

Need:

i != j

because same character at same position cannot match itself.

Example:

A = aab

Compare:

    a a b
  -------
a | ? ? ?
a | ? ? ?
b | ? ? ?

When:

i == j

ignore matching.

Only allow:

i != j

DP relation:

If:

s[i-1]==s[j-1]
AND
i!=j

then:
dp[i][j] = 1 + dp[i-1][j-1]

Else:

dp[i][j] = max(dp[i-1][j], dp[i][j-1])

Exactly LCS with one extra condition.
