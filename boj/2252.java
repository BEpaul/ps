import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[] indegree;
	static List<Integer>[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		indegree = new int[n + 1];
		edges = new ArrayList[n + 1];
		
		for (int i = 1; i <= n; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			edges[a].add(b);
			indegree[b]++;
		}

		Queue<Integer> queue = new ArrayDeque<>();
		
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
				bw.write(i + " ");
			}
		}
		
		while (!queue.isEmpty()) {
			int zeroNode = queue.poll();
			
			for (int node : edges[zeroNode]) {
				indegree[node]--;
				if (indegree[node] == 0) {
					queue.add(node);
					bw.write(node + " ");
				}
			}
		}
		
		br.close();
		bw.close();
	}
}
