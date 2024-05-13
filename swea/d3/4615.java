import java.io.*;
import java.util.*;

public class Solution {
    static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; // 북 북동 동 남동 남 남서 서 북서
    static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    static int t;
    static int n, m;
    static int[][] map;
    static int[] answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= t; testCase++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new int[n][n];
            map[n / 2][n / 2] = 2;
            map[n / 2 - 1][n / 2 - 1] = 2;
            map[n / 2][n / 2 - 1] = 1;
            map[n / 2 - 1][n / 2] = 1;

            int x, y, color;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken()) - 1;
                y = Integer.parseInt(st.nextToken()) - 1;
                color = Integer.parseInt(st.nextToken());

                answer = new int[2];
                map[x][y] = color;
                int nextX, nextY;
                for (int j = 0; j < 8; j++) {
                    Queue<Pos> queue = new ArrayDeque<>();
                    for (int k = 1; k <= 7; k++) {
                        nextX = x + dx[j] * k;
                        nextY = y + dy[j] * k;
                        if (!inRange(nextX, nextY)) break;
                        if (map[nextX][nextY] == 0) break;
                        if (map[nextX][nextY] == color) {
                            while (!queue.isEmpty()) {
                                Pos pos = queue.poll();
                                map[pos.x][pos.y] = color;
                            }
                            break;
                        } else {
                            queue.offer(new Pos(nextX, nextY));
                        }
                    }
                }
            }

            getAnswer();
            bw.write("#" + testCase + " " + answer[0] + " " + answer[1] + "\n");
        }

        br.close();
        bw.close();
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    static void getAnswer() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    answer[0]++;
                } else if (map[i][j] == 2) {
                    answer[1]++;
                }
            }
        }
    }
}

class Pos {
    int x, y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Pos{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}