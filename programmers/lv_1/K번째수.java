import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int comLen = commands.length;
        int[] answer = new int[comLen];
        
        int i, j, k;
        int iter = 0;
        for (int[] command : commands) {
            i = command[0];
            j = command[1];
            k = command[2];
            
            int[] newArr = Arrays.copyOfRange(array, i - 1, j);
            Arrays.sort(newArr);
            answer[iter] = newArr[k - 1];
            
            iter++;
        }
        
        return answer;
    }
}