import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 987654321;

    static int n, m;
    static List<Node>[] adjs;
    static PriorityQueue<Node> pq;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjs = new ArrayList[n + 1];
        dist = new int[n + 1];
        Arrays.fill(dist, INF);

        for (int i = 1; i <= n; i++) {
            adjs[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjs[s].add(new Node(e, c));
            adjs[e].add(new Node(s, c));
        }

        dijkstra();

        bw.write(String.valueOf(dist[n]));
        br.close();
        bw.close();
    }

    static void dijkstra() {
        pq = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (int i = 0; i < adjs[now.index].size(); i++) {
                Node next = adjs[now.index].get(i);

                if (dist[now.index] < now.cost) continue;

                if (dist[next.index] > now.cost + next.cost) {
                    dist[next.index] = now.cost + next.cost;
                    pq.offer(new Node(next.index, now.cost + next.cost));
                }
            }
        }
    }
}

class Node {
    int index, cost;

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