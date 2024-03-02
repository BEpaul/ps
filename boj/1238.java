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
    static int n, m, x;
    static int[] dist;
    static List<Node>[] adj;
    static int[] timeCost;
    static PriorityQueue<Node> pq;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        dist = new int[n + 1];
        Arrays.fill(dist, INF);

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        timeCost = new int[n + 1];

        int s, e, t;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            adj[s].add(new Node(e, t));
        }

        pq = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        pq.add(new Node(x, 0));

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

        for (int i = 1; i <= n; i++) {
            if (i != x) {
                timeCost[i] += dist[i];
            }
        }

        for (int i = 1; i <= n; i++) {
            if (i != x) {
                dijkstra(i);
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, timeCost[i]);
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    static void dijkstra(int start) {
        Arrays.fill(dist, INF);
        pq.add(new Node(start, 0));

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

        timeCost[start] += dist[x];
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