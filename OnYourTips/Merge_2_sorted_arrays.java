public static int[] merge(int[] a, int[] b) {
    int n = a.length;
    int m = b.length;

    int[] result = new int[n + m];

    int i = 0, j = 0, k = 0;

    while (i < n && j < m) {
        if (a[i] <= b[j]) {
            result[k++] = a[i++];
        } else {
            result[k++] = b[j++];
        }
    }

    while (i < n) {
        result[k++] = a[i++];
    }

    while (j < m) {
        result[k++] = b[j++];
    }

    return result;
}
