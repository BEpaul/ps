import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m, k;
	static int[][] parent;
	static List<Integer>[] adj;
	static int[] depth;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		int s, e;
		StringTokenizer st;
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			adj[s].add(e);
			adj[e].add(s);
		}
		
		// 최대 depth 알아내기
		int temp = 1;
		k = 0;
		while (temp < n) {
			temp <<= 1;
			k++;
		}
		
		parent = new int[n + 1][k];
		depth = new int[n + 1];
		
		// dfs 돌려서 테이블 첫째 작성
		dfs(1, 1);
		fillSparseTable();
		
		m = Integer.parseInt(br.readLine());
		
		int a, b;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			int result = lca(a, b);
			bw.write(result + "\n");
		}
		
		br.close();
		bw.close();
	}
	
	static void dfs(int node, int count) {
		depth[node] = count;
		
		for (Integer next : adj[node]) {
			if (depth[next] == 0) {
				dfs(next, count + 1);
				parent[next][0] = node;
			}
		}
	}
	
	static void fillSparseTable() {
		for (int i = 1; i < k; i++) {
			for (int j = 1; j <= n; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
			}
		}
	}
	
	static int lca (int a, int b) {
		// depth가 a가 더 낮으면 더 깊은 것으로 swap
		if (depth[a] < depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		// depth를 동일하게 맞춘다.
		for (int i = k - 1; i >= 0; i--) {
			// depth 차이를 구한다.
			int diff = depth[a] - depth[b];
			if (Math.pow(2, i) <= diff) {
				a = parent[a][i];
			}
		}
		
		// a = b이면, 반환
		if (a == b) return a;
		
		// a != b이면, 위로 올라가면서 lca를 찾는다.
		for (int i = k - 1; i >= 0; i--) {
			if (parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		
		return parent[a][0];
	}
}
