import java.io.*;
import java.util.*;

public class Solution {

    static int t;
    static int n, l;
    static int[] favors;
    static int[] kcals;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= t; testCase++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            favors = new int[n + 1];
            kcals = new int[n + 1];
            dp = new int[n + 1][10001];

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                favors[i] = Integer.parseInt(st.nextToken());
                kcals[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= 10000; j++) {
                    if (j >= kcals[i]) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - kcals[i]] + favors[i]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            int answer = 0;
            for (int i = 1; i <= n; i++) {
                answer = Math.max(answer, dp[i][l]);
            }

            bw.write("#" + testCase + " " + answer + "\n");
        }


        br.close();
        bw.close();
    }
}