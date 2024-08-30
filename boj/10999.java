import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static long[] nums, tree, lazy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        int treeSize = getTreeSize();
        tree = new long[treeSize];
        lazy = new long[treeSize];

        init(0, n - 1, 1);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            if (a == 1) { // b~c번째 수에 d를 더한다.
                long d = Long.parseLong(st.nextToken());
                update(0, n - 1, 1, b, c, d);

            } else { // b~c번째 수의 합을 구하여 출력한다.
                bw.write(query(0, n-1, 1, b, c) + "\n");
            }
        }

        br.close();
        bw.close();
    }

    static int getTreeSize() {
        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        return 1 << (h + 1);
    }

    static long init(int left, int right, int node) {
        if (left == right) return tree[node] = nums[left];

        int mid = (left + right) / 2;
        return tree[node] = init(left, mid, node * 2) + init(mid + 1, right, node * 2 + 1);
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight) {
        propagate(left, right, node);
        if (right < queryLeft || queryRight < left) return 0;
        if (queryLeft <= left && right <= queryRight) return tree[node];

        int mid = (left + right) / 2;
        return query(left, mid, node * 2, queryLeft, queryRight)
                + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
    }

    static void update(int left, int right, int node, int queryLeft, int queryRight, long diff) {
        propagate(left, right, node);
        if (right < queryLeft || queryRight < left) return;
        if (queryLeft <= left && right <= queryRight) {
            lazy[node] = diff;
            propagate(left, right, node);
            return;
        }

        int mid = (left + right) / 2;
        update(left, mid, node * 2, queryLeft, queryRight, diff);
        update(mid + 1, right, node * 2 + 1, queryLeft, queryRight, diff);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static void propagate(int left, int right, int node) {
        if (lazy[node] != 0) {
            if (left != right) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }

            tree[node] += lazy[node] * (right - left + 1);
            lazy[node] = 0;
        }
    }
}
