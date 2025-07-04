import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int e : enemy) {
            pq.offer(e);
            n -= e;
            
            if (n < 0) {
                if (k == 0) break;
                
                int max = pq.poll();
                k--;
                n += max;
            }
            
            answer++;
        }
        
        
        return answer;
    }
}