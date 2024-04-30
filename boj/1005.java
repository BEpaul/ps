import java.io.*;
import java.util.*;

public class Main {
    static int t, n, k;
    static int[] indegree;
    static List<Integer>[] buildings;
    static int[] timeCosts;
    static long[] results;
    static int target;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());
        for (int testCase = 1; testCase <= t; testCase++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            indegree = new int[n + 1];
            timeCosts = new int[n + 1];
            results = new long[n + 1];
            buildings = new List[n + 1];

            for (int i = 1; i <= n; i++) {
                buildings[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                timeCosts[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int preB = Integer.parseInt(st.nextToken());
                int postB = Integer.parseInt(st.nextToken());

                buildings[preB].add(postB);
                indegree[postB]++;
            }

            st = new StringTokenizer(br.readLine());
            target = Integer.parseInt(st.nextToken());

            topologicalSort();

            bw.write(results[target] + "\n");
        }

        br.close();
        bw.close();
    }

    static void topologicalSort() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }

            results[i] = timeCosts[i];
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i = 0; i < buildings[now].size(); i++) {
                int next = buildings[now].get(i);

                indegree[next]--;
                results[next] = Math.max(results[next], results[now] + timeCosts[next]);

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
    }
}