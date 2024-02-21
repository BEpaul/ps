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
	
	static int n, m, k;
	static PriorityQueue<Node> pq;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine());
		
		List<Node>[] adj = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		int s, e, c;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			adj[s].add(new Node(e, c));
		}
		
		int[] dist = new int[n + 1];
		Arrays.fill(dist, INF);
		
		pq = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
		pq.add(new Node(k, 0));
		
		Node now, next;
		while (!pq.isEmpty()) {
			now = pq.poll();
			
			for (int i = 0; i < adj[now.getIndex()].size(); i++) {
				next = adj[now.getIndex()].get(i);
				
				if (dist[next.getIndex()] > now.getCost() + next.getCost()) {
					dist[next.getIndex()] = now.getCost() + next.getCost();
					pq.add(new Node(next.getIndex(), now.getCost() + next.getCost()));
				}
			}
		}
		 
		for (int i = 1; i < dist.length; i++) {
			if (i == k) {
				bw.write(0 + "\n");
			} else if (dist[i] == INF) {
				bw.write("INF\n");
			} else {
				bw.write(String.valueOf(dist[i] + "\n"));
			}
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
	
	public int getIndex() {
		return index;
	}

	public int getCost() {
		return cost;
	}

	@Override
	public String toString() {
		return "Node [index=" + index + ", cost=" + cost + "]";
	}
}