import java.io.*;
import java.util.*;

public class Solution {

    static int t;
    static int n, m, k;
    static int[] arrives;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= t; testCase++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arrives = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arrives[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arrives);

            int idx = 0;
            boolean isPossible = true;
            for (int i = 0; i < n; i++) {
                int time = arrives[i];
                int bread = k * (time / m);
                if (i + 1 > bread) {
                    isPossible = false;
                    break;
                }
            }

            if (!isPossible) {
                bw.write("#" + testCase + " Impossible\n");
            } else {
                bw.write("#" + testCase + " Possible\n");
            }
        }

        br.close();
        bw.close();
    }
}