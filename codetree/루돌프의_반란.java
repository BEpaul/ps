import java.io.*;
import java.util.*;

/**
 * [구현 순서]
 * 0. 입력 받기: Santa[p + 1], map[n][n], Rudolph
 * 1. (1) 게임판의 구성: Santa 배열과 루돌프 객체, map을 동기화하는 함수 작성하기
 * 2. (2) 루돌프의 움직임: 루돌프로부터 가장 가까운 거리의 산타에게 1칸 돌진. 거리가 같을 경우, 행, 열이 크면 우선순위를 가진다.
 * 3. (3) 산타의 움직임: 기절하지 않은 산타에 대해 순서대로 루돌프쪽으로 1칸씩 움직인다.
 * 3-1. 다른 산타가 있는 칸이나 게임판 밖으로는 움직일 수 없다. 움직일 수 없다면 안움직이기.
 * 3-2. 움직일 수 있더라도 루돌프와 가까워지는 방법이 없다면 움직이지 않기
 * 4. (4) 충돌: 산타와 루돌프가 같은 칸에 있게 되면 충돌이 발생한다. (루돌프가 움직인 후, 산타가 움직인 후 해당 동작이 실행된다.)
 * 4-1. 루돌프가 움직여서 충돌이 일어난 경우, 해당 산타는 루돌프의 힘만큼의 점수를 얻고 루돌프가 이동해온 방향으로 밀려난다.
 * 4-2. 산타가 움직여서 충돌이 일어난 경우, 산타는 자신의 힘만큼의 점수를 얻고 자신이 이동해온 방향으로 밀려난다.
 * 4-3. 이때, 밀려나는 것은 도중에 충돌이 일어나지 않고 정확히 원하는 위치에 도달한다.
 * 4-4. 만약 밀려난 위치가 게임판 밖이라면 산타는 게임에서 탈락한다.
 * 4-5. 만약 밀려난 칸에 다른 산타가 있는 경우 상호작용이 발생한다.
 * 5. (5) 상호작용: 루돌프와의 충돌 후 산타는 포물선 궤적으로 착지하는 칸에서만 상호작용이 발생한다.
 * 5-1. 충돌 후 착지하게 되는 칸에 다른 산타가 있다면 그 산타는 1칸 해당 방향으로 밀려나게 된다. 그 옆에 산타가 있다면 연쇄적으로 1칸씩 밀려나는 것을
 *      반복한다. 또한 게임판 밖으로 밀려난 산타는 게임에서 탈락한다.
 * 6. (6) 기절: 산타는 루돌프와의 충돌 후 기절한다. 그래서 깨어나는 턴을 산타 객체의 sleep에 넣어주자.
 * 7. (7) 게임 종료: m번의 턴이 끝나거나 모든 산타가 탈락하면 게임이 종료한다.
 * 7-1. 매 턴 이후 탈락하지 않은 모든 산타에게 1점씩 부여한다.
 * 8. 각 산타가 얻은 최종 점수를 출력한다.
 *
 * [Remind]
 * - 루돌프는 8방향, 산타는 4방항 가까워질 수 있는 방향이 여러 개라면 상우하좌 우선순위다.
 */

public class Main {
    static final int[] rX = {-1, -1, 0, 1, 1, 1, 0, -1}; // 북 북동 동 남동 남 남서 서 북서
    static final int[] rY = {0, 1, 1, 1, 0, -1, -1, -1};
    static final int[] sX = {-1, 0, 1, 0}; // 북 동 남 서
    static final int[] sY = {0, 1, 0, -1};

    static int n, m, p, c, d;
    static int[][] map;
    static Santa[] santas;
    static Rudolph rudolph;
    static int t;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        santas = new Santa[p + 1];

