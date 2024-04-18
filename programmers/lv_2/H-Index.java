import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] citations) {
        if (citations.length == 1 && citations[0] == 0) return 0;
        Arrays.sort(citations);
        
        int answer = 0;
        int total = citations.length;
        for (int i = 0; i < total; i++) {
            if (citations[i] >= total - i) {
                if (i <= citations[i]) {
                    answer = Math.max(answer, total - i);    
                }
            }
        }
        
        return answer;
    }
}