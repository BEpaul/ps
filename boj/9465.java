import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] stickers;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < t; testCase++) {
            n = Integer.parseInt(br.readLine());
            stickers = new int[2][n];
            dp = new int[2][n];

            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (n == 1) {
                bw.write(Math.max(stickers[0][0], stickers[1][0]) + "\n");
                bw.flush();
                continue;
            }

            dp[0][0] = stickers[0][0];
            dp[1][0] = stickers[1][0];
            dp[0][1] = stickers[1][0] + stickers[0][1];
            dp[1][1] = stickers[0][0] + stickers[1][1];

            for (int i = 0; i < 2; i++) {
                for (int j = 2; j < n; j++) {
                    dp[0][j] = stickers[0][j] + Math.max(dp[1][j - 1], dp[1][j - 2]);
                    dp[1][j] = stickers[1][j] + Math.max(dp[0][j - 1], dp[0][j - 2]);
                }
            }

            bw.write(Math.max(dp[0][n - 1], dp[1][n - 1]) + "\n");
            bw.flush();
        }

        br.close();
        bw.close();
    }
}