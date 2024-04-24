import java.io.*;
import java.util.*;

/**
 * [구현 순서]
 * 0. 입력 받기: map[n][n], people[m + 1], convenience[m + 1]
 * 1. 3번 구현: t <= m일 때, 편의점과 가장 가까운 베이스 캠프에 들어가기 -> 편의점에서 BFS 해서 step을 찍어서 가장 가까운 편의점 찾기
 *            이때, 만약 동일한 거리라면 행-열 순으로 우선순위.
 * 2. 해당 베이스캠프에 사람을 넣고 벽 처리한다.
 * 3. 1번 구현: 해당 사람에 배정되어 있는 편의점으로 걸어간다. 이때, 편의점에서 BFS 돌려서 가까운 거리로 간다.
 * 4. 2번 구현: 편의점에 도착하면 해당 편의점에서 멈추고, 해당 편의점을 벽 처리한다.
 *
 * [Remind]
 * - BFS에서 탐색 순서 [북 서 동 남]으로 하기
 *
 */

public class Main {
    static final int[] dx = {-1, 0, 0, 1}; // 북 서 동 남
    static final int[] dy = {0, -1, 1, 0};

    static int n, m;
    static int[][] map;
    static Pair[] people;
    static Pair[] convenienceStores;
    static boolean[][] visited;
    static int[][] step;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        people = new Pair[m + 1];
        convenienceStores = new Pair[m + 1];
        visited = new boolean[n][n];
        step = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int cx = Integer.parseInt(st.nextToken());
            int cy = Integer.parseInt(st.nextToken());
            convenienceStores[i] = new Pair(cx - 1, cy - 1);
            people[i] = new Pair(-1, -1);
        }

        int t = 1;
        while (true) {
            move();
            checkArrivingCS();
            if (t <= m) {
                getNearBaseCamp(t);
            }

            if (isDone()) {
                break;
            }

            t++;
        }

        bw.write(String.valueOf(t));
        br.close();
        bw.close();
    }

    static void move() {
        for (int i = 1; i <= m; i++) {
            // 만약 해당 사람이 출발하지 않았거나 편의점에 도착했다면 continue
            if (people[i].x == -1 && people[i].y == -1) continue;
            if (people[i].x == convenienceStores[i].x && people[i].y == convenienceStores[i].y) continue;

            Pair nextPos = getNextPos(i);
            people[i].x = nextPos.x;
            people[i].y = nextPos.y;
        }
    }

    static void checkArrivingCS() {
        for (int i = 1; i <= m; i++) {
            if (people[i].x == convenienceStores[i].x && people[i].y == convenienceStores[i].y) {
                map[people[i].x][people[i].y] = -1;
            }
        }
    }

    static Pair getNextPos(int num) {
        bfs(num);

        Pair pair = new Pair(-1, -1);

        int minDist = Integer.MAX_VALUE;
        int minX = -1;
        int minY = -1;

        for (int i = 0; i < 4; i++) {
            int nx = people[num].x + dx[i];
            int ny = people[num].y + dy[i];

            if (inRange(nx, ny) && visited[nx][ny] && minDist > step[nx][ny]) {
                minDist = step[nx][ny];
                minX = nx;
                minY = ny;
            }
        }

        pair.x = minX;
        pair.y = minY;

        initVisited();
        initStep();

        return pair;
    }

    static void getNearBaseCamp(int num) {
        bfs(num);

        // 2. 해당 베이스캠프에 사람을 넣고 벽 처리한다.
        fillBaseCamp(num);

        initVisited();
        initStep();
    }

    private static void bfs(int num) {
        Queue<Pair> queue = new ArrayDeque<>();

        int startX = convenienceStores[num].x;
        int startY = convenienceStores[num].y;

        queue.offer(new Pair(startX, startY));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            visited[pair.x][pair.y] = true;
            for (int i = 0; i < 4; i++) {
                int nextX = pair.x + dx[i];
                int nextY = pair.y + dy[i];

                if (inRange(nextX, nextY) && map[nextX][nextY] != -1 && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    step[nextX][nextY] = step[pair.x][pair.y] + 1;
                    queue.offer(new Pair(nextX, nextY));
                }
            }
        }
    }

    static void fillBaseCamp(int num) {
        int minDist = Integer.MAX_VALUE;
        int minX = -1;
        int minY = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && visited[i][j] && minDist > step[i][j]) {
                    minDist = step[i][j];
                    minX = i;
                    minY = j;
                }
            }
        }

        people[num].x = minX;
        people[num].y = minY;
        map[minX][minY] = -1;
    }

    static boolean isDone() {
        for (int i = 1; i <= m; i++) {
            if (people[i].x != convenienceStores[i].x || people[i].y != convenienceStores[i].y) {
                return false;
            }
        }

        return true;
    }

    static void initVisited() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                visited[i][j] = false;
    }

    static void initStep() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                step[i][j] = 0;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    static void print2DArr(int[][] arr, String name) {
        System.out.println("===== " + name + " =====");
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }
}

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}