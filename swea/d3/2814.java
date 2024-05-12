import java.io.*;
import java.util.*;

public class Solution {

    static int t;
    static int n, m;
    static List<Integer>[] adjs;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= t; testCase++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            answer = 1;
            visited = new boolean[n + 1];
            adjs = new List[n + 1];
            for (int i = 1; i <= n; i++) {
                adjs[i] = new ArrayList<>();
            }

            int start, end;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());
                adjs[start].add(end);
                adjs[end].add(start);
            }

            for (int i = 1; i <= n; i++) {
                visited[i] = true;
                dfs(i, 1, 0);
                visited[i] = false;
            }

            bw.write("#" + testCase + " " + answer + "\n");
        }

        br.close();
        bw.close();
    }

    static void dfs(int start, int step, int depth) {
        answer = Math.max(answer, step);

        if (depth == n) return;

        for (int i = 0; i < adjs[start].size(); i++) {
            int next = adjs[start].get(i);
            if (visited[next]) continue;

            visited[next] = true;
            dfs(next, step + 1, depth + 1);
            visited[next] = false;
        }
    }
}