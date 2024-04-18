import java.io.*;
import java.util.*;

class Solution {
    static final int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
    static final int[] dy = {0, 1, 0, -1};
    static Queue<Pair> queue = new ArrayDeque<>();
    static boolean[][] visited;
    static int n, m;
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        visited = new boolean[n][m];
        
        boolean isRoute = false;
        queue.add(new Pair(0, 0, 0));
        
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            
            visited[pair.x][pair.y] = true;
            
            int curCnt = pair.count;
            curCnt++;
            
            if (pair.x == n - 1 && pair.y == m - 1) return curCnt;
            for (int i = 0; i < 4; i++) {
                int nextX = pair.x + dx[i];
                int nextY = pair.y + dy[i];
                
                if (inRange(nextX, nextY) && maps[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                    queue.add(new Pair(nextX, nextY, curCnt));
                    visited[nextX][nextY] = true;
                }
            }
        }
        
        return -1;
    }
    
    static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}

class Pair {
    int x;
    int y;
    int count;
    
    public Pair (int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
    
    public String toString() {
        return "(x, y, count) = (" + x + ", " + y + ", " + count + ")"; 
    }
}