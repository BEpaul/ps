import java.io.*;
import java.util.*;

class Solution {
    static Queue<Integer> progressQ = new ArrayDeque<>();
    static Queue<Integer> speedQ = new ArrayDeque<>();
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        int len = progresses.length;
        for (int progress : progresses) {
            progressQ.offer(progress);
        }
        
        for (int speed : speeds) {
            speedQ.offer(speed);
        }
        
        int day = 0;
        int count = 0;
        while (!progressQ.isEmpty()) {
            day++;
            if ((progressQ.peek() + day * speedQ.peek()) >= 100) {
                progressQ.poll();
                speedQ.poll();
                count++;
                while (true) {
                    if (progressQ.isEmpty()) break;
                    if ((progressQ.peek() + day * speedQ.peek()) >= 100) {
                        progressQ.poll();
                        speedQ.poll();
                        count++;
                    } else {
                        break;
                    }
                }
                list.add(count);
                count = 0;       
            }
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}