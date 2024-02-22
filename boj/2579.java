import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int n;
	static int[] stairs;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		
		stairs = new int[n + 1];
		dp = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		
		if (n == 1) {
			bw.write(String.valueOf(stairs[1]));
		} else if (n == 2) {
			bw.write(String.valueOf(stairs[1] + stairs[2]));
		} else {
			dp[1] = stairs[1];
			dp[2] = stairs[1] + stairs[2];
			
			for (int i = 3; i <= n; i++) {
				dp[i] = Math.max(dp[i-3] + stairs[i-1], dp[i-2]) + stairs[i];
			}

			bw.write(String.valueOf(dp[n]));
		}
		
		br.close();
		bw.close();
	}
}