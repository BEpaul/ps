import java.io.*;
import java.util.*;

public class Solution {
    static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; // 북 북동 동 남동 남 남서 서 북서
    static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    static int t;
    static int n;
    static String[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= t; testCase++) {
            n = Integer.parseInt(br.readLine());
            map = new String[n][n];

            for (int i = 0; i < n; i++) {
                String[] split = br.readLine().split("");
                for (int j = 0; j < n; j++) {
                    map[i][j] = split[j];
                }
            }

            boolean isYes = false;
            iteration:
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j].equals("o")) {
                        isYes = bfs(i, j);
                        if (isYes) {
                            break iteration;
                        }
                    }
                }
            }

            if (isYes) bw.write("#" + testCase + " YES\n");
            else bw.write("#" + testCase + " NO\n");
        }

        br.close();
        bw.close();
    }

    static boolean bfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 4; j++) {
                int nextX = x + dx[i] * j;
                int nextY = y + dy[i] * j;
                if (!inRange(nextX, nextY) || map[nextX][nextY].equals(".")) break;

                if (j == 4) return true;
            }
        }

        return false;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}