import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[] parent;
	static int[] weight;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			if (n == 0 && m == 0) {
				break;
			}
			
			parent = new int[n + 1];
			weight = new int[n + 1];
			
			init();
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				String option = st.nextToken();
				
				if (option.equals("!")) {
					// 무게 재기 (union)
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					int w = Integer.parseInt(st.nextToken());
					
					union(a, b, w);
					
				} else if (option.equals("?")) {
					// 교수님의 질문 (find)
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());

					if (find(a) != find(b)) {
						bw.write("UNKNOWN\n");
					} else {
						bw.write(String.valueOf(weight[b] - weight[a]));
						bw.newLine();
					}
				}
			}
		}
		
		br.close();
		bw.close();
	}
	
	static void init() {
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
	}
	
	static int find(int num) {
		if (parent[num] == num) {
			return num;
		} else {
			int parentId = find(parent[num]);
			weight[num] += weight[parent[num]];
			return parent[num] = parentId;
		}
	}
	
	static void union(int a, int b, int w) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA > rootB) {
			int temp = weight[b] - w;
			weight[rootA] = temp - weight[a];
			parent[rootA] = rootB;
		} else {
			int temp = weight[a] + w;
			weight[rootB] = temp - weight[b];
			parent[rootB] = rootA;
		}
	}
}