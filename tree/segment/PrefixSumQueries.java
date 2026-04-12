import java.io.*;
import java.util.*;
// https://cses.fi/problemset/task/2166
Time:  O((n + q) log n)
Space: O(n)
  
public class Main {

    static class Node {
        long sum;
        long prefix;

        Node(long sum, long prefix) {
            this.sum = sum;
            this.prefix = prefix;
        }
    }

    static Node[] seg;
    static int n;

    // 🔗 merge two nodes
    static Node merge(Node left, Node right) {
        long sum = left.sum + right.sum;
        long prefix = Math.max(left.prefix, left.sum + right.prefix);
        return new Node(sum, prefix);
    }

    // 🧱 build tree
    static void build(int idx, int l, int r, int[] arr) {
        if (l == r) {
            seg[idx] = new Node(arr[l], Math.max(arr[l], 0));
            return;
        }

        int mid = (l + r) / 2;

        build(2 * idx, l, mid, arr);
        build(2 * idx + 1, mid + 1, r, arr);

        seg[idx] = merge(seg[2 * idx], seg[2 * idx + 1]);
    }

    // 🔍 query
    static Node query(int idx, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) {
            return new Node(0, 0); // neutral node
        }

        if (ql <= l && r <= qr) {
            return seg[idx];
        }

        int mid = (l + r) / 2;

        Node left = query(2 * idx, l, mid, ql, qr);
        Node right = query(2 * idx + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    // 🔄 update
    static void update(int idx, int l, int r, int pos, int val) {
        if (l == r) {
            seg[idx] = new Node(val, Math.max(val, 0));
            return;
        }

        int mid = (l + r) / 2;

        if (pos <= mid) {
            update(2 * idx, l, mid, pos, val);
        } else {
            update(2 * idx + 1, mid + 1, r, pos, val);
        }

        seg[idx] = merge(seg[2 * idx], seg[2 * idx + 1]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        seg = new Node[4 * n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        build(1, 0, n - 1, arr);

        StringBuilder output = new StringBuilder();

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) {
                int k = Integer.parseInt(st.nextToken()) - 1;
                int u = Integer.parseInt(st.nextToken());
                update(1, 0, n - 1, k, u);
            } else {
                int l = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken()) - 1;

                Node res = query(1, 0, n - 1, l, r);
                output.append(res.prefix).append("\n");
            }
        }

        System.out.print(output);
    }
}
