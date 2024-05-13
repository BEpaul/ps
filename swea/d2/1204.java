import java.io.*;
import java.util.*;

public class Solution {

    static int t;
    static int[] students;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= t; testCase++) {
            int tNum = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            students = new int[101];
            for (int i = 0; i < 1000; i++) {
                students[Integer.parseInt(st.nextToken())]++;
            }

            int answer = 0;
            int max = 0;
            for (int i = 0; i < 101; i++) {
                if (students[i] >= max) {
                    max = students[i];
                    answer = i;
                }
            }

            bw.write("#" + testCase + " " + answer + "\n");
        }

        br.close();
        bw.close();
    }
}