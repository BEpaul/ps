import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int count = 1;
	static int[] visited;
	static boolean[] isCut;
	static List<Integer>[] adj;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		adj = new ArrayList[n + 1];
		visited = new int[n + 1];
		isCut = new boolean[n + 1];
		
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		int s, e;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			adj[s].add(e);
			adj[e].add(s);
		}
		
		
		// dfs
		for (int i = 1; i <= n; i++) {
			if (visited[i] == 0) {
				dfs(i, true);
			}
		}
		
		// 출력
		int cnt = 0;
		for (int i = 1; i <= n; i++) {
			if (isCut[i]) {
				cnt++;
			}
		}
		
		bw.write(String.valueOf(cnt));
		bw.newLine();
		
		for (int i = 1; i <= n; i++) {
			if (isCut[i]) {
				bw.write(String.valueOf(i) + " ");
			}
		}
		
		br.close();
		bw.close();
	}
	
	static int dfs (int now, boolean isRoot) {
		visited[now] = count++;
		int low = visited[now]; // 인접한 노드 중에 가장 빨리 방문되는 순서
		int child = 0;
		
		int next;
		for (int i = 0; i < adj[now].size(); i++) {
			next = adj[now].get(i);
			
			if (visited[next] == 0) {
				child++;
				
				// low: 자식 노드가 갈 수 있는 노드 중에 가장 일찍 방문한 노드의 순번
				int lowChild = dfs(next, false);
				if (!isRoot && visited[now] <= lowChild) {
					isCut[now] = true;
				}
				
				low = Math.min(low, lowChild);
			} else {
				low = Math.min(low, visited[next]); // *핵심*
			}
		}
		
		if (isRoot && child >= 2) {
			isCut[now] = true;
		}
		
		return low;
	}
}
