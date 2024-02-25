import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int n, m;
    static int[] nums;
    static int[] maxTree;
    static int[] minTree;
    static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        S = 1;
        while (S < n) {
            S *= 2;
        }

        maxTreeInit();
        minTreeInit();

        int a, b;
        int min, max;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            min = query(1, 1, S, 1, a, b);
            max = query(0, 1, S, 1, a, b);

            bw.write(String.valueOf(min) + " " + String.valueOf(max) + "\n");
        }

        br.close();
        bw.close();
    }

    static void maxTreeInit() {
        maxTree = new int[S * 2];
        for (int i = 0; i < n; i++) {
            maxTree[S + i] = nums[i];
        }

        for (int i = S - 1; i > 0; i--) {
            maxTree[i] = Math.max(maxTree[2 * i], maxTree[2 * i + 1]);
        }
    }

    static void minTreeInit() {
        minTree = new int[S * 2];
        Arrays.fill(minTree, INF);

        for (int i = 0; i < n; i++) {
            minTree[S + i] = nums[i];
        }

        for (int i = S - 1; i > 0; i--) {
            minTree[i] = Math.min(minTree[2 * i], minTree[2 * i + 1]);
        }
    }

    static int query(int type, int left, int right, int node, int queryLeft, int queryRight) {
        if (right < queryLeft || queryRight < left) {
            if (type == 0) {
                return 0;
            } else {
                return INF;
            }
        } else if (queryLeft <= left && right <= queryRight) {
            if (type == 0) {
                return maxTree[node];
            } else {
                return minTree[node];
            }
        } else {
            int mid = (left + right) / 2;
            if (type == 0) {
                return Math.max(query(0, left, mid, node * 2, queryLeft, queryRight),
                        query(0, mid + 1, right, node * 2 + 1, queryLeft, queryRight));
            } else {
                return Math.min(query(1, left, mid, node * 2, queryLeft, queryRight),
                        query(1, mid + 1, right, node * 2 + 1, queryLeft, queryRight));
            }
        }
    }
}