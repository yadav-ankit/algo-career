https://leetcode.com/problems/merge-intervals/ 
// O(nlogn) + O(n) 

public class MergeIntervals { 

    public static List<Interval> merge(List<Interval> intervals) { 
        if (intervals == null || intervals.size() <= 1) { 
            return intervals; 
        } 

        // Sort intervals based on start time 
        intervals.sort(Comparator.comparingInt(i -> i.start)); 

        List<Interval> merged = new ArrayList<>(); 
        Interval current = intervals.get(0); 

        for (Interval interval : intervals) { 
            if (interval.start <= current.end) { 
                // Merge intervals 
                current.end = Math.max(current.end, interval.end); 
            } else { 
                // Add the current interval to the merged list and update the current interval 
                merged.add(current); 
                current = interval; 
            } 
        }
        // Add the last interval 
        merged.add(current); 
        return merged; 
    } 
