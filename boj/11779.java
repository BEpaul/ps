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
    static int n, m;
    static int start, end;
    static int[] dist;
    static List<Node>[] adj;
    static int[] routes;
    static List<Integer> answers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dist = new int[n + 1];
        Arrays.fill(dist, INF);

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        routes = new int[n + 1];

        StringTokenizer st;
        int s, e, c;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            adj[s].add(new Node(e, c));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        pq.add(new Node(start, 0));

        Node now, next;
        while (!pq.isEmpty()) {
            now = pq.poll();
            if (dist[now.index] < now.cost) {
                continue;
            }

            for (int i = 0; i < adj[now.index].size(); i++) {
                next = adj[now.index].get(i);

                if (dist[next.index] > now.cost + next.cost) {
                    dist[next.index] = now.cost + next.cost;
                    pq.add(new Node(next.index, now.cost + next.cost));

                    routes[next.index] = now.index;
                }
            }
        }

        answers = new ArrayList<>();
        answers.add(end);
        bw.write(dist[end] + "\n");

        while (true) {
            int route = routes[end];
            answers.add(route);
            end = routes[end];

            if (route == start) {
                break;
            }
        }

        bw.write(answers.size() + "\n");
        for (int i = answers.size() - 1; i >= 0; i--) {
            bw.write(answers.get(i) + " ");
        }
        br.close();
        bw.close();
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