import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] nums, tree;
    static int a, b, c;
    static int S = 1;
    static final int INF = Integer.MAX_VALUE - 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        init();

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                update(b, c);
            } else {
                bw.write(query(1, S, 1, b, c) + "\n");
            }
        }

        br.close();
        bw.close();
    }

    static void init() {
        while (S < n) S *= 2;

        tree = new int[2 * S];
        Arrays.fill(tree, INF);

        for (int i = 0; i < n; i++) {
            tree[S + i] = nums[i];
        }

        for (int i = S - 1; i > 0; i--) {
            tree[i] = Math.min(tree[2 * i], tree[2 * i + 1]);
        }
    }

    static int query(int left, int right, int node, int queryLeft, int queryRight) {
        if (right < queryLeft || queryRight < left) return INF;
        if (queryLeft <= left && right <= queryRight) return tree[node];

        int mid = (left + right) / 2;
        return Math.min(query(left, mid, node * 2, queryLeft, queryRight),
                query(mid + 1, right, node * 2 + 1, queryLeft, queryRight));
    }

    static void update(int target, int value) {
        int node = S + target - 1;
        tree[node] = value;

        while (node >= 1) {
            node /= 2;
            tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
        }
    }
}