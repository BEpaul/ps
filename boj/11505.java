import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int MODULO = 1_000_000_007;
    static int n, m, k;
    static int[] nums;
    static long[] tree;
    static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        init();

        int a, b, c;
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a == 1) { // b번째 수 -> c로 update
                update(b, c);
            } else { // b ~ c까지의 곱 query
                bw.write(String.valueOf(query(1, S, 1, b, c)));
                bw.newLine();
            }
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
        Arrays.fill(tree, 1);

        for (int i = 0; i < n; i++) {
            tree[S + i] = nums[i];
        }

        for (int i = S - 1; i > 0; i--) {
            tree[i] = (tree[i * 2] * tree[i * 2 + 1]) % MODULO;
        }
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight) {
        if (right < queryLeft || queryRight < left) {
            return 1;
        } else if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            return ((query(left, mid, node * 2, queryLeft, queryRight) % MODULO *
                    query(mid + 1, right, node * 2 + 1, queryLeft, queryRight) % MODULO) % MODULO);
        }
    }

    static void update(int target, int toValue) {
        int node = S + target - 1;
        tree[node] = toValue;
        node /= 2;
        while (node >= 1) {
            tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MODULO;
            node /= 2;
        }
    }
}