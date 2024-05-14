import java.io.*;
import java.util.*;

public class Solution {
    static int t;
    static int[] S, D, H, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= t; testCase++) {
            S = new int[14];
            D = new int[14];
            H = new int[14];
            C = new int[14];

            String input = br.readLine();
            int iter = input.length() / 3;
            String[] cards = new String[iter];
            for (int i = 0; i < iter; i++) {
                cards[i] = input.substring(3 * i, 3 * (i + 1));
            }

            boolean isError = false;
            for (String card : cards) {
                char type = card.charAt(0);
                int num = Integer.parseInt(card.substring(1, 3));

                if (type == 'S') {
                    if (S[num] == 1) isError = true;
                    S[num]++;
                } else if (type == 'D') {
                    if (D[num] == 1) isError = true;
                    D[num]++;
                } else if (type == 'H') {
                    if (H[num] == 1) isError = true;
                    H[num]++;
                } else {
                    if (C[num] == 1) isError = true;
                    C[num]++;
                }

                if (isError) break;
            }

            if (isError) bw.write("#" + testCase + " ERROR\n");
            else {
                int[] answer = new int[4];
                for (int i = 1; i <= 13; i++) {
                    if (S[i] == 0) answer[0]++;
                    if (D[i] == 0) answer[1]++;
                    if (H[i] == 0) answer[2]++;
                    if (C[i] == 0) answer[3]++;
                }

                bw.write("#" + testCase + " " + answer[0] + " " + answer[1] + " " + answer[2] + " " + answer[3]
                        + "\n");
            }
        }

        br.close();
        bw.close();
    }
}