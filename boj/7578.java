import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] nums;
    static int[] tree;
    static int S;
    static Map<Integer, Integer> map;
    static long answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        nums = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        map = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            map.put(Integer.parseInt(st.nextToken()), i);
        }

        init();

        for (int i = 1; i <= n; i++) {
            int index = map.get(nums[i]);
            update(1, S, 1, index, 1);
            answer += query(1, S, 1, index + 1, n);
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    static void init() {
        S = 1;
        while (S < n) {
            S *= 2;
        }

        tree = new int[S * 2];
    }

    static int query(int left, int right, int node, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 0;
        } else if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            return query(left, mid, node * 2, queryLeft, queryRight) +
                    query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
        }
    }

    static void update(int left, int right, int node, int target, int diff) {
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