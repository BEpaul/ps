import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] indegree;
    static List<Integer>[] buildings;
    static int[] timeCosts;
    static int[] results;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        buildings = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            buildings[i] = new ArrayList<>();
        }

        indegree = new int[n + 1];
        timeCosts = new int[n + 1];
        results = new int[n + 1];

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            timeCosts[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int precededBuilding = Integer.parseInt(st.nextToken());
                if (precededBuilding == -1) {
                    break;
                }
                buildings[precededBuilding].add(i);
                indegree[i]++;
            }
        }

        topologicalSort();

        for (int i = 1; i <= n; i++) {
            bw.write(String.valueOf(results[i]));
            bw.newLine();
        }

        bw.flush();

        br.close();
        bw.close();

    }

    static void topologicalSort() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
            results[i] = timeCosts[i];
        }

        int now;
        while (!queue.isEmpty()) {
            now = queue.poll();

            for (int next : buildings[now]) {
                indegree[next]--;
                results[next] = Math.max(results[next], results[now] + timeCosts[next]);
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
    }
}