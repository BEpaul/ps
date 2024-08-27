import java.io.*;
import java.util.*;

public class Main {
    static int t, n, m;
    static int[] tree;
    static final int MAX = 200_000;
    static int S = 1;
    static int check;
    static Map<Integer, Integer> position;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            init();

            st = new StringTokenizer(br.readLine());
            while (m-- > 0) {
                int select = Integer.parseInt(st.nextToken());
                int pos = position.get(select);
                
                bw.write(query(1, S, 1, pos + 1, MAX) + " ");
                update(pos, 0);
                update(check, 1);
                position.put(select, check++);
            }
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    static void init() {
        while (S < MAX) S *= 2;
        check = n + 1;

        position = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            position.put(i, n - i + 1);
        }

        tree = new int[2 * S];

        for (int i = 0; i < n; i++) {
            tree[S + i] = 1;
        }

        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    static int query(int left, int right, int node, int queryLeft, int queryRight) {
        if (right < queryLeft || queryRight < left) return 0;
        if (queryLeft <= left && right <= queryRight) return tree[node];

        int mid = (left + right) / 2;
        return query(left, mid, node * 2, queryLeft, queryRight)
                + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
    }

    static void update(int target, int value) {
        int node = S + target - 1;
        tree[node] = value;
        node /= 2;

        while (node > 1) {
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
            node /= 2;
        }
    }
}