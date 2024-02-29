import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int n, m;
    static int[] cost, memory;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cost = new int[n + 1];
        memory = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            sum += cost[i];
        }


        dp = new int[n + 1][sum + 1];
        int answer = INF;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j - cost[i] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cost[i]] + memory[i]);
                }
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);

                if (dp[i][j] >= m) {
                    answer = Math.min(j, answer);
                }
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
