import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static String[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        arr = new String[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = st.nextToken();
        }

        Arrays.sort(arr, (x, y) -> (y + x).compareTo(x + y));

        for (String s : arr) {
            sb.append(s);
        }

        if (sb.charAt(0) == '0') {
            bw.write("0");
        } else {
            bw.write(sb.toString());
        }

        br.close();
        bw.close();
    }
}