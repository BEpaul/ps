import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] count = new int[100001];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '}' || c == ',') {
                if (sb.length() > 0) {
                    int num = Integer.parseInt(sb.toString());
                    count[num]++;
                    sb.setLength(0);
                }
                continue;
            }
            
            sb.append(c);
        }
        
        int max = getMaxValue(count);
        int[] answer = new int[max];
        for (int i = 1; i < 100001; i++) {
            if (count[i] > 0) {
                answer[max - count[i]] = i;
            }
        }
        
        return answer;
    }
    
    int getMaxValue(int[] array) {
        int val = -1;
        for (int i = 0; i < array.length; i++) {
            val = Math.max(val, array[i]);
        }
        return val;
    }
}