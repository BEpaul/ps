import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            pq.offer(work);
        }
        
        for (int i = 0; i < n; i++) {
            int w = pq.poll();
            if (w == 0) break;
            
            pq.offer(--w);
        }
        
        for (int work : pq) {
            answer += work * work;
        }
        
        return answer;
    }
}