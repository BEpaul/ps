import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static String s1, s2;
	static int n, m;
	static int[][] dp;
	static String lcs = "";
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		s1 = br.readLine();
		s2 = br.readLine();
		
		n = s2.length();
		m = s1.length();
		
		dp = new int[n + 1][m + 1];
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (s1.charAt(j-1) == s2.charAt(i-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		findLCS(n, m);
		
		String reversedLcs = sb.toString();
		bw.write(String.valueOf(reversedLcs.length()));
		bw.newLine();
		if (reversedLcs.length() != 0) {
			for (int i = reversedLcs.length() - 1; i >= 0; i--) {
				bw.write(reversedLcs.charAt(i));
			}
		}
		
		br.close();
		bw.close();
		
	}
	
	static void findLCS(int x, int y) {
		
		while (true) {
			if (s2.charAt(x-1) == s1.charAt(y-1)) {
				sb.append(s2.charAt(x-1));
				x -= 1;
				y -= 1;
			} else if (dp[x][y] == dp[x][y-1]) {
				y -= 1;
			} else {
				x -= 1;
			}
			
			if (x == 0 || y == 0) {
				break;
			}
		}
	}
}