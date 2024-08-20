import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] nums;
    static Node[] tree;
    static int a, b, c;
    static int S = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        init();

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                update(b, c);
            } else if (a == 2) {
                bw.write(query(0, 1, S, 1, b, c) + "\n");
            } else {
                bw.write(query(1, 1, S, 1, b, c) + "\n");
            }
        }

        br.close();
        bw.close();
    }

    static void init() {
        while (S < n) S *= 2;
        tree = new Node[2 * S];
        for (int i = 0; i < 2 * S; i++) {
            tree[i] = new Node(0, 0);
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                tree[S + i].increaseEven();
            } else {
                tree[S + i].increaseOdd();
            }
        }

        for (int i = S - 1; i > 0; i--) {
            tree[i].odds = tree[i * 2].odds + tree[i * 2 + 1].odds;
            tree[i].evens += tree[i * 2].evens + tree[i * 2 + 1].evens;
        }
    }

    static int query(int type, int left, int right, int node, int queryLeft, int queryRight) {
        if (right < queryLeft || queryRight < left) return 0;
        if (queryLeft <= left && right <= queryRight) {
            if (type == 0) return tree[node].evens;
            else return tree[node].odds;
        }

        int mid = (left + right) / 2;
        if (type == 0) {
            return query(0, left, mid, node * 2, queryLeft, queryRight)
                    + query(0, mid + 1, right, node * 2 + 1, queryLeft, queryRight);
        } else {
            return query(1, left, mid, node * 2, queryLeft, queryRight)
                    + query(1, mid + 1, right, node * 2 + 1, queryLeft, queryRight);
        }
    }

    static void update(int target, int value) {
        int node = S + target - 1;
        boolean isOdd = true;

        if (value % 2 == 0) {
            if (tree[node].evens == 1) return;
            isOdd = false;
            tree[node].decreaseOdd();
            tree[node].increaseEven();
        } else {
            if (tree[node].odds == 1) return;

            tree[node].increaseOdd();
            tree[node].decreaseEven();
        }

        while (node >= 1) {
            node /= 2;
            if (isOdd) {
                tree[node].increaseOdd();
                tree[node].decreaseEven();
            } else {
                tree[node].decreaseOdd();
                tree[node].increaseEven();
            }
        }
    }
}

class Node {
    int odds;
    int evens;

    public Node(int odds, int evens) {
        this.odds = odds;
        this.evens = evens;
    }

    @Override
    public String toString() {
        return "Node{" +
                "odds=" + odds +
                ", evens=" + evens +
                '}';
    }

    public void increaseOdd() {
        this.odds++;
    }

    public void decreaseOdd() {
        this.odds--;
    }

    public void increaseEven() {
        this.evens++;
    }

    public void decreaseEven() {
        this.evens--;
    }
}