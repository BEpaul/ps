import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [구현 순서]
 * 1. 입력 받기: Map으로 (지금 턴에 탑승하는 학생의 번호): (좋아하는 학생 번호 (List)) -> Queue에 넣기
 * 2. seats 이중배열 생성하고 앉히기 시작 (빈 자리: 0 / 있는 자리: 해당 학생 번호)
 * 3. 완탐하면서
 */
public class Main {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int n;
    static Queue<BoardingInfo> queue;
    static int[][] seats;
    static int student;
    static List<Integer> likes;
    static int[][] likeCount;
    static int[][] emptyCount;
    static Map<Integer, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        queue = new ArrayDeque<>();

        int num;
        for (int i = 0; i < n * n; i++) {
            likes = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 4; j++) {
                likes.add(Integer.parseInt(st.nextToken()));
            }

            queue.add(new BoardingInfo(num, likes));
            map.put(num, likes);
        }

        seats = new int[n][n];
        ride();

        bw.write(String.valueOf(calculateScore()));

        br.close();
        bw.close();
    }

    private static void ride() {
        int nextX, nextY;
        while (!queue.isEmpty()) {
            BoardingInfo boardingInfo = queue.poll();
            student = boardingInfo.student;
            likes = boardingInfo.likes;
            likeCount = new int[n][n];
            emptyCount = new int[n][n];
            int likeMax = -1;
            int emptyMax = -1;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < 4; k++) {
                        nextX = i + dx[k];
                        nextY = j + dy[k];

                        if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                            if (seats[nextX][nextY] == 0) {
                                emptyCount[i][j]++;
                            } else if (likes.contains(seats[nextX][nextY])) {
                                likeCount[i][j]++;
                            }
                        }

                        likeMax = Math.max(likeMax, likeCount[i][j]);
                        emptyMax = Math.max(emptyMax, emptyCount[i][j]);
                    }
                }
            }

            List<int[]> maxLikes = getMaxLikes(likeMax, emptyMax);

            putPosition(maxLikes);
        }
    }

    private static List<int[]> getMaxLikes(int likeMax, int emptyMax) {
        List<int[]> maxLikes = new ArrayList<>();
        List<int[]> maxEmpties = new ArrayList<>();

        while (true) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int[] arr = new int[2];
                    if (likeCount[i][j] == likeMax && seats[i][j] == 0) {
                        arr[0] = i;
                        arr[1] = j;
                        maxLikes.add(arr);
                    }

                    if (emptyCount[i][j] == emptyMax && seats[i][j] == 0) {
                        arr[0] = i;
                        arr[1] = j;
                        maxEmpties.add(arr);
                    }
                }
            }

            if (!maxLikes.isEmpty() || likeMax == 0) {
                break;
            }

            likeMax--;
        }
        return maxLikes;
    }

    private static void putPosition(List<int[]> maxLikes) {
        if (maxLikes.size() == 1) {
            int[] pos = maxLikes.get(0);
            seats[pos[0]][pos[1]] = student;
        } else {
            int posX = 0;
            int posY = 0;
            int maxEmpty = -1;

            for (int i = 0; i < maxLikes.size(); i++) {
                int[] pos = maxLikes.get(i);

                if (emptyCount[pos[0]][pos[1]] > maxEmpty) {
                    maxEmpty = emptyCount[pos[0]][pos[1]];
                    posX = pos[0];
                    posY = pos[1];

                }

            }

            seats[posX][posY] = student;
        }
    }

    static int calculateScore() {
        int nextX, nextY;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                List<Integer> likeList = map.get(seats[i][j]);

                for (int k = 0; k < 4; k++) {
                    nextX = i + dx[k];
                    nextY = j + dy[k];

                    if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                        if (likeList.contains(seats[nextX][nextY])) {
                            count++;
                        }
                    }
                }

                if (count == 2) count = 10;
                else if (count == 3) count = 100;
                else if (count == 4) count = 1000;
                ret += count;
            }
        }

        return ret;
    }
}

class BoardingInfo {
    int student;
    List<Integer> likes;

    public BoardingInfo(int student, List<Integer> likes) {
        this.student = student;
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "BoardingInfo{" +
                "student=" + student +
                ", likes=" + likes +
                '}';
    }
}