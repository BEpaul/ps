import java.io.*;
import java.util.*;

public class Solution {

    static int t;
    static int n;
    static int[] prices;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= t; testCase++) {
            n = Integer.parseInt(br.readLine());
            prices = new int[n];

            long answer = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            int max = -1;
            for (int i = n - 1; i >= 0; i--) {
                if (max >= prices[i]) {
                    answer += max - prices[i];
                } else {
                    max = prices[i];
                }
            }

            bw.write("#" + testCase + " " + answer + "\n");
        }

        br.close();
        bw.close();
    }
}