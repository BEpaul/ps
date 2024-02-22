import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[][] a;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		a = new int[n + 1][m + 1];
		dp = new int[n + 1][m + 1];
		
		int ans = 0;
		String[] s;
		for (int i = 1; i <= n; i++) {
			s = br.readLine().split("");
			for (int j = 1; j <= m; j++) {
				a[i][j] = Integer.parseInt(s[j-1]);
				if (a[i][j] == 1) {
					dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1;
					ans = Math.max(ans, dp[i][j]);
				}
			}
		}
		
		bw.write(String.valueOf(ans*ans));
		
		br.close();
		bw.close();
	}
	
	static int min(int a, int b, int c) {
		a = a < b ? a : b;
		return a < c? a : c;
	}
}
