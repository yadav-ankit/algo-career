import java.util.*;

// https://cses.fi/alon/task/1143 

⚡ Complexity
Build → O(n)
Each query → O(log n)
Total → O((n + m) log n)

  
class SegmentTree {
    int[] seg;
    int n;

    SegmentTree(int[] arr) {
        n = arr.length;
        seg = new int[4 * n];
        build(1, 0, n - 1, arr);
    }

    void build(int idx, int left, int right, int[] arr) {
        if (left == right) {
            seg[idx] = arr[left];
            return;
        }

        int mid = (left + right) / 2;

        build(2 * idx, left, mid, arr);
        build(2 * idx + 1, mid + 1, right, arr);

        seg[idx] = Math.max(seg[2 * idx], seg[2 * idx + 1]);
    }

    // 🔍 find first index with value >= x
    int query(int idx, int left, int right, int x) {
        if (seg[idx] < x) return -1; // no valid hotel

        if (left == right) return left;

        int mid = (left + right) / 2;

        if (seg[2 * idx] >= x) {
            return query(2 * idx, left, mid, x);
        } else {
            return query(2 * idx + 1, mid + 1, right, x);
        }
    }

    // 🔄 update: reduce value at position
    void update(int idx, int left, int right, int pos, int val) {
        if (left == right) {
            seg[idx] -= val;
            return;
        }

        int mid = (left + right) / 2;

        if (pos <= mid) {
            update(2 * idx, left, mid, pos, val);
        } else {
            update(2 * idx + 1, mid + 1, right, pos, val);
        }

        seg[idx] = Math.max(seg[2 * idx], seg[2 * idx + 1]);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] hotels = new int[n];
        for (int i = 0; i < n; i++) {
            hotels[i] = sc.nextInt();
        }

        SegmentTree st = new SegmentTree(hotels);

        StringBuilder result = new StringBuilder();

        while (m-- > 0) {
            int group = sc.nextInt();

            int pos = st.query(1, 0, n - 1, group);

            if (pos == -1) {
                result.append(0).append(" ");
            } else {
                result.append(pos + 1).append(" "); // convert to 1-based
                st.update(1, 0, n - 1, pos, group);
            }
        }

        System.out.println(result);
    }
}
