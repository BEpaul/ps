import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    static int n;
    static int number;
    static PriorityQueue<Integer> minPQ = new PriorityQueue<>();
    static PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            number = Integer.parseInt(br.readLine());

            if (maxPQ.size() == minPQ.size()) {
                maxPQ.add(number);
            } else {
                minPQ.add(number);
            }

            if (!maxPQ.isEmpty() && !minPQ.isEmpty()) {
                if (minPQ.peek() < maxPQ.peek()) {
                    minPQ.add(maxPQ.poll());
                    maxPQ.add(minPQ.poll());
                }
            }
            bw.write(maxPQ.peek() + "\n");
        }
        
        br.close();
        bw.close();
    }
}
