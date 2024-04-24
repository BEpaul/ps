import java.io.*;
import java.util.*;

/**
 * [구현 순서]
 * 0. 입력 받아서 Player[m], guns: List<Integer>[n][n]으로 받기
 * 1. 첫 번째 플레이어부터 순차적으로 향하는 방향대로 한 칸만큼 이동한다. 만약 격자를 벗어나면 정반대 방향으로 바꾸어 이동한다. (이때 방향 바꾸자.)
 * 2. 만약 이동한 방향에 플레이어가 없고 총이 있다면 총을 획득한다. 이미 총을 가지고 있다면 비교하여 더 쎈 총을 획득하고, 나머지 총은 격자에 둔다.
 * 3. 만약 이동한 방향에 플레이어가 있다면 두 플레이어가 싸운다. (초기 능력치 + 갖고 있는 총의 공격력)을 비교하여 겨루고, 이긴 플레이어는 그 차이만큼을 획득한다.
 * 3-1. 진 플레이어는 본인이 가진 총을 격자에 내려두고, 한 칸 더 이동한다. 이때 이동하려는 칸에 다른 플레이어가 있거나 격자 범위 밖인 경우 오른쪽으로 90도씩
 * 회전하면서 빈 칸이 보이는 순간 이동한다. 만약 해당 칸에 총이 있다면, 해당 플레이어는 가장 공격력이 높은 총을 획득하고 나머지 총은 격자에 내려 놓는다.
 * 3-2. 이긴 플레이어는 좀전에 떨어뜨린 패배자의 총과 비교한 후 공격력이 더 쎄다면 바꾼다.
 * 4. 라운드를 반복한다.
 *
 * [Remind]
 * -
 *
 */

public class Main {
    static final int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
    static final int[] dy = {0, 1, 0, -1};

    static int n, m, k;
    static List<Integer>[][] guns;
    static Player[] players;
    static int[] points;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        guns = new ArrayList[n][n];
        players = new Player[m];
        points = new int[m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                guns[i][j] = new ArrayList<>();
                guns[i][j].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            players[i] = new Player(x - 1, y - 1, d, s, 0);
        }
        
        for (int i = 0; i < k; i++) {
            round();
        }

        for (int i = 0; i < m; i++) {
            bw.write(points[i] + " ");
        }
        
        bw.flush();
        br.close();
        bw.close();
    }

    static void round() {
        // 1. 첫 번째 플레이어부터 순차적으로 향하는 방향대로 한 칸만큼 이동한다. 만약 격자를 벗어나면 정반대 방향으로 바꾸어 이동한다. (이때 방향 바꾸자.)
        for (int i = 0; i < m; i++) {
            int nowX = players[i].x;
            int nowY = players[i].y;
            int nowD = players[i].d;

            int nextX = nowX + dx[nowD];
            int nextY = nowY + dy[nowD];

            
            // 만약 격자를 벗어나면 방향을 바꾸어서 이동
            if (!inRange(nextX, nextY)) {
                if (nowD == 2) {
                    players[i].d = 0;
                    nowD = 0;
                } else if (nowD == 3) {
                    players[i].d = 1;
                    nowD = 1;
                } else {
                    players[i].d += 2;
                    nowD += 2;
                }
                nextX = nowX + dx[nowD];
                nextY = nowY + dy[nowD];
            }

            int curPlayerNum = -1;
            boolean isPlayer = false;
            for (int j = 0; j < m; j++) {
                if (nextX == players[j].x && nextY == players[j].y) {
                    isPlayer = true;
                    curPlayerNum = j;
                }
            }

            players[i].x = nextX;
            players[i].y = nextY;

            // 만약 이동한 방향에 플레이어가 없고 총이 있다면 총을 획득한다. 이미 총을 가지고 있다면 비교하여 더 쎈 총을 획득하고, 나머지 총은 격자에 둔다.
            if (!isPlayer) {
                if (!guns[nextX][nextY].isEmpty()) {
                    Integer max = Collections.max(guns[nextX][nextY]);
                    if (max > players[i].b) {
                        guns[nextX][nextY].add(players[i].b);
                        players[i].b = max;
                        guns[nextX][nextY].remove(max);
                    }
                }

            } else { // 만약 이동한 방향에 플레이어가 있다면 두 플레이어가 싸운다. (초기 능력치 + 갖고 있는 총의 공격력)을 비교하여 겨루고,
                    // 이긴 플레이어는 그 차이만큼을 획득한다.
                int diff = Math.abs((players[i].a + players[i].b) - (players[curPlayerNum].a + players[curPlayerNum].b));
                int loser, winner;
                if ((players[i].a + players[i].b) > (players[curPlayerNum].a + players[curPlayerNum].b)) {
                    winner = i;
                    loser = curPlayerNum;
                } else if ((players[i].a + players[i].b) < (players[curPlayerNum].a + players[curPlayerNum].b)) {
                    winner = curPlayerNum;
                    loser = i;
                } else {
                    if (players[i].a > players[curPlayerNum].a) {
                        winner = i;
                        loser = curPlayerNum;
                    } else {
                        winner = curPlayerNum;
                        loser = i;
                    }
                }

                points[winner] += diff;

                // 3-1. 진 플레이어는 본인이 가진 총을 격자에 내려두고, 한 칸 더 이동한다. 이때 이동하려는 칸에 다른 플레이어가 있거나
                // 격자 범위 밖인 경우 오른쪽으로 90도씩 회전하면서 빈 칸이 보이는 순간 이동한다. 만약 해당 칸에 총이 있다면,
                // 해당 플레이어는 가장 공격력이 높은 총을 획득하고 나머지 총은 격자에 내려 놓는다.
                guns[players[loser].x][players[loser].y].add(players[loser].b);
                players[loser].b = 0;

                while (true) {
                    nextX = players[loser].x + dx[players[loser].d];
                    nextY = players[loser].y + dy[players[loser].d];

                    if (!inRange(nextX, nextY) || isPlayer(nextX, nextY)) {
                        if (players[loser].d == 3) players[loser].d = 0;
                        else players[loser].d++;
                    } else {
                        players[loser].x = nextX;
                        players[loser].y = nextY;
                        if (!guns[nextX][nextY].isEmpty()) {
                            Integer max = Collections.max(guns[players[loser].x][players[loser].y]);
                            players[loser].b = max;
                            guns[players[loser].x][players[loser].y].remove(max);
                        }
                        break;
                    }
                }

                // 3-2. 이긴 플레이어는 좀전에 떨어뜨린 패배자의 총과 비교한 후 공격력이 더 쎄다면 바꾼다.
                if (!guns[players[winner].x][players[winner].y].isEmpty()) {
                    Integer max = Collections.max(guns[players[winner].x][players[winner].y]);
                    if (max > players[winner].b) {
                        guns[players[winner].x][players[winner].y].add(players[winner].b);
                        players[winner].b = max;
                        guns[players[winner].x][players[winner].y].remove(max);
                    }
                }
            }
        }
    }

    static boolean isPlayer(int x, int y) {
        for (int i = 0; i < m; i++) {
            if (x == players[i].x && y == players[i].y) {
                return true;
            }
        }

        return false;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 & x < n && y >= 0 && y < n;
    }
}

class Player {
    int x, y, d, a, b;

    public Player(int x, int y, int d, int a, int b) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "Player{" +
                "x=" + x +
                ", y=" + y +
                ", d=" + d +
                ", a=" + a +
                ", b=" + b +
                '}';
    }
}