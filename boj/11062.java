import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int t, n;
	static int[] a;
	static int[] sums;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		t = Integer.parseInt(br.readLine());
		for (int iter = 0; iter < t; iter++) {
			n = Integer.parseInt(br.readLine());
			
			a = new int[n + 1];
			sums = new int[n + 1];
			dp = new int[n + 1][n + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				a[i] = Integer.parseInt(st.nextToken());
				sums[i] = sums[i-1] + a[i];
			}
			
			bw.write(String.valueOf(select(1, n)));
			bw.newLine();
		}
		
		br.close();
		bw.close();
	}
	
	static int select(int left, int right) {
		if (dp[left][right] != 0) {
			return dp[left][right];
		}
		
		if (left == right) {
			return a[left];
		} else {
			int sum = sums[right] - sums[left-1];
			return dp[left][right] = sum - Math.min(select(left+1, right), select(left, right-1));
		}
	}
}
