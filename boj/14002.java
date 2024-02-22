import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] nums;
	static int[] dp;
	static int lis = 1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		
		nums = new int[n + 1];
		dp = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			dp[i] = 1;
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
					lis = Math.max(lis, dp[i]);
				}
			}
		}
		
		List<Integer> answers = new ArrayList<>();
		for (int i = n; i >= 1; i--) {
			if (dp[i] == lis) {
				answers.add(nums[i]);
				lis--;
			}
		}

		bw.write(String.valueOf(answers.size()));
		bw.newLine();
		for (int i = answers.size() - 1; i >= 0; i--) {
			bw.write(answers.get(i) + " ");
		}
		
		br.close();
		bw.close();
	}
}
