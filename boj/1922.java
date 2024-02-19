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
	static int[] parent;
	static List<Edge> edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		parent = new int[n + 1];
		edges = new ArrayList<>();
		
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			Edge edge = new Edge(a, b, c);
			
			edges.add(edge);
		}
		
		Collections.sort(edges, Comparator.comparingInt(Edge::getCost));
		
		
		long answer = 0;
		
		for (Edge edge : edges) {
			if (find(edge.getA()) != find(edge.getB())) {
				union(edge.getA(), edge.getB());
				answer += edge.getCost();
			}
		}
		
		bw.write(String.valueOf(answer));
		
		br.close();
		bw.close();
	}
	
	static int find(int num) {
		if (parent[num] == num) {
			return num;
		} else {
			return parent[num] = find(parent[num]);
		}
	}
	
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		parent[rootA] = rootB;
	}
	
}

class Edge {
	int a;
	int b;
	int cost;
	
	public Edge(int a, int b, int cost) {
		this.a = a;
		this.b = b;
		this.cost = cost;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int getCost() {
		return cost;
	}

	@Override
	public String toString() {
		return "Edge [a=" + a + ", b=" + b + ", cost=" + cost + "]";
	}
}
