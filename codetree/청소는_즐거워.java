import java.io.*;
import java.util.*;

/**
 * [구현 순서]
 * 1. map 입력 받고 회전 구현하기. 이때, 격자 바깥으로 2칸씩 더 빼서 크게 만들기
 * 2. 이동할 때마다 근처에 먼지 쌓이는 것 계산하기. 이때, 빗자루가 이동한 위치(Curr)에 있는 먼지는 모두 사라지게 하기
 * 3. 이동하면서 격자 바깥으로 나간 먼지 양 계산하기
 * [Remind]
 * - 비율을 곱해줄 때 소숫점 아래의 숫자는 버림한다.
 */

public class Main {
    static final int[] dx = {0, 1, 0, -1}; // 서 남 동 북
    static final int[] dy = {-1, 0, 1, 0};

    // 서쪽
    static final int[] westX = {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0}; // 2%, 10%, 7%, 1%, 5%, 10%, 7%, 1%, 2%, a%
    static final int[] westY = {0, -1, 0, 1, -2, -1, 0, 1, 0, -1};
    // 남쪽
    static final int[] southX = {0, 1, 0, -1, 2, 1, 0, -1, 0, 1};
    static final int[] southY = {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0};
    // 동쪽
    static final int[] eastX = {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0};
    static final int[] eastY = {0, 1, 0, -1, 2, 1, 0, -1, 0, 1};
    // 북쪽
    static final int[] northX = {0, -1, 0, 1, -2, -1, 0, 1, 0, -1};
    static final int[] northY = {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0};
    static final double[] percentages = {(double) 2 / (double) 100, (double) 10 / (double) 100,
            (double) 7 / (double) 100, (double) 1 / (double) 100, (double) 5 / (double) 100, (double) 10 / (double) 100,
            (double) 7 / (double) 100, (double) 1 / (double) 100, (double) 2 / (double) 100};

    static int n;
    static int[][] map;
    static int answer;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n + 4][n + 4];

        for (int i = 2; i < n + 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 2; j < n + 2; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x = (n + 4) / 2;
        int y = (n + 4) / 2;
        int dist = 1;
        int iter = 0; // 0: 서, 1: 남, 2: 동, 3: 북
        double curVal;
        boolean isArrive = false;
        while (true) {
            for (int i = 0; i < dist; i++) {
                x += dx[iter];
                y += dy[iter];
                curVal = map[x][y];
                if (iter == 0) { // 서
                    for (int j = 0; j < 9; j++) {
                        int nextX = x + westX[j];
                        int nextY = y + westY[j];
                        int addVal = (int) Math.floor(map[x][y] * percentages[j]);
                        map[nextX][nextY] += addVal;

                        curVal -= addVal;
                    }
                    int aX = x + westX[9];
                    int aY = y + westY[9];
                    map[aX][aY] += (int) curVal;

                } else if (iter == 1) { // 남
                    for (int j = 0; j < 9; j++) {
                        int nextX = x + southX[j];
                        int nextY = y + southY[j];
                        int addVal = (int) Math.floor(map[x][y] * percentages[j]);
                        map[nextX][nextY] += addVal;

                        curVal -= addVal;
                    }
                    int aX = x + southX[9];
                    int aY = y + southY[9];
                    map[aX][aY] += (int) curVal;
                } else if (iter == 2) { // 동
                    for (int j = 0; j < 9; j++) {
                        int nextX = x + eastX[j];
                        int nextY = y + eastY[j];
                        int addVal = (int) Math.floor(map[x][y] * percentages[j]);
                        map[nextX][nextY] += addVal;

                        curVal -= addVal;
                    }
                    int aX = x + eastX[9];
                    int aY = y + eastY[9];
                    map[aX][aY] += (int) curVal;
                } else { // 북
                    for (int j = 0; j < 9; j++) {
                        int nextX = x + northX[j];
                        int nextY = y + northY[j];
                        int addVal = (int) Math.floor(map[x][y] * percentages[j]);
                        map[nextX][nextY] += addVal;

                        curVal -= addVal;
                    }
                    int aX = x + northX[9];
                    int aY = y + northY[9];
                    map[aX][aY] += (int) curVal;
                }

                map[x][y] = 0;

                if (x == 2 && y == 2) {
                    isArrive = true;
                    break;
                }
            }

            if (isArrive) break;

            iter++;

            if (iter % 2 == 0) dist++;
            if (iter % 4 == 0) iter = 0;
        }

        calculateAnswer();

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    static void calculateAnswer() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n + 4; j++) {
                answer += map[i][j];
            }
        }

        for (int i = n + 2; i < n + 4; i++) {
            for (int j = 0; j < n + 4; j++) {
                answer += map[i][j];
            }
        }

        for (int i = 2; i < n + 2; i++) {
            for (int j = 0; j < 2; j++) {
                answer += map[i][j];
            }

            for (int k = n + 2; k < n + 4; k++) {
                answer += map[i][k];
            }
        }
    }
}