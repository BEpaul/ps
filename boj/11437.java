import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    static int n, m, k;
    static int[][] parent;
    static List<Integer>[] adj;
    static int[] depth;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        int s, e;
        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            adj[s].add(e);
            adj[e].add(s);
        }

        int temp = 1;
        k = 0;
        while (temp < n) {
            temp *= 2;
            k++;
        }

        parent = new int[k][n + 1];
        depth = new int[n + 1];

        dfs(1, 1);
        fillSparseTable();

        m = Integer.parseInt(br.readLine());

        int a, b;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            int result = lca(a, b);
            bw.write(String.valueOf(result));
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    static void dfs(int node, int count) {
        depth[node] = count;

        for (Integer next : adj[node]) {
            if (depth[next] == 0) {
                dfs(next, count + 1);
                parent[0][next] = node;
            }
        }
    }

    static void fillSparseTable() {
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }
    }

    static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int i = k - 1; i >= 0; i--) {
            int diff = depth[a] - depth[b];
            if (Math.pow(2, i) <= diff) {
                a = parent[i][a];
            }
        }

        if (a == b) {
            return a;
        }

        for (int i = k - 1; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }
}
