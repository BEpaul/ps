import java.util.*;

class Solution {
    static char[][] grid;
    static int n, m;
    static int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    
    public List solution(String[] maps) {
        int[] answer = {};
        List<Integer> answers = new ArrayList<>();
        
        n = maps.length;
        m = maps[0].length();
        grid = new char[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = maps[i].charAt(j);
            }
        }
        
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != 'X' && !visited[i][j]) {
                    int result = bfs(i, j);
                    answers.add(result);
                }
            }
        }
        
        if (answers.isEmpty()) {
            answers.add(-1);
            return answers;
        }
        
        Collections.sort(answers);
        
        return answers;
    }
    
    int bfs(int x, int y) {
        int res = 0;
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(x, y));
        
        while (!queue.isEmpty()) {
            Pair now = queue.poll();
            visited[now.x][now.y] = true;
            res += grid[now.x][now.y] - '0';
            
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                
                if (isInRange(nextX, nextY) && !visited[nextX][nextY] && grid[nextX][nextY] != 'X') {
                    visited[nextX][nextY] = true;
                    queue.offer(new Pair(nextX, nextY));
                }
            }
        }
        
        return res;
    }
    
    boolean isInRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}

class Pair {
    int x;
    int y;
    
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public String toString(int x, int y) {
        return "(x, y) = (" + x + ", " + y + ")";
    }
}