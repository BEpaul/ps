import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int INF = 987654321;

    static int n, m;
    static int[] nums;
    static int[] tree;
    static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        nums = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        init();

        m = Integer.parseInt(br.readLine());
        int opt, a, b;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            opt = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (opt == 1) {
                update(1, S, 1, a, b); // a 를 b로 바꾼다.
            } else {
                bw.write(query(1, S, 1, a, b) + "\n");
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

        tree = new int[S * 2];

        nums[0] = INF;
        for (int i = 1; i <= n; i++) {
            tree[S + i - 1] = i;
        }

        for (int i = S - 1; i > 0; i--) {
            if (nums[tree[i * 2]] > nums[tree[i * 2 + 1]]) {
                tree[i] = tree[i * 2 + 1];
            } else {
                tree[i] = tree[i * 2];
            }
        }
    }

    static int query(int left, int right, int node, int queryLeft, int queryRight) {
        if (right < queryLeft || queryRight < left) {
            return -1;
        } else if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            int leftRes = query(left, mid, node * 2, queryLeft, queryRight);
            int rightRes = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);

            return getMin(leftRes, rightRes);
        }
    }

    static int getMin(int num1, int num2) {
        if (num1 == -1) return num2;
        else if (num2 == -1) return num1;
        else {
            if (nums[num1] > nums[num2]) {
                return num2;
            } else {
                return num1;
            }
        }
    }

    static void update(int left, int right, int node, int target, int val) {
        if (right < target || target < left) {
            return;
        } else {
            if (left == right) {
                tree[node] = target;
                nums[target] = val;
            } else {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, val);
                update(mid + 1, right, node * 2 + 1, target, val);

                if (nums[tree[node * 2]] > nums[tree[node * 2 + 1]]) {
                    tree[node] = tree[node * 2 + 1];
                } else {
                    tree[node] = tree[node * 2];
                }
            }
        }
    }
}