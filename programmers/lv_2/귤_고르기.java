import java.util.*;

class Solution {
    static int[] tangCount = new int[10000001];
    static int answer;
    public int solution(int k, int[] tangerine) {
        int pick = tangerine.length - k;
        
        for (int i = 0; i < tangerine.length; i++) {
            tangCount[tangerine[i]]++;
        }
        
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < 10000001; i++) {
            if (tangCount[i] > 0) counts.add(tangCount[i]);
        }
        
        Collections.sort(counts);
        int count = 0;
        for (int i = 0; i < counts.size(); i++) {
            if (count + counts.get(i) > pick) {
                answer = counts.size() - i;
                break;
            }
            
            count += counts.get(i);
        }
        
        return answer;
    }
}