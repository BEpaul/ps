import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] indegree, counts;
    static List<Integer>[] classes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        indegree = new int[n + 1];
        classes = new List[n + 1];
        counts = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            classes[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            classes[a].add(b);
            indegree[b]++;
        }

        topologicalSort();

        for (int i = 1; i <= n; i++) {
            bw.write(counts[i] + " ");
        }

        br.close();
        bw.close();
    }

    static void topologicalSort() {
        Queue<Class> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) queue.offer(new Class(i, 1));

            counts[i] = 1;
        }

        while (!queue.isEmpty()) {
            Class now = queue.poll();
            for (int i = 0; i < classes[now.num].size(); i++) {
                int next = classes[now.num].get(i);
                indegree[next]--;

                counts[next] = Math.max(counts[next], now.order + 1);
                if (indegree[next] == 0) queue.offer(new Class(next, now.order + 1));
            }
        }
    }
}

class Class {
    int num, order;

    public Class(int num, int order) {
        this.num = num;
        this.order = order;
    }

    @Override
    public String toString() {
        return "Class{" +
                "num=" + num +
                ", order=" + order +
                '}';
    }
}