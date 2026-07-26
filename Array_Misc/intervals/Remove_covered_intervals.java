## LeetCode 1288 — Remove Covered Intervals

**Problem:** Given a list of intervals, remove all intervals that are covered by another interval. Return the number of remaining intervals.

An interval `[a, b]` is covered by `[c, d]` if `c <= a` and `b <= d`.

---

### Approach: Sort + Greedy

1. **Sort** by start ascending; for ties, sort by end **descending** (larger interval first).
2. **Iterate** and track the max end seen so far. If the current interval's end ≤ max end, it's covered — skip it. Otherwise, count it and update max end.

Why descending end on ties? If two intervals share the same start, the one with the larger end will cover the smaller one — putting it first lets us skip the smaller one naturally.

---

### Code (Java)

```java
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort by start asc; on tie, sort by end desc
        Arrays.sort(intervals, (a, b) ->
            a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]
        );

        int count = 0;
        int maxEnd = 0;

        for (int[] interval : intervals) {
            if (interval[1] > maxEnd) {
                count++;
                maxEnd = interval[1];
            }
            // else: interval[1] <= maxEnd → covered, skip
        }

        return count;
    }
}
```

---

### Walkthrough — `[[1,4],[3,6],[2,8]]`

After sort (by start asc, end desc): `[[1,4],[2,8],[3,6]]`

| Interval | maxEnd before | Action         | maxEnd after |
|----------|--------------|----------------|-------------|
| [1,4]    | 0            | count++ → 1    | 4           |
| [2,8]    | 4            | count++ → 2    | 8           |
| [3,6]    | 8            | 6 ≤ 8, skip    | 8           |

**Result: 2** ✓

---

### Complexity

| | |
|---|---|
| **Time** | O(n log n) — sorting |
| **Space** | O(1) — excluding sort overhead |

---

**Key insight:** After sorting, you only need to track `maxEnd`. You never need to look back — any interval whose end doesn't exceed the running max is guaranteed to be covered.
