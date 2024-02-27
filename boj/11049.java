import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;

    static int n;
    static int[] r, c;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        r = new int[n + 1];
        c = new int[n + 1];
        dp = new int[n + 1][n + 1];

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            r[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(getResult(1, n)));

        br.close();
        bw.close();
    }

    static int getResult(int i, int j) {
        if (i == j) {
            return 0;
        }

        int res = dp[i][j];
        if (res == 0) {
            res = INF;
            for (int k = i; k < j; k++) {
                res = Math.min(getResult(i, k) + getResult(k + 1, j) + r[i] * c[k] * c[j], res);
            }
        }

        return dp[i][j] = res;
    }
}