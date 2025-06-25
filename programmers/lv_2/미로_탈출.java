import java.util.*;

class Solution {
    static final int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
    static final int[] dy = {0, 1, 0, -1};
    static int n, m;
    static char[][] maze;
    static int[] start = new int[2];
    static int[] end = new int[2];
    static int[] lever = new int[2];
    
    public int solution(String[] maps) {
        int answer = 0;
        n = maps.length;
        m = maps[0].length();
        maze = new char[n][m];
        
        // set maps to 2d array
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maze[i][j] = maps[i].charAt(j);
                
                if (maze[i][j] == 'S') {
                    start = new int[]{i, j};
                } else if (maze[i][j] == 'E') {
                    end = new int[]{i, j};
                } else if (maze[i][j] == 'L') {
                    lever = new int[]{i, j};
                }
            }
        }
        
        int firstSearchRes = bfs(start, lever);
        int secondSearchRes = bfs(lever, end);
        if (firstSearchRes == -1 || secondSearchRes == -1) return -1;
        
        answer += firstSearchRes + secondSearchRes;
        
        return answer;
    }
    
    int bfs(int[] start, int[] target) {
        Queue<Pair> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        int[][] steps = new int[n][m];
        queue.offer(new Pair(start[0], start[1]));
        
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            if (pair.x == target[0] && pair.y == target[1]) {
                return steps[pair.x][pair.y];
            }
            
            visited[pair.x][pair.y] = true;
            for (int i = 0; i < 4; i++) {
                int nextX = pair.x + dx[i];
                int nextY = pair.y + dy[i];
                
                if (isInRange(nextX, nextY) && maze[nextX][nextY] != 'X' && !visited[nextX][nextY]) {
                    queue.offer(new Pair(nextX, nextY));
                    visited[nextX][nextY] = true;
                    steps[nextX][nextY] = steps[pair.x][pair.y] + 1;
                }
            }
        }
        
        return -1;
    }
    
    boolean isInRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}

class Pair {
    int x;
    int y;
    
    public Pair (int x, int y) {
        this.x = x;
        this. y = y;
    }
    
    public String toString() {
        return "(x, y, step) = (" + x + ", " + y + ")";
    }
}