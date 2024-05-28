https://leetcode.com/problems/insert-interval/description/ 
 
public class InsertInterval { 

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) { 
        List<Interval> result = new ArrayList<>(); 
        int i = 0; 
        // Add all intervals that end before the new interval starts 
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) { 
            result.add(intervals.get(i)); 
            i++; 
        }
        
      // Merge overlapping intervals 
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) { 
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start); 
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end); 
            i++; 
        } 
      
        result.add(newInterval);
      
      // Add remaining intervals 
        while (i < intervals.size()) { 
            result.add(intervals.get(i)); 
            i++; 
        } 
        return result;
    } 

  

    public static void main(String[] args) { 
        List<Interval> intervals = Arrays.asList( 
            new Interval(1, 3), 
            new Interval(6, 9) 
        ); 

        Interval newInterval = new Interval(2, 5); 
        List<Interval> result = insert(intervals, newInterval); 
        for (Interval interval : result) { 
            System.out.println(interval); 
        } 
    } 
}
