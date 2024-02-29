import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int n, m;
    static long[] dist;
    static Edge[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        adj = new Edge[m];

        int a, b, c;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            adj[i] = new Edge(a, b, c);
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                if (dist[adj[j].s] != INF && dist[adj[j].e] > dist[adj[j].s] + adj[j].c) {
                    dist[adj[j].e] = dist[adj[j].s] + adj[j].c;
                }
            }
        }

        boolean flag = false;
        for (int j = 0; j < m; j++) {
            if (dist[adj[j].s] != INF && dist[adj[j].e] > dist[adj[j].s] + adj[j].c) {
                flag = true;
                break;
            }
        }

        if (flag) {
            bw.write("-1\n");
        } else {
            for (int i = 2; i <= n; i++) {
                if (dist[i] == INF) {
                    bw.write("-1\n");
                } else {
                    bw.write(String.valueOf(dist[i] + "\n"));
                }
            }
        }

        br.close();
        bw.close();
    }
}

class Edge {
    int s, e, c;

    public Edge(int s, int e, int c) {
        this.s = s;
        this.e = e;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "s=" + s +
                ", e=" + e +
                ", c=" + c +
                '}';
    }
}