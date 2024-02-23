import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[] a;
	static int[][] dp;
	static final int INF = Integer.MAX_VALUE;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		a = new int[n + 1];
		dp = new int[n + 1][n + 1];
		visited = new boolean[n + 1][n + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= n; i++) {
			Arrays.fill(dp[i], INF);
		}
		
		bw.write(String.valueOf(getResult(1, n)));
		
		br.close();
		bw.close();
	}
	
	
	static int getResult(int i, int j) {
		if (i == j) {
			return 0;
		}
		
		if (dp[i][j] != INF) {
			return dp[i][j];
		} 

		for (int k = i; k < j; k++) {
			if (a[i] != a[k+1]) {
				dp[i][j] = Math.min(dp[i][j], getResult(i, k) + getResult(k+1, j) + 1);
			} else {
				dp[i][j] = Math.min(dp[i][j], getResult(i, k) + getResult(k+1, j));
			}
		}
		
		return dp[i][j];
	}
}
