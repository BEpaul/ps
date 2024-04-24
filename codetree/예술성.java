import java.io.*;
import java.util.*;

/**
 * [구현 순서]
 * 0. 입력 받아서 map[][] 에 넣기
 * 1. 그룹핑 -> BFS
 * 2. 초기 예술 점수 계산하기
 * 2-1. 두 그룹이 갖고 있는 칸의 수 구하기
 * 2-2. 각 그룹이 갖고 있는 숫자 값 구하기
 * 2-3. 두 그룹이 서로 맞닿아 있는 변의 수 구하기
 * 3. 90도 회전, 180도 회전, 270도 회전 구현하여 1, 2, 3차의 map 만들기
 * 4. 돌릴 때마다 예술점수 합하고 마지막에 출력하기
 *
 * [Remind]
 * - 1 <= 각 칸의 숫자 <= 10
 * - Point: 맞닿아 있는 변의 수 구하기 + 행렬 rotate
 */

public class Main {
    static final int[] dx = {0, 1, 0, -1}; // 동 남 서 북
    static final int[] dy = {1, 0, -1, 0};

    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[][] groups;
    static int[][] groupCount = new int[1000][2];
    static int[][] adjs;
    static int[][] map1, map2, map3, map4;
    static int answer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        groups = new int[n][n];
        adjs = new int[1000][1000];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 그룹핑 -> BFS
        grouping();

        // 2. 초기 예술 점수 계산하기
        answer += getScore();

        // 3. 90도 회전, 180도 회전, 270도 회전 구현하여 1, 2, 3차의 map 만들기
        int mid = n / 2;
        map1 = new int[mid][mid];
        map2 = new int[mid][mid];
        map3 = new int[mid][mid];
        map4 = new int[mid][mid];

        rotateStep(mid);
        rotateStep(mid);
        rotateStep(mid);

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    static void grouping() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, ++count);
                }
            }
        }

        initVisited();
    }

    static void bfs(int x, int y, int num) {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(x, y, map[x][y]));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int nowX = pair.x;
            int nowY = pair.y;
            int nowVal = pair.val;
            visited[nowX][nowY] = true;
            groups[nowX][nowY] = num;

            for (int i = 0; i < 4; i++) { // 동 남 서 북
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                if (inRange(nextX, nextY) && !visited[nextX][nextY] && nowVal == map[nextX][nextY]) {
                    queue.add(new Pair(nextX, nextY, map[nextX][nextY]));
                }
            }
        }
    }

    static int getScore() {
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 2; j++) {
                groupCount[i][j] = 0;
            }
        }
        // 2-1. 두 그룹이 갖고 있는 칸의 수 구하기 + 2-2. 각 그룹이 갖고 있는 숫자 값 구하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                groupCount[groups[i][j]][0]++;
                groupCount[groups[i][j]][1] = map[i][j];
            }
        }

        // 2-3. 두 그룹이 서로 맞닿아 있는 변의 수 구하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    getAdjs(i, j);
                }
            }
        }

        return calculateScore();
    }

    static void getAdjs(int x, int y) {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(x, y, groups[x][y]));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int nowX = pair.x;
            int nowY = pair.y;
            int nowVal = pair.val;

            visited[nowX][nowY] = true;

            for (int i = 0; i < 4; i++) { // 동 남 서 북
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                if (inRange(nextX, nextY) && !visited[nextX][nextY]) {
                    if (nowVal == groups[nextX][nextY]) {
                        queue.add(new Pair(nextX, nextY, groups[nextX][nextY]));
                        visited[nextX][nextY] = true;
                    } else {
                        adjs[nowVal][groups[nextX][nextY]]++;
                    }
                }
            }
        }
    }

    static int calculateScore() {
        int score = 0;

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (adjs[i][j] != 0) {
                    int a = i;
                    int b = j;

                    int slots = groupCount[a][0] + groupCount[b][0];
                    score += slots * groupCount[a][1] * groupCount[b][1] * adjs[a][b];
                }
            }
        }

        return score;
    }

    static void rotateStep(int mid) {
        initVisited();
        initGroups();
        initAdjs();
        createNextMap(mid);
        grouping();

        answer += getScore();
    }

    static void createNextMap(int mid) {
        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                map1[i][j] = map[i][j];
                map2[i][j] = map[i][j + mid + 1];
                map3[i][j] = map[i + mid + 1][j];
                map4[i][j] = map[i + mid + 1][j + mid + 1];
            }
        }

        map = rotate(map, 270);
        map1 = rotate(map1, 90);
        map2 = rotate(map2, 90);
        map3 = rotate(map3, 90);
        map4 = rotate(map4, 90);
        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                map[i][j] = map1[i][j];
                map[i][j + mid + 1] = map2[i][j];
                map[i + mid + 1][j] = map3[i][j];
                map[i + mid + 1][j + mid + 1] = map4[i][j];
            }
        }
    }

    static int[][] rotate(int[][] arr, int degree) {
        int length = arr.length;
        int[][] rotate = new int[length][length];

        if (degree == 90) {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    rotate[i][j] = arr[length - j - 1][i];
                }
            }
        } else if (degree == 180) {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    rotate[i][j] = arr[length - i - 1][length - j - 1];
                }
            }
        } else {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    rotate[i][j] = arr[j][length - i - 1];
                }
            }
        }

        return rotate;
    }

    static void initVisited() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                visited[i][j] = false;
    }

    static void initGroups() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                groups[i][j] = 0;
    }

    static void initAdjs() {
        for (int i = 0; i < 1000; i++)
            for (int j = 0; j < 1000; j++)
                adjs[i][j] = 0;
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
    int x;
    int y;
    int val;

    public Pair(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                ", val=" + val +
                '}';
    }
}