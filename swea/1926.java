import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            String s = Integer.toString(i);
            int count = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '3' || s.charAt(j) == '6' || s.charAt(j) == '9') count++;
            }

            if (count > 0) {
                for (int j = 0; j < count; j++) {
                    bw.write("-");
                }
            } else {
                bw.write(String.valueOf(i));
            }

            bw.write(" ");
        }

        br.close();
        bw.close();
    }
}