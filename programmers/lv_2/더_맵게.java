import java.io.*;
import java.util.*;

class Solution {
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    
    public int solution(int[] scoville, int K) {
        for (int s : scoville) {
            pq.add(s);
        }
        
        int answer = 0;
        int minVal, secMinVal;
        int newScoville;
        
        while (true) {
            if (pq.size() == 1 && pq.peek() < K) return -1;
            if (pq.peek() >= K) {
                break;
            }
            
            minVal = pq.poll();
            secMinVal = pq.poll();
            newScoville = minVal + secMinVal * 2;
            pq.add(newScoville);
            answer++;
        }
        
        return answer;
    }
}