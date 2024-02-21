import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int count = 1;
    static int[] visited;
    static int[] lows;
    static List<Edge>[] adj;
    static List<Edge> res;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        res = new ArrayList<>();
        adj = new ArrayList[n + 1];
        visited = new int[n + 1];
        lows = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(new Edge(a, b));
            adj[b].add(new Edge(b, a));
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0) {
                dfs(i, 0);
            }
        }

        Comparator<Edge> nodeComparator = new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o2.s < o1.s) {
                    return 1;
                } else if (o2.s == o1.s) {
                    return o1.e - o2.e;
                } else {
                    return -1;
                }
            }
        };

        Collections.sort(res, nodeComparator);
        bw.write(String.valueOf(res.size()));
        bw.newLine();

        for (int i = 0; i < res.size(); i++) {
            bw.write(res.get(i).s + " " + res.get(i).e + "\n");
        }

        bw.flush();

        br.close();
        bw.close();
    }

    static int dfs(int node, int parent) {
        visited[node] = count++;
        int low = visited[node];

        Edge next;
        for (int i = 0; i < adj[node].size(); i++) {
            next = adj[node].get(i);

            if (next.e == parent) {
                continue;
            }

            if (visited[next.e] == 0) {

                int lowChild = dfs(next.e, node);
                lows[next.e] = lowChild;

                if (lowChild > visited[node]) {
                    res.add(new Edge(Math.min(node, next.e), Math.max(node, next.e)));
                }

                low = Math.min(low, lowChild);
            } else {
                low = Math.min(low, visited[next.e]);
            }

        }

        return low;
    }
}

class Edge {
    int s, e;

    public Edge(int s, int e) {
        this.s = s;
        this.e = e;
    }
}