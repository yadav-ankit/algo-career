import java.util.*;

/*
⚡ Complexity
Build → O(n)
Query → O(log n)
Update → O(log n)
*/
public class SegmentTree {

    static class SegTree {
        int[] tree;
        int n;

        SegTree(int[] arr) {
            n = arr.length;
            tree = new int[4 * n];
            build(arr, 0, 0, n - 1);
        }

        // Build tree
        void build(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }

            int mid = (start + end) / 2;

            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);

            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }

        // Range sum query [l, r]
        int query(int node, int start, int end, int l, int r) {
            // No overlap
            if (r < start || end < l) return 0;

            // Complete overlap
            if (l <= start && end <= r) return tree[node];

            // Partial overlap
            int mid = (start + end) / 2;

            int left = query(2 * node + 1, start, mid, l, r);
            int right = query(2 * node + 2, mid + 1, end, l, r);

            return left + right;
        }

        // Point update: set arr[idx] = val
        void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                tree[node] = val;
                return;
            }

            int mid = (start + end) / 2;

            if (idx <= mid)
                update(2 * node + 1, start, mid, idx, val);
            else
                update(2 * node + 2, mid + 1, end, idx, val);

            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 3, 4};

        SegTree st = new SegTree(arr);

        // Query sum from index 1 to 3
        System.out.println(st.query(0, 0, arr.length - 1, 1, 3)); // 1+5+3 = 9

        // Update index 2 to value 10
        st.update(0, 0, arr.length - 1, 2, 10);

        // Query again
        System.out.println(st.query(0, 0, arr.length - 1, 1, 3)); // 1+10+3 = 14
    }
}
