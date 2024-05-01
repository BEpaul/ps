import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int dasom;
    static PriorityQueue<Integer> votes = new PriorityQueue<>(Comparator.reverseOrder());
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        dasom = Integer.parseInt(br.readLine());

        for (int i = 0; i < n - 1; i++) {
            votes.offer(Integer.parseInt(br.readLine()));
        }

        while (!votes.isEmpty()) {
            if (dasom > votes.peek()) break;

            int vote = votes.poll();
            if (vote > 1) {
                answer++;
                dasom++;
                votes.offer(--vote);
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}