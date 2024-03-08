import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        dp = new int[n][3];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = dp[i][1] + Math.min(dp[i - 1][2], dp[i - 1][0]);
            dp[i][2] = dp[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        bw.write(String.valueOf(min(dp[n - 1][0], dp[n - 1][1], dp[n - 1][2])));

        br.close();
        bw.close();
    }

    static int min(int a, int b, int c) {
        int res = a;
        if (res > b) res = b;
        if (res > c) res = c;

        return res;
    }
}