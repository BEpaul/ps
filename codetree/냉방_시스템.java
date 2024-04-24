import java.io.*;
import java.util.*;

/**
 * [구현 순서]
 * 1. 입력 받아서 빈 공간, 사무실 구역, 에어컨 위치에 대한 이차원 배열 생성 + 벽에 대한 이차원 배열 생성 + 에어컨 시원함에 대한 이차원 배열 생성
 * 2. 에어컨 전파 구현 (각 에어컨 위치에서 상대적으로 전파되도록) + 벽에 막히는지 아닌지 체크 필수
 * 3. 전파된 에어컨의 시원함 합치기
 * 4. 시원한 공기들이 섞이는 것 구현 (이때, 동시에 일어나는 것 생각!) + 벽을 사이에 두면 전파가 일어나지 않음 유의
 * 5. 외벽에 맞닿아 있는 칸들의 시원함 1씩 감소 구현, 만약 0이었다면 감소하지 않음
 * 6. 사무실을 순회하며 모든 사무실의 시원함이 전부 k 이상인지 확인하고, k 이상이라면 종료
 *
 * [Remind]
 * - 100 분이 넘으면 -1을 출력한다.
 * - 에어컨 바람이 벽에 막히는 경우에 대해 실수하지 말자.
 */

public class Main {
    static final int[] dx = {0, 1, 0, -1}; // 동 남 서 북
    static final int[] dy = {1, 0, -1, 0};

