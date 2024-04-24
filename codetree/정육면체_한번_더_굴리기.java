import java.io.*;
import java.util.*;

/**
 * [구현 순서]
 * 1. 입력 받아서 격자판 만들고, 주사위 굴리기 구현
 * 2. 해당 격자판과 인접한 칸수만큼 점수 더하기
 * 3. 바닥면과 해당 격자판 값에 따라 어느방향으로 이동하는지 구현 + 만약 격자를 벗어난다면 방향 반대로 틀기
 * 4. m번만큼 반복하고 최종 점수 출력
 *
 * [Remind]
 * - 맨 처음에는 오른쪽으로 출발
 */

public class Main {
    static final int[] dx = {0, 1, 0, -1}; // 동 남 서 북
    static final int[] dy = {1, 0, -1, 0};

    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static Map<Integer, Integer> dice = new HashMap<>();
    static int one, two, three, four, five, six;
    static Queue<Node> queue = new ArrayDeque<>();
    static int posX, posY;
    static int answer;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        initDice();

        // 처음에는 반드시 오른쪽으로 굴리기
        rollRight();
        calculateScore();
        initVisited();

        int d = 0; // 방향 0: 동쪽, 1: 남쪽, 2: 서쪽, 3: 북쪽
        for (int iter = 0; iter < m - 1; iter++) {
            if (dice.get(6) > map[posX][posY]) {
                if (d == 3) d = 0;
                else d++;
            } else if (dice.get(6) < map[posX][posY]) {
                if (d == 0) d = 3;
                else d--;
            }

            int nextX = posX + dx[d];
            int nextY = posY + dy[d];
            boolean isRoute = false;

            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                isRoute = true;
            }

            if (d == 0) {
                if (isRoute) rollRight();
                else {
                    rollLeft();
                    d = 2;
                }
            } else if (d == 1) {
                if (isRoute) rollDown();
                else {
                    rollUp();
                    d = 3;
                }
            } else if (d == 2) {
                if (isRoute) rollLeft();
                else {
                    rollRight();
                    d = 0;
                }
            } else if (d == 3) {
                if (isRoute) rollUp();
                else {
                    rollDown();
                    d = 1;
                }
            }

            calculateScore();
            initVisited();

        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    static void initDice() {
        for (int i = 1; i <= 6; i++) {
            dice.put(i, i);
        }
    }

    static void calculateScore() {
        queue.add(new Node(posX, posY));
        int count = 1;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int nowX = node.x;
            int nowY = node.y;
            visited[nowX][nowY] = true;

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                    if (!visited[nextX][nextY] && map[nextX][nextY] == map[nowX][nowY]) {
                        visited[nextX][nextY] = true;
                        count++;
                        queue.add(new Node(nextX, nextY));
                    }
                }

            }
        }
        answer += map[posX][posY] * count;
    }

    static void initVisited() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }
    }

    static void rollRight() {
        three = dice.get(3);
        one = dice.get(1);
        four = dice.get(4);
        six = dice.get(6);

        dice.put(3, one);
        dice.put(1, four);
        dice.put(4, six);
        dice.put(6, three);

        posY++;
    }

    static void rollDown() {
        two = dice.get(2);
        one = dice.get(1);
        five = dice.get(5);
        six = dice.get(6);

        dice.put(2, one);
        dice.put(1, five);
        dice.put(5, six);
        dice.put(6, two);

        posX++;
    }

    static void rollLeft() {
        four = dice.get(4);
        one = dice.get(1);
        three = dice.get(3);
        six = dice.get(6);

        dice.put(4, one);
        dice.put(1, three);
        dice.put(3, six);
        dice.put(6, four);

        posY--;
    }

    static void rollUp() {
        five = dice.get(5);
        one = dice.get(1);
        two = dice.get(2);
        six = dice.get(6);

        dice.put(5, one);
        dice.put(1, two);
        dice.put(2, six);
        dice.put(6, five);

        posX--;
    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}