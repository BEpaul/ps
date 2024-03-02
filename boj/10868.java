import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1_987_654_321;
    static int n, m;
    static int[] nums;
    static int[] tree;
    static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        init();

        int a, b;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            bw.write(String.valueOf(query(1, S, 1, a, b)));
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    static void init() {
        S = 1;
        while (S < n) {
            S *= 2;
        }

        tree = new int[S * 2];
        Arrays.fill(tree, INF);
        for (int i = 1; i <= n; i++) {
            tree[S + i - 1] = nums[i];
        }

        for (int i = S - 1; i > 0; i--) {
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
        }
    }

    static int query(int left, int right, int node, int queryLeft, int queryRight) {
        if (right < queryLeft || queryRight < left) {
            return INF;
        } else if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            return Math.min(query(left, mid, node * 2, queryLeft, queryRight),
                    query(mid + 1, right, node * 2 + 1, queryLeft, queryRight));
        }
    }
}