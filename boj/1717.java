import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] set;
	static int option, a, b;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		set = new int[n + 1];
		
		init();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			option = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if (option == 0) {
				// union
				union(a, b);
				
			} else if (option == 1) {
				// find
				if (find(a) == find(b)) {
					bw.write("YES\n");
				} else {
					bw.write("NO\n");
				}
				
			}
		}
		
		br.close();
		bw.close();
	}
	
	static void init() {
		for (int i = 1; i <= n; i++) {
			set[i] = i;
		}
	}
	
	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		set[aRoot] = bRoot;
	}
	
	static int find(int num) {
		if (set[num] == num) {
			return num;
		} else {
			return set[num] = find(set[num]);
		}
	}
}
