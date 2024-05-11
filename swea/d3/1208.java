import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static int[] boxes = new int[101];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        for (int testCase = 1; testCase <= 10; testCase++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= 100; i++) {
                boxes[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < n; i++) {
                int[] max = getMax();
                int[] min = getMin();

                if (max[0] - min[0] <= 1) break;

                boxes[max[1]]--;
                boxes[min[1]]++;
            }

            int[] maxResult = getMax();
            int[] minResult = getMin();
            bw.write("#" + testCase + " " + (maxResult[0] - minResult[0]) + "\n");
        }

        br.close();
        bw.close();
    }

    static int[] getMax() {
        int max = -1;
        int index = 1;
        for (int i = 1; i <= 100; i++) {
            if (boxes[i] > max) {
                max = boxes[i];
                index = i;
            }
        }
        
        int[] ret = {max, index};
        return ret;
    }

    static int[] getMin() {
        int min = 101;
        int index = 1;
        for (int i = 1; i <= 100; i++) {
            if (boxes[i] < min) {
                min = boxes[i];
                index = i;
            }
        }

        int[] ret = {min, index};
        return ret;
    }
}