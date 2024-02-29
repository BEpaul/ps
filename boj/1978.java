import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] primes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        primes = new int[1001];

        Arrays.fill(primes, 1);
        primes[0] = 0;
        primes[1] = 0;

        for (int i = 2; i * i <= 1000; i++) {
            if (primes[i] == 0) continue;
            for (int j = i * i; j <= 1000; j += i) {
                primes[j] = 0;
            }
        }

        int count = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            if (primes[Integer.parseInt(st.nextToken())] == 1) count++;
        }

        bw.write(String.valueOf(count));
        br.close();
        bw.close();
    }
}
