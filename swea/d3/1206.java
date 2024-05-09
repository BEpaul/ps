import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static int[] heights;
    static int[] maxH;
    static long answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int testCase = 1; testCase <= 10; testCase++) {
            n = Integer.parseInt(br.readLine());
            heights = new int[n];
            maxH = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }


            for (int i = 2; i < n - 2; i++) {
                maxH[i] = max(heights[i - 2], heights[i - 1], heights[i + 1], heights[i + 2]);
                if (heights[i] > maxH[i]) answer += heights[i] - maxH[i];
            }

            bw.write("#" + testCase + " " + answer + "\n");
            answer = 0;
        }

        br.close();
        bw.close();
    }

    static int max(int a, int b, int c, int d) {
        int ret = a;
        if (ret < b) ret = b;
        if (ret < c) ret = c;
        if (ret < d) ret = d;

        return ret;
    }
}