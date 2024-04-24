import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [구현 순서]
 * 1. 리브로수 map 받기
 * 2. 이동 후 특수영양제 위치의 리브로수 1씩 성장
 * 3. 대각선 방향 2 이상의 리브로수만큼 또 성장
 * 4. 맵 전체 순회하며 높이가 2 이상인 리브로수의 높이 2만큼 잘라내고 해당 땅 위에 특수 영양제 올려두기
 * 5. 연도만큼 반복화
 */
public class Main {
    static final int[] dx = {2, 0, -1, -1, -1, 0, 1, 1, 1}; // 동 북동 북 북서 서 남서 남 남동
    static final int[] dy = {2, 1, 1, 0, -1, -1, -1, 0, 1};
    static int n, m;
    static int[][] trees;
    static int d, p;
    static Queue<Move> years = new ArrayDeque<>();
    static Queue<Medicine> meds, arriveMeds;
    static int x, y, nextX, nextY;
    static List<Medicine> medicineList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        trees = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                trees[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            years.add(new Move(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        meds = new ArrayDeque<>();
        arriveMeds = new ArrayDeque<>();
        meds.add(new Medicine(n - 2, 0));
        meds.add(new Medicine(n - 2, 1));
        meds.add(new Medicine(n - 1, 0));
        meds.add(new Medicine(n - 1, 1));

        while (!years.isEmpty()) {
            Move move = years.poll();
            d = move.d;
            p = move.p;

            moveMed();
            growByAdj();
            cutting();

            medicineList.clear();
        }

        bw.write(String.valueOf(countHeights()));

        br.close();
        bw.close();
    }

    static void moveMed() {
        while (!meds.isEmpty()) {
            Medicine medicine = meds.poll();
            x = medicine.x;
            y = medicine.y;

            for (int i = 0; i < p; i++) {
                nextX = x + dx[d];
                nextY = y + dy[d];

                if (nextX < 0) {
                    nextX = n - 1;
                } else if (nextX >= n) {
                    nextX = 0;
                }

                if (nextY < 0) {
                    nextY = n - 1;
                } else if (nextY >= n) {
                    nextY = 0;
                }

                x = nextX;
                y = nextY;
            }

            trees[x][y]++;
            arriveMeds.add(new Medicine(x, y));
            medicineList.add(new Medicine(x, y));
        }
    }

    static void growByAdj() {
        while (!arriveMeds.isEmpty()) {
            Medicine medicine = arriveMeds.poll();
            x = medicine.x;
            y = medicine.y;
            int count = 0;

            // 대각선 리브로수의 높이가 1 이상인 개수만큼 높이 증가 -> 2, 4, 6, 8
            for (int i = 2; i <= 8; i += 2) {
                nextX = x + dx[i];
                nextY = y + dy[i];

                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                    if (trees[nextX][nextY] >= 1) {
                        count++;
                    }
                }
            }

            trees[x][y] += count;
        }
    }

    // trees 순회하며 높이가 2 이상인 리브로수 높이 2만큼 잘라내고 meds 큐에 넣기
    static void cutting() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean isUsed = false;
                for (int k = 0; k < medicineList.size(); k++) {
                    Medicine medicine = medicineList.get(k);
                    if (i == medicine.x && j == medicine.y) {
                        isUsed = true;
                    }
                }

                if (!isUsed && trees[i][j] >= 2) {
                    trees[i][j] -= 2;
                    meds.add(new Medicine(i, j));
                }

            }
        }
    }

    static int countHeights() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count += trees[i][j];
            }
        }

        return count;
    }
}

class Medicine {
    int x;
    int y;

    public Medicine(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Move {
    int d;
    int p;

    public Move(int d, int p) {
        this.d = d;
        this.p = p;
    }

    @Override
    public String toString() {
        return "Move{" +
                "d=" + d +
                ", p=" + p +
                '}';
    }
}