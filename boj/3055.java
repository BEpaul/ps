import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int r, c;
    static char[][] map;
    static int[][] dp;
    static Queue<Point> queue;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        r = sc.nextInt();
        c = sc.nextInt();

        map = new char[r][c];
        dp = new int[r][c];

        queue = new LinkedList<>();

        Point st = null;
        for (int i = 0; i < r; i++) {
            String line = sc.next();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '*') {
                    queue.add(new Point(i, j, '*'));
                } else if (map[i][j] == 'S') {
                    st = new Point(i, j, 'S');
                }
            }
        }

        queue.add(st);

        boolean foundAnswer = false;
        while (!queue.isEmpty()) {
            // 1. 큐에서 꺼내옴
            Point p = queue.poll();

            // 2. 목적지인가? (고슴도치)
            if (p.type == 'D') {
                // 정답 처리
                System.out.println(dp[p.y][p.x]);
                foundAnswer = true;
                break;
            }

            // 3. 연결된 곳을 순회(좌우 위아래)
            for (int i = 0; i < 4; i++) {
                int ty = p.y + dy[i];
                int tx = p.x + dx[i];

                // 4. 갈 수 있는가? (공통) -> 맵을 벗어나지 않기
                if (0 <= ty && ty < r && 0 <= tx && tx < c) {
                    if (p.type == 'S' || p.type == '.') {
                        // 4. 갈 수 있는가? (고슴도치) ., D, 방문하지 않은 곳
                        if ((map[ty][tx] == '.' || map[ty][tx] == 'D') && dp[ty][tx] == 0) {
                            // 5. 체크인 (고슴도치)
                            dp[ty][tx] = dp[p.y][p.x] + 1;
                            // 6. 큐에 넣음
                            queue.add(new Point(ty, tx, map[ty][tx]));
                        }
                    } else if (p.type == '*') {
                        // 4. 갈 수 있는가? (물) ., S
                        if (map[ty][tx] == '.' || map[ty][tx] == 'S') {
                            // 5. 체크인 (물)
                            map[ty][tx] = '*';
                            // 6. 큐에 넣음
                            queue.add(new Point(ty, tx, '*'));
                        }
                    }
                }
            }
        }

        if (!foundAnswer) {
            System.out.println("KAKTUS");
        }
    }
}

class Point {
    int y;
    int x;
    char type;

    public Point(int y, int x, char type) {
        this.y = y;
        this.x = x;
        this.type = type;
    }

    @Override
    public String toString() {
        return "[y=" + y + ", x=" + x + ", type=" + type + "]";
    }
}