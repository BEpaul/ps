import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int w, h;
    static int map[][];
    static boolean visited[][];
    static final int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
    static final int[] dy = {0, 0, -1, 1, -1, -1, 1, 1};
    static Queue<Node> queue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            map = new int[h][w];
            visited = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            queue = new LinkedList<>();

            long count = 0;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        bfs(i, j);
                        count++;
                    }
                }
            }

            bw.write(String.valueOf(count));
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    static void bfs(int y, int x) {
        visited[y][x] = true;
        queue.add(new Node(y, x));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int curY = node.y;
            int curX = node.x;

            int nextX, nextY;
            for (int i = 0; i < 8; i++) {
                nextY = curY + dy[i];
                nextX = curX + dx[i];

                if (nextX >= 0 && nextX < w && nextY >= 0 && nextY < h) {
                    if (!visited[nextY][nextX] && map[nextY][nextX] == 1) {
                        visited[nextY][nextX] = true;
                        queue.add(new Node(nextY, nextX));
                    }
                }
            }
        }
    }
}

class Node {
    int y;
    int x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public String toString() {
        return "Node{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }
}