import java.io.*;
import java.util.*;

/**
 * [구현 순서]
 * l: 체스판의 크기, n: 기사의 수, q: 명령의 수, k: 기사의 체력
 * 0. 입력 받기: map[l][l], knight[n], knightMap[l][l]
 * 1. knight 객체 배열의 정보를 활용해 한눈에 알아보기 쉬운 knightMap을 한 턴 주기로 갱신시켜주기
 * 2. (1) 기사 이동: 이동하려는 위치에 대해 벽 또는 기사가 있는지 재귀적으로 체크하고, 밀 수 있다면 민다. 가능성은 큐 또는 재귀로 구현하자.
 *                이때 밀리는 기사들을 모두 기억한다.
 * 3. (2) 대결 대미지: 밀려난 기사들은 각자의 위치 안에 있는 함정의 수만큼 피해를 입고, 체력이 0이 되면 체스판에서 사라진다.
 *                  체스판에서 사라진다 -> knight hp:0으로 만들고, knightMap을 0으로 갱신한다.
 * 4. 만약 사라진 기사에게 명령 내리면 무시하기
 * 5. Q번의 왕의 명령이 끝나면 생존한 기사들이 총 받은 데미지를 출력한다.
 *
 * [Remind]
 * - 체스판에서 사라진 기사에게 명령을 내리면 아무런 반응이 없다.
 * - d: 0123 / 북동남서
 * - 체스판에서 사라진 기사에게 명령이 내려질 수 도 있음에 유의 -> 해당 명령은 무시한다.
 */

public class Main {
    static final int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
    static final int[] dy = {0, 1, 0, -1};

    static int l, n, q, k;
    static int[][] map;
    static Knight[] knights;
    static int[][] knightMap;
    static boolean[][] visited;
    static HashSet<Integer> moveSet;
    static long answer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        map = new int[l][l];
        knightMap = new int[l][l];
        knights = new Knight[n + 1];
        visited = new boolean[l][l];

        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < l; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            knights[i] = new Knight(r - 1, c - 1, h, w, k, k);
        }

        // 왕의 명령
        for (int iter = 0; iter < q; iter++) {
            st = new StringTokenizer(br.readLine());
            int kNum = Integer.parseInt(st.nextToken());
            int kDir = Integer.parseInt(st.nextToken());

            // 4. 만약 사라진 기사에게 명령 내리면 무시하기
            if (knights[kNum].curHp <= 0) continue;

            // 1. knight 객체 배열의 정보를 활용해 한눈에 알아보기 쉬운 knightMap를 한 턴 주기로 갱신시켜주기
            renewKnightMap();

            move(kNum, kDir);
            renewKnightMap();
            // 3. (2) 대결 대미지: 밀려난 기사들은 각자의 위치 안에 있는 함정의 수만큼 피해를 입고, 체력이 0이 되면 체스판에서 사라진다.
            //                  체스판에서 사라진다 -> knight hp:0으로 만들고, knightMap을 0으로 갱신한다.
            giveDamage();

            renewKnightMap();
        }

        // 5. Q번의 왕의 명령이 끝나면 생존한 기사들이 총 받은 데미지를 출력한다.
        for (Knight knight : knights) {
            if (knight != null && knight.curHp > 0) {
                answer += knight.initHp - knight.curHp;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    // 2. (1) 기사 이동: 이동하려는 위치에 대해 벽 또는 기사가 있는지 재귀적으로 체크하고, 밀 수 있다면 민다. 가능성은 큐 또는 재귀로 구현하자.
    //                 이때 밀리는 기사들을 모두 기억한다.
    static void move(int kNum, int kDir) {
        moveSet = new HashSet<>();
        boolean canGo = search(kNum, kDir);

        if (canGo) {
            for (Integer move : moveSet) {
                knights[move].x += dx[kDir];
                knights[move].y += dy[kDir];
            }
        } else {
            moveSet.clear();
        }

        moveSet.remove(kNum);
    }

    static boolean search(int kNum, int kDir) {
        initVisited();
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(knights[kNum].x, knights[kNum].y));
        moveSet.add(kNum);
        while (!queue.isEmpty()) {
            Pair now = queue.poll();
            visited[now.x][now.y] = true;
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (!inRange(nextX, nextY) && i == kDir) {
                    return false;
                }

                if (inRange(nextX, nextY) && map[nextX][nextY] == 2 && i == kDir) {
                    return false;
                }

                if (inRange(nextX, nextY) && !visited[nextX][nextY] && knightMap[nextX][nextY] > 0) {

                    if (knightMap[now.x][now.y] == knightMap[nextX][nextY]) {
                        queue.add(new Pair(nextX, nextY));
                        visited[nextX][nextY] = true;
                        moveSet.add(knightMap[nextX][nextY]);
                    } else if (knightMap[now.x][now.y] != knightMap[nextX][nextY] && i == kDir) {
                        queue.add(new Pair(nextX, nextY));
                        visited[nextX][nextY] = true;
                        moveSet.add(knightMap[nextX][nextY]);
                    }
                }
            }
        }

        initVisited();

        return true;
    }

    static void giveDamage() {
        for (Integer move : moveSet) {
            for (int i = knights[move].x; i < knights[move].x + knights[move].h; i++) {
                for (int j = knights[move].y; j < knights[move].y + knights[move].w; j++) {
                    if (map[i][j] == 1) {
                        if (knights[move].curHp > 0) {
                            knights[move].curHp--;
                        }
                    }
                }
            }
        }
    }

    static void renewKnightMap() {
        initKnightMap();
        for (int i = 1; i <= n; i++) {
            if (knights[i].curHp > 0) {
                for (int j = 0; j < knights[i].h; j++) {
                    for (int k = 0; k < knights[i].w; k++) {
                        knightMap[knights[i].x + j][knights[i].y + k] = i;
                    }
                }
            }
        }
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < l && y >= 0 && y < l;
    }

    static void initKnightMap() {
        for (int i = 0; i < l; i++)
            for (int j = 0; j < l; j++)
                knightMap[i][j] = 0;
    }

    static void initVisited() {
        for (int i = 0; i < l; i++)
            for (int j = 0; j < l; j++)
                visited[i][j] = false;
    }

    static void print2DArr(int[][] arr, String name) {
        System.out.println("===== " + name + " =====");
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }
}