        // 0. 입력 받기: Santa[p + 1], map[n][n], Rudolph
        st = new StringTokenizer(br.readLine());
        int rx = Integer.parseInt(st.nextToken());
        int ry = Integer.parseInt(st.nextToken());
        rudolph = new Rudolph(rx - 1, ry - 1, c, -1);

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int sNum = Integer.parseInt(st.nextToken());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            santas[sNum] = new Santa(sx - 1, sy - 1, 0, d, 0, -1);
        }

        // 1. (1) 게임판의 구성: Santa 배열과 루돌프 객체, map을 동기화하는 함수 작성하기
        syncMap();
        
        for (t = 1; t <= m; t++) {
            moveRudolph();
            syncMap();
            rudolphClash();
            syncMap();
            moveSanta();
            syncMap();
            addSantaScore();
            syncMap();

            if(isDone()) break;
        }

        for (int i = 1; i <= p; i++) {
            bw.write(santas[i].score + " ");
        }
        br.close();
        bw.close();
    }

    // 2. (2) 루돌프의 움직임: 루돌프로부터 가장 가까운 거리의 산타에게 1칸 돌진. 거리가 같을 경우, 행, 열이 크면 우선순위를 가진다.
    static void moveRudolph() {
        int santaNum = getSantaNearNum();
        int minDist = Integer.MAX_VALUE;
        int minX = -1;
        int minY = -1;
        int dir = -1;
        for (int i = 0; i < 8; i++) {
            int nextX = rudolph.x + rX[i];
            int nextY = rudolph.y + rY[i];

            if (inRange(nextX, nextY)) {
                int dist = (int) (Math.pow(santas[santaNum].x - nextX, 2) + Math.pow(santas[santaNum].y - nextY, 2));
                if (minDist > dist) {
                    minDist = dist;
                    minX = nextX;
                    minY = nextY;
                    dir = i;
                }
            }
        }

        rudolph.x = minX;
        rudolph.y = minY;
        rudolph.recentDir = dir;
        syncMap();
    }

    static int getSantaNearNum() {
        int minDist = Integer.MAX_VALUE;
        int santaNum = -1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (map[i][j] > 0) {
                    int dist = (int) (Math.pow(rudolph.x - i, 2) + Math.pow(rudolph.y - j, 2));
                    if (minDist > dist) {
                        minDist = dist;
                        santaNum = map[i][j];
                    }
                }
            }
        }

        return santaNum;
    }

    // 3. (3) 산타의 움직임: 기절하지 않은 산타에 대해 순서대로 루돌프쪽으로 1칸씩 움직인다.
    static void moveSanta() {
        for (int i = 1; i <= p; i++) {
            // 게임에서 탈락한 산타 또는 기절 중인 산타는 움직일 수 없습니다.
            if (santas[i].x == -1 && santas[i].y == -1 || santas[i].sleep > t) continue;
            syncMap();
            int minX = -1;
            int minY = -1;
            int dir = -1;
            int minDist = (int) (Math.pow(rudolph.x - santas[i].x, 2) + Math.pow(rudolph.y - santas[i].y, 2));
            for (int d = 0; d < 4; d++) {
                int nextX = santas[i].x + sX[d];
                int nextY = santas[i].y + sY[d];

                // 3-1. 다른 산타가 있는 칸이나 게임판 밖으로는 움직일 수 없다. 움직일 수 없다면 안움직이기.
                if (inRange(nextX, nextY) && map[nextX][nextY] <= 0) {
                    int dist = (int) (Math.pow(rudolph.x - nextX, 2) + Math.pow(rudolph.y - nextY, 2));
                    if (minDist > dist) {
                        minDist = dist;
                        minX = nextX;
                        minY = nextY;
                        dir = d;
                    }
                }
            }

            // 3-2. 움직일 수 있더라도 루돌프와 가까워지는 방법이 없다면 움직이지 않기
            if (minX == -1 && minY == -1) continue;
            // 매 산타가 움직일 때마다 map도 바꿔주는것 잊지 말자.
            santas[i].x = minX;
            santas[i].y = minY;
            santas[i].recentDir = dir;
            syncMap();
            santaClash();
        }
    }

    // 4. (4) 충돌: 산타와 루돌프가 같은 칸에 있게 되면 충돌이 발생한다. (루돌프가 움직인 후, 산타가 움직인 후 해당 동작이 실행된다.)
    // 4-1. 루돌프가 움직여서 충돌이 일어난 경우, 해당 산타는 루돌프의 힘만큼의 점수를 얻고 루돌프가 이동해온 방향으로 밀려난다.
    // 4-3. 이때, 밀려나는 것은 도중에 충돌이 일어나지 않고 정확히 원하는 위치에 도달한다.
    // 4-4. 만약 밀려난 위치가 게임판 밖이라면 산타는 게임에서 탈락한다.
    // 4-5. 만약 밀려난 칸에 다른 산타가 있는 경우 상호작용이 발생한다.
    static void rudolphClash() {
        for (int i = 1; i <= p; i++) {
            if (santas[i].x == rudolph.x && santas[i].y == rudolph.y) {
                santas[i].score += rudolph.power;
                int nextX = santas[i].x + rX[rudolph.recentDir] * rudolph.power;
                int nextY = santas[i].y + rY[rudolph.recentDir] * rudolph.power;

                // 산타 탈락
                if (!inRange(nextX, nextY)) {
                    santas[i].x = -1;
                    santas[i].y = -1;
                    continue;
                }

                // 만약 다른 산타가 있는 경우, 상호작용 발생
                if (map[nextX][nextY] > 0) {
                    interaction(nextX, nextY, rudolph.recentDir, 0);
                }

                santas[i].x = nextX;
                santas[i].y = nextY;

                // 6. (6) 기절: 산타는 루돌프와의 충돌 후 기절한다. 그래서 깨어나는 턴을 산타 객체의 sleep에 넣어주자.
                santas[i].sleep = t + 2;
                syncMap();
            }
        }
    }

    // 4-2. 산타가 움직여서 충돌이 일어난 경우, 산타는 자신의 힘만큼의 점수를 얻고 자신이 이동해온 방향으로 밀려난다.
    static void santaClash() {
        for (int i = 1; i <= p; i++) {
            if (santas[i].x == rudolph.x && santas[i].y == rudolph.y) {
                santas[i].score += santas[i].power;
                int dir = santas[i].recentDir;
                if (dir == 2) {
                    dir = 0;
                } else if (dir == 3) {
                    dir = 1;
                } else {
                    dir += 2;
                }

                int nextX = santas[i].x + sX[dir] * santas[i].power;
                int nextY = santas[i].y + sY[dir] * santas[i].power;

                map[rudolph.x][rudolph.y] = -1;
                // 산타 탈락
                if (!inRange(nextX, nextY)) {
                    santas[i].x = -1;
                    santas[i].y = -1;
                    continue;
                }

                // 만약 다른 산타가 있는 경우, 상호작용 발생
                if (map[nextX][nextY] > 0) {
                    interaction(nextX, nextY, dir, 1);
                }

                santas[i].x = nextX;
                santas[i].y = nextY;

                // 6. (6) 기절: 산타는 루돌프와의 충돌 후 기절한다. 그래서 깨어나는 턴을 산타 객체의 sleep에 넣어주자.
                santas[i].sleep = t + 2;
                syncMap();
            }
        }
    }

    // 5. (5) 상호작용: 루돌프와의 충돌 후 산타는 포물선 궤적으로 착지하는 칸에서만 상호작용이 발생한다.
    // 5-1. 충돌 후 착지하게 되는 칸에 다른 산타가 있다면 그 산타는 1칸 해당 방향으로 밀려나게 된다. 그 옆에 산타가 있다면 연쇄적으로 1칸씩 밀려나는 것을
    //      반복한다. 또한 게임판 밖으로 밀려난 산타는 게임에서 탈락한다.
    // opt = 0: 루돌프에 의한 상호작용, opt = 1: 산타에 의한 상호작용 (방향이 달라진다.)
    static void interaction(int x, int y, int dir, int opt) {
        Queue<Santa> queue = new ArrayDeque<>();
        int curSantaNum = map[x][y];
        queue.offer(santas[curSantaNum]);
        while (!queue.isEmpty()) {
            syncMap();
            Santa santa = queue.poll();
            int nextX = -2;
            int nextY = -2;
            if (opt == 0) {
                nextX = santa.x + rX[dir];
                nextY = santa.y + rY[dir];
            } else if (opt == 1) {
                nextX = santa.x + sX[dir];
                nextY = santa.y + sY[dir];
            }

            if (!inRange(nextX, nextY)) {
                santa.x = -1;
                santa.y = -1;
                continue;
            }

            if (map[nextX][nextY] > 0) {
                int nextSantaNum = map[nextX][nextY];
                queue.offer(santas[nextSantaNum]);
            }

            santa.x = nextX;
            santa.y = nextY;
        }

        syncMap();
    }

    static void addSantaScore() {
        for (int i = 1; i <= p; i++) {
            if (santas[i].x != -1) {
                santas[i].score++;
            }
        }
    }

    static void syncMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = 0;
            }
        }

        for (int i = 1; i <= p; i++) {
            if (santas[i].x != -1) {
                map[santas[i].x][santas[i].y] = i;
            }
        }

        map[rudolph.x][rudolph.y] = -1;
    }

    static boolean isDone() {
        for (int i = 1; i <= p; i++) {
            if (santas[i].x != -1) {
                return false;
            }
        }

        return true;
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

class Santa {
    int x, y;
    int score, power;
    int sleep;
    int recentDir;

    public Santa(int x, int y, int score, int power, int sleep, int recentDir) {
        this.x = x;
        this.y = y;
        this.score = score;
        this.power = power;
        this.sleep = sleep;
        this.recentDir = recentDir;
    }

    @Override
    public String toString() {
        return "Santa{" +
                "x=" + x +
                ", y=" + y +
                ", score=" + score +
                ", power=" + power +
                ", sleep=" + sleep +
                ", recentDir=" + recentDir +
                '}';
    }
}

class Rudolph {
    int x, y;
    int power;
    int recentDir;

    public Rudolph(int x, int y, int power, int recentDir) {
        this.x = x;
        this.y = y;
        this.power = power;
        this.recentDir = recentDir;
    }

    @Override
    public String toString() {
        return "Rudolph{" +
                "x=" + x +
                ", y=" + y +
                ", power=" + power +
                ", recentDir=" + recentDir +
                '}';
    }
}