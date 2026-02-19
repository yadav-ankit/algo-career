import java.util.*;

Time:
O(N log k)

  💡 Idea (Same as Linked List Version)

Put the first element of each array into a min-heap.

Each heap node stores:

value

which array it came from

index inside that array

When you poll smallest:

Add to result

Insert next element from same array

Heap size never exceeds k.
  
public class MergeKSortedArrays {

    static class Element {
        int value;
        int arrayIndex;
        int elementIndex;

        Element(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static int[] mergeKArrays(int[][] arrays) {

        PriorityQueue<Element> pq =
                new PriorityQueue<>((a, b) -> a.value - b.value);

        int totalSize = 0;

        // Add first element of each array
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                pq.offer(new Element(arrays[i][0], i, 0));
                totalSize += arrays[i].length;
            }
        }

        int[] result = new int[totalSize];
        int index = 0;

        while (!pq.isEmpty()) {
            Element current = pq.poll();
            result[index++] = current.value;

            int nextIndex = current.elementIndex + 1;

            if (nextIndex < arrays[current.arrayIndex].length) {
                pq.offer(new Element(
                        arrays[current.arrayIndex][nextIndex],
                        current.arrayIndex,
                        nextIndex
                ));
            }
        }

        return result;
    }

    // Test
    public static void main(String[] args) {

        int[][] arrays = {
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9}
        };

        int[] merged = mergeKArrays(arrays);

        System.out.println(Arrays.toString(merged));
        // Output: [1,2,3,4,5,6,7,8,9]
    }
}
