Given an array arr[] and an integer k, where every element is at most k positions away from its correct sorted position.
This means that if the array were completely sorted, the element at index i in the given array can be at any index from i - k to i + k.

Input: arr[]= [1, 4, 5, 2, 3, 6, 7, 8, 9, 10], k = 2
Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


public static void sortKSorted(int[] arr, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    int index = 0;

    // Add first k+1 elements
    for (int i = 0; i <= k && i < arr.length; i++) {
        pq.offer(arr[i]);
    }

    // Process remaining elements
    for (int i = k + 1; i < arr.length; i++) {
        arr[index++] = pq.poll();
        pq.offer(arr[i]);
    }

    // Empty heap
    while (!pq.isEmpty()) {
        arr[index++] = pq.poll();
    }
}

Key Observation
Since every element is at most k positions away from its final sorted position:
The smallest element among the first k+1 elements must be the first element in the sorted array.
After placing that element, the next smallest must lie within the next k+1 elements.
This naturally suggests using a Min Heap.
Example
arr = [6, 5, 3, 2, 8, 10, 9]
k = 3
Sorted array is
[2, 3, 5, 6, 8, 9, 10]
Notice:
2 moved from index 3 -> 0 (distance = 3)
3 moved from index 2 -> 1 (distance = 1)
5 moved from index 1 -> 2 (distance = 1)
...
No element moved more than 3 positions.
Why Min Heap of Size k+1?
Suppose we are finding the first element.
Since every element can move at most k places, the correct first element cannot be beyond index k.
Therefore,
First sorted element
must be somewhere inside

arr[0...k]
So:
Take first k+1 elements
↓

Find minimum
↓

Output it
A min heap gives the minimum in
O(log k)
Dry Run
arr = [6,5,3,2,8,10,9]
k=3
Initial heap (first 4 elements)
6
5
3
2

Heap

    2
   / \
  3   6
 /
5
Output
2
Insert next element (8)
Heap
3
5
6
8
Output
3
Insert 10
Heap
5
8
6
10
Output
5
Insert 9
Heap
6
8
10
9
Output
6
Finally remove remaining
8
9
10
Result
2 3 5 6 8 9 10
Algorithm
1. Insert first k+1 elements into min heap.

2. For every remaining element:
       Extract minimum -> answer
       Insert current element

3. Remove all remaining heap elements.
