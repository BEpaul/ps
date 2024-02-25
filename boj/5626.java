import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static final int MODULO = 1000000007;
    static int n;
    static int[] a;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = new int[n + 1];
        dp = new long[2][5003];

        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        if (a[1] == -1) {
            dp[0][0] = 1;
        } else if (a[1] == 0) {
            dp[0][0] = 1;
        } else {
            dp[0][0] = 0;
        }

        for (int i = 2; i <= n; i++) {
            if (a[i] == -1) {
                dp[1][0] = (dp[0][0] + dp[0][1]) % MODULO;
                for (int j = 1; j <= n / 2; j++) {
                    dp[1][j] = (dp[0][j - 1] + dp[0][j] + dp[0][j + 1]) % MODULO;
                }
            } else if (a[i] == 0) {
                dp[1][0] = (dp[0][0] + dp[0][1]) % MODULO;
            } else {
                dp[1][a[i]] = (dp[0][a[i] - 1] + dp[0][a[i]] + dp[0][a[i] + 1]) % MODULO;
            }

            for (int j = 0; j <= n / 2; j++) {
                dp[0][j] = dp[1][j];
                dp[1][j] = 0;
            }
        }

        bw.write(String.valueOf(dp[0][0]));
        br.close();
        bw.close();
    }
}