    static int n, m, k;
    static int[][] map;
    static boolean[][] upWalls, rightWalls, downWalls, leftWalls;
    static int[][] cool;
    static int[][] mixMap;
    static Queue<AC> ACQueue = new ArrayDeque<>();
    static Queue<AC> saveACQueue = new ArrayDeque<>();
    static int answer = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        upWalls = new boolean[n][n];
        rightWalls = new boolean[n][n];
        downWalls = new boolean[n][n];
        leftWalls = new boolean[n][n];
        cool = new int[n][n];
        mixMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 2) {
                    saveACQueue.add(new AC(i, j, map[i][j]));
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            if (s == 0) {
                upWalls[x - 1][y - 1] = true;
                downWalls[x - 2][y - 1] = true;
            } else if (s == 1) {
                leftWalls[x - 1][y - 1] = true;
                rightWalls[x - 1][y - 2] = true;
            }
        }

        // 에어컨 전파 구현 (각 에어컨 위치에서 상대적으로 전파되도록) + 벽에 막히는지 아닌지 체크 필수
        while (true) {
            ACQueue.addAll(saveACQueue);
            while (!ACQueue.isEmpty()) {
                spread();
            }

            // 시원한 공기들이 섞이는 것 구현 (이때, 동시에 일어나는 것 생각!) + 벽을 사이에 두면 전파가 일어나지 않음 유의
            mix();

            // 외벽에 맞닿아 있는 칸들의 시원함 1씩 감소 구현, 만약 0이었다면 감소하지 않음
            reduceNearWall();

            // 사무실을 순회하며 모든 사무실의 시원함이 전부 k 이상인지 확인하고, k 이상이라면 종료
            boolean isDone = checkOffices();
            if (isDone) break;

            answer++;
            if (answer > 100) {
                answer = -1;
                break;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    static void spread() {
        AC airCon = ACQueue.poll();

        boolean[][] visited = new boolean[n][n];
        Queue<Air> airs = new ArrayDeque<>();
        if (airCon.d == 2) { // 서쪽
            airs.add(new Air(airCon.x, airCon.y - 1, 5));

            while (!airs.isEmpty()) {
                Air air = airs.poll();
                int x = air.x;
                int y = air.y;
                int power = air.power;

                checkIn(visited, x, y, power);

                if (power > 1) {
                    if (inRange(x - 1, y - 1) && !leftWalls[x - 1][y] && !upWalls[x][y]) {
                        airs.add(new Air(x - 1, y - 1, power - 1));
                    }
                    if (inRange(x, y - 1) && !leftWalls[x][y]) {
                        airs.add(new Air(x, y - 1, power - 1));
                    }
                    if (inRange(x + 1, y - 1) && !leftWalls[x + 1][y] && !downWalls[x][y]) {
                        airs.add(new Air(x + 1, y - 1, power - 1));
                    }
                }
            }
        } else if (airCon.d == 3) { // 북쪽
            airs.add(new Air(airCon.x - 1, airCon.y, 5));

            while (!airs.isEmpty()) {
                Air air = airs.poll();
                int x = air.x;
                int y = air.y;
                int power = air.power;

                checkIn(visited, x, y, power);

                if (power > 1) {
                    if (inRange(x - 1, y - 1) && !upWalls[x][y - 1] && !leftWalls[x][y]) {
                        airs.add(new Air(x - 1, y - 1, power - 1));
                    }
                    if (inRange(x - 1, y) && !upWalls[x][y]) {
                        airs.add(new Air(x - 1, y, power - 1));
                    }
                    if (inRange(x - 1, y + 1) && !upWalls[x][y + 1] && !rightWalls[x][y]) {
                        airs.add(new Air(x - 1, y + 1, power - 1));
                    }
                }
            }
        } else if (airCon.d == 4) { // 동쪽
            airs.add(new Air(airCon.x, airCon.y + 1, 5));

            while (!airs.isEmpty()) {
                Air air = airs.poll();
                int x = air.x;
                int y = air.y;
                int power = air.power;

                checkIn(visited, x, y, power);

                if (power > 1) {
                    if (inRange(x - 1, y + 1) && !rightWalls[x - 1][y] && !upWalls[x][y]) {
                        airs.add(new Air(x - 1, y + 1, power - 1));
                    }
                    if (inRange(x, y + 1) && !rightWalls[x][y]) {
                        airs.add(new Air(x, y + 1, power - 1));
                    }
                    if (inRange(x + 1, y + 1) && !rightWalls[x + 1][y] && !downWalls[x][y]) {
                        airs.add(new Air(x + 1, y + 1, power - 1));
                    }
                }
            }
        } else if (airCon.d == 5) { // 남쪽
            airs.add(new Air(airCon.x + 1, airCon.y, 5));

            while (!airs.isEmpty()) {
                Air air = airs.poll();
                int x = air.x;
                int y = air.y;
                int power = air.power;

                checkIn(visited, x, y, power);

                if (power > 1) {
                    if (inRange(x + 1, y - 1) && !downWalls[x][y - 1] && !leftWalls[x][y]) {
                        airs.add(new Air(x + 1, y - 1, power - 1));
                    }
                    if (inRange(x + 1, y) && !downWalls[x][y]) {
                        airs.add(new Air(x + 1, y, power - 1));
                    }
                    if (inRange(x + 1, y + 1) && !downWalls[x][y + 1] && !rightWalls[x][y]) {
                        airs.add(new Air(x + 1, y + 1, power - 1));
                    }
                }
            }
        }
    }

    static void mix() {
        mixMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 일단은 벽 고려하지 않고 주변보다 큰지 확인 후 크면 그 차이 / 4의 내림 만큼 주는 것을 새로운 배열에 기록하기
                // k -> 0: 동 / 1: 남 / 2: 서 / 3: 북
                for (int k = 0; k < 4; k++) {
                    int nextX = i + dx[k];
                    int nextY = j + dy[k];

                    // 벽 있는지 체크
                    if (k == 0 && rightWalls[i][j]) continue;
                    if (k == 1 && downWalls[i][j]) continue;
                    if (k == 2 && leftWalls[i][j]) continue;
                    if (k == 3 && upWalls[i][j]) continue;

                    if (inRange(nextX, nextY) && cool[i][j] - cool[nextX][nextY] >= 4) {
                        int val = (cool[i][j] - cool[nextX][nextY]) / 4;
                        mixMap[nextX][nextY] += val;
                        mixMap[i][j] -= val;
                    }
                }

            }
        }

        calculateMix();
        initMixMap();
    }

    static void initMixMap() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                mixMap[i][j] = 0;
    }

    static void calculateMix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cool[i][j] += mixMap[i][j];
            }
        }
    }

    static void reduceNearWall() {
        for (int i = 0; i < n; i++) {
            if (cool[i][0] >= 1) cool[i][0]--;
            if (cool[i][n - 1] >= 1) cool[i][n - 1]--;
        }

        for (int j = 1; j < n - 1; j++) {
            if (cool[0][j] >= 1) cool[0][j]--;
            if (cool[n - 1][j] >= 1) cool[n - 1][j]--;
        }
    }

    static boolean checkOffices() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    if (cool[i][j] < k) return false;
                }
            }
        }

        return true;
    }

    static void checkIn(boolean[][] visited, int x, int y, int power) {
        if (!visited[x][y]) {
            cool[x][y] += power;
            visited[x][y] = true;
        }
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}

class Air {
    int x;
    int y;
    int power;

    public Air(int x, int y, int power) {
        this.x = x;
        this.y = y;
        this.power = power;
    }

    @Override
    public String toString() {
        return "Air{" +
                "x=" + x +
                ", y=" + y +
                ", power=" + power +
                '}';
    }
}

class AC {
    int x;
    int y;
    int d;

    public AC(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

    @Override
    public String toString() {
        return "AC{" +
                "x=" + x +
                ", y=" + y +
                ", d=" + d +
                '}';
    }
}