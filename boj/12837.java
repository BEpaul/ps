import java.io.*;
import java.util.*;

public class Main {
    static int n, q;
    static long[] tree;
    static int S = 1;
    static int a, b, c;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        init();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                update(1, S, 1, b, c);
            } else {
                bw.write(query(1, S, 1, b, c) + "\n");
            }
        }


        br.close();
        bw.close();
    }

    static void init() {
        while (S < n) S *= 2;

        tree = new long[2 * S];
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight) {
        if (right < queryLeft || queryRight < left) return 0;
        if (queryLeft <= left && right <= queryRight) return tree[node];

        int mid = (left + right) / 2;
        return query(left, mid, node * 2, queryLeft, queryRight)
                + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
    }

    static void update(int left, int right, int node, int target, int diff) {
        if (right < target || target < left) return;

        tree[node] += diff;
        if (left != right) {
            int mid = (left + right) / 2;
            update(left, mid, node * 2, target, diff);
            update(mid + 1, right, node * 2 + 1, target, diff);
        }
    }
}