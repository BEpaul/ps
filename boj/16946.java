import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dy = {0, 1, 0, -1};

    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[][] groups;
    static Map<Integer, Integer> groupMap = new HashMap<>();
    static Set<Integer> set = new HashSet<>();
    static int groupNum = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        groups = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] splitted = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(splitted[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    bfs(i, j);
                    groupNum++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int answer = 0;

                if (map[i][j] == 1) {
                    answer++;
                    for (int k = 0; k < 4; k++) {
                        int nextX = i + dx[k];
                        int nextY = j + dy[k];

                        if (inRange(nextX, nextY) && !set.contains(groups[nextX][nextY])) {
                            if (groups[nextX][nextY] > 0) {
                                answer += groupMap.get(groups[nextX][nextY]);
                                set.add(groups[nextX][nextY]);
                            }
                        }
                    }
                }

                bw.write(String.valueOf(answer % 10));
                set.clear();
            }
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    static void bfs(int x, int y) {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(x, y));
        int count = 0;

        while (!queue.isEmpty()) {
            Pair now = queue.poll();
            visited[now.x][now.y] = true;
            groups[now.x][now.y] = groupNum;
            count++;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (inRange(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] == 0) {
                    groups[nextX][nextY] = groupNum;
                    visited[nextX][nextY] = true;

                    queue.offer(new Pair(nextX, nextY));
                }
            }
        }

        groupMap.put(groupNum, count);
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}