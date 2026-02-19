import java.util.*;


🔹 Problem Restatement

You’re given two arrays:

A = [a1, a2, a3, ...]
B = [b1, b2, b3, ...]


Form a set:

S = { ai + bj | for all i, j }


That means every possible sum of an element from A with an element from B.

👉 Goal: Find the N-th smallest element in this set.

🔹 Brute Force (Not Ideal)

Generate all sums → sort → return Nth.

Time: O((n*m) log(n*m))

Space: O(n*m)

Too slow when arrays are large.

✅ Optimal Approach (Min Heap / Priority Queue)
Idea

If both arrays are sorted, we can cleverly generate sums in increasing order using a min heap, similar to merging sorted lists.

Steps

Sort A and B.

Push (A[0] + B[0], i=0, j=0) into a min heap.

Each time you pop smallest:

Push (A[i+1] + B[j])

Push (A[i] + B[j+1])

Use a visited set to avoid duplicates.

After popping N times → answer.

⏱ Complexity

Time: O(N log N)

Space: O(N)




public class NthSmallestSum {

    // For heap nodes
    static class Node {
        int sum, i, j;

        Node(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }
    }

    // For visited index pairs
    static class Pair {
        int i, j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return i == pair.i && j == pair.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    public static int nthSmallestSum(int[] A, int[] B, int N) {

        Arrays.sort(A);
        Arrays.sort(B);

        PriorityQueue<Node> pq =
                new PriorityQueue<>(Comparator.comparingInt(n -> n.sum));

        Set<Pair> visited = new HashSet<>();

        pq.add(new Node(A[0] + B[0], 0, 0));
        visited.add(new Pair(0, 0));

        Node current = null;

        for (int count = 0; count < N; count++) {

            current = pq.poll();
            int i = current.i;
            int j = current.j;

            // Next in A
            if (i + 1 < A.length) {
                Pair p1 = new Pair(i + 1, j);
                if (!visited.contains(p1)) {
                    pq.add(new Node(A[i + 1] + B[j], i + 1, j));
                    visited.add(p1);
                }
            }

            // Next in B
            if (j + 1 < B.length) {
                Pair p2 = new Pair(i, j + 1);
                if (!visited.contains(p2)) {
                    pq.add(new Node(A[i] + B[j + 1], i, j + 1));
                    visited.add(p2);
                }
            }
        }

        return current.sum;
    }

    public static void main(String[] args) {
        int[] A = {1, 3, 5};
        int[] B = {2, 4, 6};
        int N = 4;

        System.out.println(nthSmallestSum(A, B, N)); // 7
    }
}
