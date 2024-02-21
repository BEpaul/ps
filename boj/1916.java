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
	
	static int n, m;
	static int INF = Integer.MAX_VALUE;
	static List<Node>[] adj;
	static PriorityQueue<Node> pq;
	static int[] dist;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		dist = new int[n + 1];
		Arrays.fill(dist, INF);
		
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
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		pq = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
		pq.add(new Node(start, 0));
		
		Node now, next;
		while (!pq.isEmpty()) {
			now = pq.poll();
			
			if (dist[now.getIndex()] < now.getCost()) {
				continue;
			}
			
			for (int i = 0; i < adj[now.getIndex()].size(); i++) {
				next = adj[now.getIndex()].get(i);
				
				if (dist[next.getIndex()] > now.getCost() + next.getCost()) {
					dist[next.getIndex()] = now.getCost() + next.getCost();
					pq.add(new Node(next.getIndex(), now.getCost() + next.getCost()));
				}
			}
		}
		
		if (start == end) {
			bw.write(String.valueOf(0));
		} else {
			bw.write(String.valueOf(dist[end]));
		}
		
		br.close();
		bw.close();
	}
}

class Node {
	int index;
	int cost;
	
	public int getIndex() {
		return index;
	}

	public int getCost() {
		return cost;
	}

	public Node(int index, int cost) {
		this.index = index;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Node [index=" + index + ", cost=" + cost + "]";
	}
}
