import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] techniques;
    static int[] cards;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        techniques = new int[n];
        cards = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            techniques[i] = Integer.parseInt(st.nextToken());
        }

        int first = 0;
        int second = 1;
        int last = n - 1;
        int value = n;
        for (int technique : techniques) {
            if (technique == 1) {
                cards[first] = value;
                first = second;
                second++;
            } else if (technique == 2) {
                cards[second++] = value;
            } else {
                cards[last--] = value;
            }

            value--;
        }

        for (int card : cards) {
            bw.write(card + " ");
        }

        br.close();
        bw.close();
    }
}