import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    static int n, e;
    static int[] dist1, dist2, dist3; // dist1 : 1 기준, dist2: N 기준, dist3: v1 ~ v2
    static List<Node>[] adj;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        dist1 = new int[n + 1];
        dist2 = new int[n + 1];
        dist3 = new int[n + 1];
        Arrays.fill(dist1, INF);
        Arrays.fill(dist2, INF);
        Arrays.fill(dist3, INF);

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        int a, b, c;
        for (int i = 1; i <= e; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        dist1[1] = 0;
        dist2[n] = 0;
        dist3[v1] = 0;

        pq = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        pq.add(new Node(1, 0));
        dijkstra(dist1);

        pq.add(new Node(n, 0));
        dijkstra(dist2);

        pq.add(new Node(v1, 0));
        dijkstra(dist3);
        int essentialCost = dist3[v2];

        if (dist1[v1] == INF || dist1[v2] == INF || dist2[v1] == INF || dist2[v2] == INF) {
            bw.write("-1");
            bw.flush();
            return;
        }

        int res1 = dist1[v1] + essentialCost + dist2[v2];
        int res2 = dist1[v2] + essentialCost + dist2[v1];
        int res3 = dist1[v1] + essentialCost * 2 + dist2[v1];
        int res4 = dist1[v2] + essentialCost * 2 + dist2[v2];

        bw.write(String.valueOf(min(res1, res2, res3, res4)));
        bw.flush();
        br.close();
        bw.close();
    }

    static void dijkstra(int[] dist) {
        Node now, next;
        while (!pq.isEmpty()) {
            now = pq.poll();
            for (int i = 0; i < adj[now.index].size(); i++) {
                next = adj[now.index].get(i);

                if (dist[next.index] > now.cost + next.cost) {
                    dist[next.index] = now.cost + next.cost;
                    pq.add(new Node(next.index, now.cost + next.cost));
                }
            }
        }
    }

    static int min(int a, int b, int c, int d) {
        int ret = a;
        if (ret > b) ret = b;
        if (ret > c) ret = c;
        if (ret > d) ret = d;

        return ret;
    }
}

class Node {
    int index;
    int cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Node{" +
                "index=" + index +
                ", cost=" + cost +
                '}';
    }
}