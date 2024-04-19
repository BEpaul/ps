import java.io.*;
import java.util.*;

public class Main {
    static int v, e;
    static int[] set;
    static PriorityQueue<Edge> pq;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        set = new int[v + 1];
        initSet();

        pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getCost));

        int a, b, c;
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(a, b, c));
        }

        kruskal();

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    static void initSet() {
        for (int i = 1; i <= v; i++) {
            set[i] = i;
        }
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        set[rootA] = rootB;
    }

    static int find(int num) {
        if (set[num] == num) {
            return num;
        }

        return set[num] = find(set[num]);
    }

    static void kruskal() {
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (find(edge.start) == find(edge.end)) continue;

            answer += edge.cost;
            union(edge.start, edge.end);
        }
    }
}

class Edge {
    int start, end, cost;

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}