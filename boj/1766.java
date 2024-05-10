import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] indegree;
    static List<Integer>[] adjs;
    static int[] answer;
    static int index = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        indegree = new int[n + 1];
        answer = new int[n + 1];
        adjs = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adjs[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjs[a].add(b);
            indegree[b]++;
        }

        topologicalSort();

        for (int i = 1; i <= n; i++) {
            bw.write(answer[i] + " ");
        }

        br.close();
        bw.close();
    }

    static void topologicalSort() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int now = pq.poll();
            answer[index] = now;
            index++;

            for (int i = 0; i < adjs[now].size(); i++) {
                int next = adjs[now].get(i);
                indegree[next]--;

                if (indegree[next] == 0) {
                    pq.offer(next);
                }
            }
        }
    }
}