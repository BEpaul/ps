import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static String a, b;
	static int[][] dp;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		a = br.readLine();
		b = br.readLine();
		
		dp = new int[b.length() + 1][a.length() + 1];

		for (int i = 1; i <= b.length(); i++) {
			for (int j = 1; j <= a.length(); j++) {
				if (b.charAt(i-1) == a.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
					ans = Math.max(ans, dp[i][j]);
				}
			}
		}
		
		bw.write(String.valueOf(ans));
		
		br.close();
		bw.close();
	}
}