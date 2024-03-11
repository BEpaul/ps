import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] A, B, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        A = new int[n];
        B = new int[m];
        C = new int[n + m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int i = 0;
        int j = 0;
        int k = 0;
        while (true) {
            if (i < n && j < m) {
                if (A[i] >= B[j]) {
                    C[k] = B[j];
                    j++;
                } else {
                    C[k] = A[i];
                    i++;
                }
            } else if (i < n&& j == m) {
                C[k] = A[i];
                i++;
            } else {
                C[k] = B[j];
                j++;
            }

            k++;

            if (i == n && j == m) {
                break;
            }
        }

        for (int c : C) {
            bw.write(c + " ");
        }

        br.close();
        bw.close();
    }
}