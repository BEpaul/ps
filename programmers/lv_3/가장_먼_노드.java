import java.util.*;

class Solution {
    
    static boolean[] visited;
    static List<Integer>[] adjs;
    static int[] dist;
    static int max;
    
    public int solution(int n, int[][] edge) {
        
        adjs = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        dist = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            adjs[i] = new ArrayList<>();
        }
        
        for (int[] e : edge) {
            adjs[e[0]].add(e[1]);
            adjs[e[1]].add(e[0]);
        }
        
        bfs();
        
        return getResult(n);
    }
    
    static void bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(1, 0));
        
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            visited[now.num] = true;
            dist[now.num] = now.dist;
            max = Math.max(max, now.dist);
            
            for (int i = 0; i < adjs[now.num].size(); i++) {
                int next = adjs[now.num].get(i);
                if (!visited[next]) {
                    queue.offer(new Node(next, now.dist + 1));
                    visited[next] = true;
                }
            }
        }
    }
    
    static int getResult(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == max) {
                count++;
            }
        }
        
        return count;
    }
}

class Node {
    int num;
    int dist;
    
    public Node(int num, int dist) {
        this.num = num;
        this.dist = dist;
    }
}

