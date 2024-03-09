import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    static int n;
    static int[][] houses;
    static int[][] dp;
    static int answer = INF;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        houses = new int[n][3];
        dp = new int[n][3];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                houses[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = houses[0][0];
        dp[0][1] = INF;
        dp[0][2] = INF;

        for (int i = 1; i < n; i++) {
            dp[i][0] = houses[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = houses[i][1] + Math.min(dp[i - 1][2], dp[i - 1][0]);
            dp[i][2] = houses[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        answer = min(answer, dp[n - 1][1], dp[n - 1][2]);

        dp[0][0] = INF;
        dp[0][1] = houses[0][1];
        dp[0][2] = INF;

        for (int i = 1; i < n; i++) {
            dp[i][0] = houses[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = houses[i][1] + Math.min(dp[i - 1][2], dp[i - 1][0]);
            dp[i][2] = houses[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        answer = min(answer, dp[n - 1][2], dp[n - 1][0]);

        dp[0][0] = INF;
        dp[0][1] = INF;
        dp[0][2] = houses[0][2];

        for (int i = 1; i < n; i++) {
            dp[i][0] = houses[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = houses[i][1] + Math.min(dp[i - 1][2], dp[i - 1][0]);
            dp[i][2] = houses[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        answer = min(answer, dp[n - 1][0], dp[n - 1][1]);

        bw.write(String.valueOf(answer));

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