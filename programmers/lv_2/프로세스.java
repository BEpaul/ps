import java.util.*;

class Solution {
    static List<Integer> pList = new ArrayList<>();
    static Queue<int[]> queue = new ArrayDeque();
    static int answer;
    
    public int solution(int[] priorities, int location) {
        for (int i = 0; i < priorities.length; i++) {
            int[] arr = new int[2];
            pList.add(priorities[i]);
            arr[0] = priorities[i];
            if (i == location) {
                arr[1] = 1;
                queue.offer(arr);
            } else {
                arr[1] = 0;
                queue.offer(arr);
            }
        }
        
        Collections.sort(pList, Comparator.reverseOrder());
        
        while (!queue.isEmpty()) {
            int[] process = queue.poll();
            
            if (process[0] == pList.get(0)) {
                pList.remove(0);
                answer++;
                
                if (process[1] == 1) break;
            } else {
                queue.offer(process);
            }
        }
        
        return answer;
    }
}