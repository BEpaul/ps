import java.util.*;
import java.io.*;

class Solution {
    static Queue<Integer> queue;
    static List<Integer>[] adjs;
    static boolean[] visited;
    static int answer;
    
    public int solution(int n, int[][] computers) {
        
        adjs = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adjs[i] = new ArrayList<>();
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (computers[i - 1][j - 1] == 1 && i != j) {
                    adjs[i].add(j);
                }
            }
        }
        
        visited = new boolean[n + 1];
        queue = new ArrayDeque<>();
        
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) answer++;            
                
            visited[i] = true;
            queue.offer(i);
            while(!queue.isEmpty()) {
                int now = queue.poll();
                visited[now] = true;
                for (int j = 0; j < adjs[now].size(); j++) {
                    int next = adjs[now].get(j);
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);    
                    }
                }
            }
        }
        
        return answer;
    }
}