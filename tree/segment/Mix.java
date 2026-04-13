

1. First Greater Element in Range (Classic Combo)
💡 Problem

Given array arr, for a query (l, r, x):

Find the first index in [l, r] where value > x

🧠 Idea
Segment Tree stores max
If max in range ≤ x → ❌ no answer
Else → go down the tree and binary search inside segment tree
