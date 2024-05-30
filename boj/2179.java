import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static String[] arr;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        int a = -1;
        int b = -1;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int match = compare(i, j);
                if (match > max) {
                    max = match;
                    a = i;
                    b = j;
                }
            }
        }

        bw.write(arr[a] + "\n");
        bw.write(arr[b]);

        br.close();
        bw.close();
    }

    static int compare(int a, int b) {
        int length = Math.min(arr[a].length(), arr[b].length());
        int matchCount = 0;
        for (int i = 0; i < length; i++) {
            if (arr[a].charAt(i) != arr[b].charAt(i)) break;
            matchCount++;
        }

        return matchCount;
    }
}