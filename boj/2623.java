import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] indegree;
    static List<Integer>[] adjs;
    static Queue<Integer> queue;
    static int[] answer;
    static int index = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        answer = new int[n + 1];
        indegree = new int[n + 1];
        adjs = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adjs[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            String[] split = br.readLine().split(" ");
            int orderCount = Integer.parseInt(split[0]);
            for (int j = 1; j <= orderCount - 1; j++) {
                adjs[Integer.parseInt(split[j])].add(Integer.parseInt(split[j + 1]));
                indegree[Integer.parseInt(split[j + 1])]++;
            }
        }

        topologicalSort();

        if (answer[n] == 0) {
            bw.write("0");
        } else {
            for (int i = 1; i <= n; i++) {
                bw.write(answer[i] + "\n");
            }
        }

        br.close();
        bw.close();
    }

    static void topologicalSort() {
        queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                answer[index] = i;
                index++;
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i = 0; i < adjs[now].size(); i++) {
                int next = adjs[now].get(i);

                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                    answer[index] = next;
                    index++;
                }
            }
        }
    }
}