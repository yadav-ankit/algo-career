
import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {

    private final PriorityQueue<Integer> maxHeap;
    private final PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

Two Heap Solution

Maintain:

Max Heap

Stores smaller half

3
2
1

Top = largest of smaller half

Min Heap

Stores larger half

4
5
6

Top = smallest of larger half

Visualization:

          Median
             |
1 2 3 | 4 5 6

maxHeap   minHeap

  Complexity
addNum

Heap insertion/removal:

O(log n)
findMedian

Just reading heap tops:

O(1)
  

    public void addNum(int num) {
      // Step 1 Always add to maxHeap.  
      maxHeap.offer(num);

      // Step 2 Move largest from maxHeap to minHeap.
        minHeap.offer(maxHeap.poll());

      // If minHeap becomes larger: Move one back.
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }

        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
}
