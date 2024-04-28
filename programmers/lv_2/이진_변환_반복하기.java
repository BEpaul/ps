import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        while (true) {
            answer[0]++;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') answer[1]++;
            }
            
            s = s.replace("0", "");

            int len = s.length();
            s = Integer.toBinaryString(len);
            if (s.equals("1")) break;
        }
        
        return answer;
    }
}