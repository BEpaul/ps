import java.io.*;
import java.util.*;

public class Solution {
    static int t;
    static int n, k;
    static int[] v, c;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= t; testCase++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            v = new int[n + 1];
            c = new int[n + 1];
            dp = new int[n + 1][k + 1];

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                v[i] = Integer.parseInt(st.nextToken());
                c[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= k; j++) {
                    if (j >= v[i]) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - v[i]] + c[i]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            int answer = 0;
            for (int i = 1; i <= n; i++) {
                answer = Math.max(answer, dp[i][k]);
            }

            bw.write("#" + testCase + " " + answer + "\n");
        }

        br.close();
        bw.close();
    }
}