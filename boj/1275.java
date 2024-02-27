import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int n, q;
    static int[] nums;
    static long[] tree;
    static int S;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        nums = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        init();

        int x, y, a, b;
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (x > y) {
                int temp = x;
                x = y;
                y = temp;
            }

            bw.write(String.valueOf(query(1, S, 1, x, y)));
            bw.newLine();
            update(1, S, 1, a, b - tree[S + a - 1]);
        }

        br.close();
        bw.close();
    }

    static void init() {
        S = 1;
        while (S < n) {
            S *= 2;
        }

        tree = new long[S * 2];

        for (int i = 1; i <= n; i++) {
            tree[S + i - 1] = nums[i];
        }

        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight) {
        if (right < queryLeft || queryRight < left) {
            return 0;
        } else if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            return query(left, mid, node * 2, queryLeft, queryRight) +
                    query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
        }
    }

    static void update(int left, int right, int node, int target, long diff) {
        if (right < target || target < left) {
            return;
        } else {
            tree[node] += diff;
            if (left != right) {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }
}