import java.util.*;

class Solution {
    static int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dy = {0, 1, 0, -1};
    
    public int solution(String dirs) {
        int answer = 0;
        boolean[][][] visited = new boolean[11][11][4];
        Pair pair = new Pair(5, 5, 0);
        int dir = -1;
        
        for (char d : dirs.toCharArray()) {
            if (d == 'U') dir = 0;
            if (d == 'R') dir = 1;
            if (d == 'D') dir = 2;
            if (d == 'L') dir = 3;
            
            int nextX = pair.x + dx[dir];
            int nextY = pair.y + dy[dir];
            if (!inRange(nextX, nextY)) continue;
            if (!visited[nextX][nextY][dir]) {
                visited[nextX][nextY][dir] = true;
                visited[pair.x][pair.y][(dir + 2) % 4] = true;
                answer++;
            }
            pair.x = nextX;
            pair.y = nextY;
        }
        
        return answer;
    }
    
    boolean inRange(int x, int y) {
        return 0 <= x && x < 11 && 0 <= y && y < 11;
    }
}

class Pair {
    int x, y, count;
    
    public Pair(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}