import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int n, m;
    static int s, d;
    static int[] dist;
    static List<Edge>[] adj;
    static List<Integer>[] route;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            adj = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
            }

            int u, v, p;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                p = Integer.parseInt(st.nextToken());

                adj[u].add(new Edge(v, p));
            }

            route = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                route[i] = new ArrayList<>();
            }

            dist = new int[n];
            Arrays.fill(dist, INF);

            dist[s] = 0;
            pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getCost));

            pq.add(new Edge(s, 0));
            Edge now, next;

            while (!pq.isEmpty()) {
                now = pq.poll();

                for (int i = 0; i < adj[now.index].size(); i++) {
                    next = adj[now.index].get(i);

                    if (dist[next.index] > dist[now.index] + next.cost) {
                        dist[next.index] = dist[now.index] + next.cost;
                        pq.add(new Edge(next.index, dist[now.index] + next.cost));

                        route[next.index].clear();
                        route[next.index].add(now.index);
                    } else if (dist[next.index] == dist[now.index] + next.cost) {
                        route[next.index].add(now.index);
                    }
                }
            }

            removeRoute();

            Arrays.fill(dist, INF);
            dist[s] = 0;

            pq.clear();
            pq.add(new Edge(s, 0));

            while (!pq.isEmpty()) {
                now = pq.poll();

                for (int i = 0; i < adj[now.index].size(); i++) {
                    next = adj[now.index].get(i);

                    if (dist[next.index] > dist[now.index] + next.cost) {
                        dist[next.index] = dist[now.index] + next.cost;
                        pq.add(new Edge(next.index, dist[now.index] + next.cost));
                    }
                }
            }

            if (dist[d] == INF) {
                bw.write(String.valueOf(-1));
                bw.newLine();
            } else {
                bw.write(String.valueOf(dist[d]));
                bw.newLine();
            }
        }

        bw.flush();

        br.close();
        bw.close();
    }

    static void removeRoute() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(d);
        boolean[] visited = new boolean[n];
        visited[d] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 0; i < route[cur].size(); i++) {
                int p = route[cur].get(i);

                for (int j = 0; j < adj[p].size(); j++) {
                    if (adj[p].get(j).index == cur) {
                        adj[p].remove(j);
                        break;
                    }
                }

                if (!visited[p]) {
                    queue.add(p);
                    visited[p] = true;
                }
            }
        }
    }
}

class Edge {
    int index;
    int cost;

    public Edge(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    public int getIndex() {
        return index;
    }

    public int getCost() {
        return cost;
    }
}