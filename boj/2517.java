import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static int n;
    static int[] tree;
    static int S;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        nums = new int[n + 1];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            map.put(nums[i], 0);
        }

        List<Integer> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet);

        for (int i = 0; i < keySet.size(); i++) {
            map.put(keySet.get(i), i+1);
        }

        init();

        for (int i = 1; i <= n; i++) {
            bw.write(String.valueOf(query(1, S, 1, map.get(nums[i]) + 1, n) + 1));
            bw.newLine();
            update(1, S, 1, map.get(nums[i]), 1);
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
    }

    static int query(int left, int right, int node, int queryLeft, int queryRight) {
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
