import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static int n, m, k;
    static int[][] map;
    static int count;

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static List<Integer> results;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        results = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = y1; j < y2; j++) {
                for (int k = x1; k < x2; k++) {
                    map[j][k] = 1;
                }
            }
        }

        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    count = 0;
                    dfs(i, j);
                    results.add(count);
                }
            }
        }

        Collections.sort(results);
        System.out.println(results.size());

        for (int i = 0; i < results.size(); i++) {
            System.out.print(results.get(i));
            if (i != results.size() - 1) {
                System.out.print(" ");
            }
        }
    }

    static void dfs(int x, int y) {
        map[x][y] = 1;
        count++;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (0 <= nextX && nextX < n && 0 <= nextY && nextY < m) {
                if (map[nextX][nextY] == 0) {
                    dfs(nextX, nextY);
                }
            }
        }
    }
}