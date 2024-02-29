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
    static boolean[][] visited;
    static Queue<Node> queue;
    static int day;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    queue.add(new Node(i, j, day));
                }
            }
        }

        bfs();

        if (!checkStatus()) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(day));
        }

        br.close();
        bw.close();
    }

    static void bfs() {
        Node now;
        while (!queue.isEmpty()) {
            now = queue.poll();
            visited[now.x][now.y] = true;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m) {
                    if (!visited[nextX][nextY] && map[nextX][nextY] == 0) {
                        visited[nextX][nextY] = true;
                        map[nextX][nextY] = 1;
                        day = now.day + 1;
                        queue.add(new Node(nextX, nextY, day));
                    }
                }
            }
        }
    }

    static boolean checkStatus() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }
}

class Node {
    int x;
    int y;
    int day;

    public Node(int x, int y, int day) {
        this.x = x;
        this.y = y;
        this.day = day;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", day=" + day +
                '}';
    }
}