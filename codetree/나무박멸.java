import java.io.*;
import java.util.*;

/**
 * [구현 순서]
 * 1. 입력 받기: 나무 + 벽 맵[][]: 그루 수, 제초제[][]: 남은 년 수
 * 2. 인접한 네 개의 칸 중 나무가 있는 칸의 수만큼 나무가 성장한다. 성장은 모든 나무가 "동시"에 일어난다!
 * 3. 번식을 진행한다. 이 과정 또한 동시에 일어난다!
 * 4. 제초제를 뿌렸을 때 박멸되는 나무의 수를 계산하고, 그 중에서 가장 많은 칸에 전파된다. 제초제는 c년 동안 유지된다.
 * 5. 제초제가 뿌려져 있는 곳은 자라지 않도록 처리한다.
 * 6. 제초제 남은 년 수-- 하고, m년 동안 과정을 반복한다.
 *
 * [Remind]
 * - m <= 10^3 -> 나머지를 10^5 안에 끝내야 한다... 시간 초과 잘 생각하자!
 * - 제초제가 뿌려진 곳에 다시 제초제가 뿌려지는 경우에는 새로 뿌려진 해로부터 다시 c년동안 제초제가 유지된다.
 * - 만약 박멸시키는 나무의 수가 동일한 칸이 있는 경우에는 행이 작은 순서 -> 열이 작은 순서로 제초제 뿌리는 것 유의!
 */

public class Main {
    static final int[] dx = {0, 1, 0, -1}; // 동 남 서 북
    static final int[] dy = {1, 0, -1, 0};

    static final int[] spreadX = {-1, 1, 1, -1}; // 북동 남동 남서 복서
    static final int[] spreadY = {1, 1, -1, -1};

    static int n, m, K, c;
    static int[][] map;
    static int[][] meds;
    static int[][] grows, breeds, deadMax;
    static long answer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        meds = new int[n][n];
        grows = new int[n][n];
        breeds = new int[n][n];
        deadMax = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int year = 1; year <= m; year++) {
            growUp();
            breed();
            putMed();
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }


    static void growUp() { // 인접한 네 개의 칸 중 나무가 있는 칸의 수만큼 나무가 성장한다. 성장은 모든 나무가 "동시"에 일어난다!
        int nextX, nextY;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] <= 0) continue;
                for (int k = 0; k < 4; k++) { // 0: 동, 1: 남, 2: 서, 3: 북
                    nextX = i + dx[k];
                    nextY = j + dy[k];

                    if (inRange(nextX, nextY) && map[nextX][nextY] > 0) {
                        grows[i][j]++;
                    }
                }
            }
        }

        addArr(map, grows);
        initArr(grows);
    }

    static void breed() { // 번식을 진행한다. 이 과정 또한 동시에 일어난다!
        int nextX, nextY;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] <= 0) continue;
                int nearCount = 0;
                for (int k = 0; k < 4; k++) {
                    nextX = i + dx[k];
                    nextY = j + dy[k];

                    if (inRange(nextX, nextY) && map[nextX][nextY] == 0 && meds[nextX][nextY] == 0) {
                        nearCount++;
                    }
                }

                if (nearCount == 0) continue;

                int val = map[i][j] / nearCount;

                for (int k = 0; k < 4; k++) {
                    nextX = i + dx[k];
                    nextY = j + dy[k];

                    if (inRange(nextX, nextY) && map[nextX][nextY] == 0 && meds[nextX][nextY] == 0) {
                        breeds[nextX][nextY] += val;
                    }
                }
            }
        }

        addArr(map, breeds);
        initArr(breeds);
    }

    static void putMed() { // 제초제를 뿌렸을 때 박멸되는 나무의 수를 계산하고, 그 중에서 가장 많은 칸에 전파된다. 제초제는 c년 동안 유지된다.
        // 제초제를 뿌렸을 때 박멸되는 나무의 수 계산
        calculateDeadTrees();
        reduceMeds();
        spread();
    }

    static void calculateDeadTrees() {
        Queue<Med> medQueue = new ArrayDeque<>();
        int nextX, nextY;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int deadTreeCount = 0;
                if (map[i][j] <= 0) continue;

                deadTreeCount += map[i][j];
                for (int k = 0; k < 4; k++) {
                    nextX = i + spreadX[k];
                    nextY = j + spreadY[k];

                    if (inRange(nextX, nextY) && map[nextX][nextY] > 0) {
                        medQueue.add(new Med(nextX, nextY, k, K));
                    }
                }

                while (!medQueue.isEmpty()) {
                    Med med = medQueue.poll();
                    deadTreeCount += map[med.x][med.y];

                    int num = med.num;
                    nextX = med.x + spreadX[med.d];
                    nextY = med.y + spreadY[med.d];

                    if (inRange(nextX, nextY) && map[nextX][nextY] > 0 && num > 1) {
                        medQueue.add(new Med(nextX, nextY, med.d, num - 1));
                    }
                }

                deadMax[i][j] = deadTreeCount;
            }
        }

    }

    static void spread() {
        int[] max = getMax();
        int x = max[0];
        int y = max[1];
        meds[x][y] = c;
        answer += map[x][y];

        map[x][y] = 0;

        Queue<Med> medQueue = new ArrayDeque<>();

        int nextX, nextY;
        for (int i = 0; i < 4; i++) {
            nextX = x + spreadX[i];
            nextY = y + spreadY[i];

            if (inRange(nextX, nextY) && map[nextX][nextY] != -1) {
                if (map[nextX][nextY] == 0) {
                    medQueue.add(new Med(nextX, nextY, i, K, false));
                } else {
                    medQueue.add(new Med(nextX, nextY, i, K));
                }
            }
        }

        while (!medQueue.isEmpty()) {
            Med med = medQueue.poll();
            int num = med.num;
            boolean stopFlag = med.stopFlag;
            meds[med.x][med.y] = c;
            answer += map[med.x][med.y];
            map[med.x][med.y] = 0;

            nextX = med.x + spreadX[med.d];
            nextY = med.y + spreadY[med.d];

            if (inRange(nextX, nextY) && map[nextX][nextY] != -1 && num > 1 && stopFlag) {
                if (map[nextX][nextY] == 0) {
                    medQueue.add(new Med(nextX, nextY, med.d, num - 1, false));
                } else {
                    medQueue.add(new Med(nextX, nextY, med.d, num - 1));
                }

            }
        }

        initArr(deadMax);
    }

    static void reduceMeds() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (meds[i][j] >= 1) {
                    meds[i][j]--;
                }
            }
        }
    }

    static int[] getMax() {
        int max = 0;
        int[] arr = new int[3]; // x, y, 나무 수

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] >= 0) {
                    arr[0] = i;
                    arr[1] = j;
                    arr[2] = deadMax[i][j];
                    break;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (deadMax[i][j] > max) {
                    max = deadMax[i][j];
                    arr[0] = i;
                    arr[1] = j;
                    arr[2] = deadMax[i][j];
                }
            }
        }

        return arr;
    }

    static void addArr(int[][] a, int[][] b) { // a: 대상, b: 더해주는 배열
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] += b[i][j];
            }
        }
    }

    static void initArr(int[][] arr) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                arr[i][j] = 0;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    static void printArr(int[][] arr, String name) {
        System.out.println("==== " + name + " ====");
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }
}

class Med {
    int x, y, d, num;
    boolean stopFlag = true;

    public Med(int x, int y, int d, int num) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.num = num;
    }

    public Med(int x, int y, int d, int num, boolean stopFlag) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.num = num;
        this.stopFlag = stopFlag;
    }
}