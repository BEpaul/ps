import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int n, m;
    static int[][] map;
    static long answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r, c, d);
        
        countCleaningSpace();

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    static void dfs(int r, int c, int d) {
        map[r][c] = 2;
        int nextX, nextY;
        boolean isRoute = false;
            // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는지 없는지 체크
            for (int i = 0; i < 4; i++) {
                nextX = r + dx[i];
                nextY = c + dy[i];

                if (map[nextX][nextY] == 0) {
                    isRoute = true;
                }
            }

            if (isRoute) { // 빈칸이 있는 경우 -> 앞으로 갈 수 있을 때까지 반시계방향으로 90도 회전 후 가기
                while (true) {
                    if (d == 0) {
                        d = 3;
                    } else {
                        d--;
                    }

                    nextX = r + dx[d];
                    nextY = c + dy[d];
                    if (map[nextX][nextY] == 0) {
                        dfs(nextX, nextY, d);
                        break;
                    }
                }
            } else { // 빈칸이 없는 경우 -> 후진
                if (d == 0) {
                    if (map[r + 1][c] != 1) {
                        dfs(r + 1, c, d);
                    }
                } else if (d == 1) {
                    if (map[r][c - 1] != 1) {
                        dfs(r, c - 1, d);
                    }
                } else if (d == 2) {
                    if (map[r - 1][c] != 1) {
                        dfs(r - 1, c, d);
                    }
                } else {
                    if (map[r][c + 1] != 1) {
                        dfs(r, c + 1, d);
                    }
                }
            }
    }

    static void countCleaningSpace() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    answer++;
                }
            }
        }
    }
}