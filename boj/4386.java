import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] set;
    static Node[] nodes;
    static PriorityQueue<Edge> pq;
    static double answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        set = new int[n + 1];
        nodes = new Node[n + 1];
        pq = new PriorityQueue<>(Comparator.comparingDouble(Edge::getDistance));
        initSet();
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            nodes[i] = new Node(i, Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }

        kruskal();

        bw.write(String.format("%.2f", answer));

        br.close();
        bw.close();
    }

    static void initSet() {
        for (int i = 1; i <= n; i++) {
            set[i] = i;
        }
    }

    static double calculateDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        set[rootA] = rootB;
    }

    static int find(int num) {
        if (set[num] == num) return num;

        return set[num] = find(set[num]);
    }

    static void kruskal() {
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                pq.offer(new Edge(nodes[i].num, nodes[j].num,
                        calculateDist(nodes[i].x, nodes[i].y, nodes[j].x, nodes[j].y)));
            }
        }

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (find(edge.start) == find(edge.end)) continue;

            answer += edge.distance;
            union(edge.start, edge.end);
        }
    }
}

class Node {
    int num;
    double x, y;

    public Node(int num, double x, double y) {
        this.num = num;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

class Edge {
    int start, end;
    double distance;

    public Edge(int start, int end, double distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", distance=" + distance +
                '}';
    }
}