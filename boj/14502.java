import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int n, m;
    static int[][] map;
    static int[][] wallMap;
    static int safetyZone;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        wallMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        bw.write(String.valueOf(safetyZone));
        br.close();
        bw.close();
    }

    static void dfs(int count) {
        if (count == 3) {
            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(count + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    static void bfs() {
        Queue<Virus> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    queue.add(new Virus(i, j));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            wallMap[i] = map[i].clone(); // 깊은 복사
        }

        while (!queue.isEmpty()) {
            Virus virus = queue.poll();
            
            if (wallMap[virus.x][virus.y] == 0) {
                wallMap[virus.x][virus.y] = 2;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = virus.x + dx[i];
                int nextY = virus.y + dy[i];

                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m) {
                    if (wallMap[nextX][nextY] == 0) {
                        wallMap[nextX][nextY] = 2;
                        queue.add(new Virus(nextX, nextY));
                    }
                }
            }
        }
        countVirus();
    }

    static void countVirus() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (wallMap[i][j] == 0) {
                    cnt++;
                }
            }
        }

        safetyZone = Math.max(safetyZone, cnt);
    }
}

class Virus {
    int x;
    int y;

    public Virus(int x, int y) {
        this.x = x;
        this.y = y;
    }
}