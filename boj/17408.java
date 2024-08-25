import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] nums;
    static Node[] tree;
    static int S = 1;
    static int a, b, c;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        init();

        m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                update(b, c);
            } else {
                int answer = 0;
                Node max = query(1, S, 1, b, c);
                answer += max.value;

                if (max.index == b) {
                    answer += query(1, S, 1, b + 1, c).value;
                } else if (max.index == c) {
                    answer += query(1, S, 1, b, c - 1).value;
                } else {
                    answer += Math.max(query(1, S, 1, b, max.index - 1).value,
                                        query(1, S, 1, max.index + 1, c).value);
                }

                bw.write(answer + "\n");
            }
        }

        br.close();
        bw.close();
    }

    static void init() {
        while (S < n) S *= 2;

        tree = new Node[2 * S];

        for (int i = 0; i < S; i++) {
            if (i < n) tree[S + i] = new Node(i + 1, nums[i]);
            else tree[S + i] = new Node(0, 0);
        }
        tree[0] = new Node(0, 0);

        for (int i = S - 1; i > 0; i--) {
            tree[i] = (tree[2 * i].value >= tree[2 * i + 1].value) ? tree[2 * i] : tree[2 * i + 1];
        }
    }

    static Node query(int left, int right, int node, int queryLeft, int queryRight) {
        if (right < queryLeft || queryRight < left) return new Node(0, 0);
        if (queryLeft <= left && right <= queryRight) return tree[node];
        int mid = (left + right) / 2;

        Node leftQuery = query(left, mid, node * 2, queryLeft, queryRight);
        Node rightQuery = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);

        if (leftQuery.value >= rightQuery.value) return new Node(leftQuery.index, leftQuery.value);
        else return new Node(rightQuery.index, rightQuery.value);
    }

    static void update(int target, int value) {
        int node = S + target - 1;
        tree[node].index = target;
        tree[node].value = value;

        while(node >= 1) {
            node /= 2;
            tree[node] = (tree[2 * node].value >= tree[2 * node + 1].value) ? tree[2 * node] : tree[2 * node + 1];
        }
    }
}

class Node {
    int index;
    int value;

    public Node(int index, int value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "index=" + index +
                ", value=" + value +
                '}';
    }
}