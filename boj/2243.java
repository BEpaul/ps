import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_CANDY_FAVOR = 1_000_001;

    static int n;
    static long a, b, c;
    static int[] nums;
    static int[] tree;

    static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        nums = new int[MAX_CANDY_FAVOR];

        S = 1;
        while (S < MAX_CANDY_FAVOR) {
            S *= 2;
        }

        tree = new int[2 * S];

        StringTokenizer st;
        int a, b, c;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (a == 1) {
                 bw.write(String.valueOf(query(1, MAX_CANDY_FAVOR, 1, b)));
                 bw.newLine();
            } else {
                c = Integer.parseInt(st.nextToken());
                update(1, MAX_CANDY_FAVOR, 1, b, c);
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static int query (int left, int right, int node, int target) {
        if (left == right) { // 1. 루트 노드를 찾은 경우
            update(1, MAX_CANDY_FAVOR, 1, left, -1);
            return left;
        }
        // 이분 탐색
        int mid = (left + right) / 2;
        if (target <= tree[node * 2]) { // 트리 왼쪽
            return query(left, mid, node * 2, target);
        } else { // 트리 오른쪽
            return query(mid + 1, right, node * 2 + 1, target - tree[node * 2]);
        }
    }

    static void update(int left, int right, int node, int target, int diff) {
        if (right < target || target < left) { // 타겟 무관
            return;
        } else { // 타겟 연관
            tree[node] += diff;
            if (left != right) {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }
}