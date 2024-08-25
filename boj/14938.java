import java.io.*;
import java.util.*;

public class Main {
    static int n, m, r;
    static int[] items, dist;
    static List<Node>[] adjs;
    static int answer;
    static PriorityQueue<Node> pq;

    static final int INF = 100;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        items = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        adjs = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adjs[i] = new ArrayList<>();
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            adjs[a].add(new Node(b, l));
            adjs[b].add(new Node(a, l));
        }

        dijkstra();

        bw.write(String.valueOf(answer));
        bw.flush();

        br.close();
        bw.close();
    }

    static void dijkstra() {
        pq = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        dist = new int[n + 1];

        for (int node = 1; node <= n; node++) {
            int itemCount = 0;
            Arrays.fill(dist, INF);
            pq.offer(new Node(node, 0));
            while (!pq.isEmpty()) {
                Node now = pq.poll();
                for (int i = 0; i < adjs[now.index].size(); i++) {
                    if (dist[now.index] < now.cost) continue;

                    Node next = adjs[now.index].get(i);
                    if (dist[next.index] > now.cost + next.cost) {
                        dist[next.index] = now.cost + next.cost;
                        pq.offer(new Node(next.index, now.cost + next.cost));
                    }
                }
            }

            for (int i = 1; i <= n; i++) {
                if (i == node || dist[i] <= m) itemCount += items[i];
            }

            answer = Math.max(answer, itemCount);
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