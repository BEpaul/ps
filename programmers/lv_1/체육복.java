import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (lost[i] == reserve[j]) {
                    lost[i] = -5;
                    reserve[j] = -10;
                    answer++;
                }
            }
        }
        
        Arrays.sort(lost);
        Arrays.sort(reserve);
        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (Math.abs(lost[i] - reserve[j]) <= 1) {
                    reserve[j] = -10;
                    answer++;
                    break;
                }
            }
        }
        
        return answer;
    }
}