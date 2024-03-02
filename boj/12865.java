import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int n, k;
    static int[] weights, values;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        weights = new int[n + 1];
        values = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n + 1][100001];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 100000; j++) {
                if (j >= weights[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i-1][j - weights[i]] + values[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(dp[i][k], answer);
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}