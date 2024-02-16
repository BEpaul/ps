import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m, k;
    static int a, b;
    static long c;
    static long[] nums;
    static long[] tree;
    static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        nums = new long[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        S = 1;
        while (S < n) {
            S *= 2;
        }

        tree = new long[S * 2];
        init();

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Long.parseLong(st.nextToken());

            if (a == 1) { // b번 째 수를 c로 바꾸는 update
                long diff = c - tree[S + b - 1];
                update(1, S, 1, b, diff);
            } else { // b번째 수부터 c번째 수까지의 합 출력
                int intC = (int) c;
                System.out.println(query(1, S, 1, b, intC));
            }
        }
    }

    static void init() {
        for (int i = 0; i < n; i++) {
            tree[S + i] = nums[i];
        }

        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight) {
        // 1. 벗어난 경우
        if (right < queryLeft || queryRight < left) {
            return 0;
        } else if (queryLeft <= left && right <= queryRight) { // 2. 내부 -> 자기 자신 반환
            return tree[node];
        } else { // 3. 걸친 경우 -> 자식에게 위임
            int mid = (left + right) / 2;
            return query(left, mid, node * 2, queryLeft, queryRight) +
                    query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
        }
    }

    static void update(int left, int right, int node, int target, long diff) {
        // 1. 타겟 무관
        if (right < target || target < left) {
            return;
        } else { // 2. 타겟 연관
            tree[node] += diff;
            if (left != right) {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }
}