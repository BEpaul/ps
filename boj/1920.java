import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] A;
    static int n, m;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        A = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int result = isNumber(Integer.parseInt(st.nextToken()));
            System.out.println(result);
        }
    }

    static int isNumber(int num) {
        int start = 0, end = n - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (A[mid] > num) {
                end = mid - 1;
            } else if (A[mid] < num) {
                start = mid + 1;
            } else {
                return 1;
            }
        }

        return 0;
    }
}