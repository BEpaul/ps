import java.util.*;

class Solution {
    
    static boolean[] visited;
    static int answer;
    
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        
        dfs(begin, target, words, 0);
        
        return answer;
    }
    
    void dfs(String begin, String target, String[] words, int count) {
        if (begin.equals(target)) {
            answer = count;
            return;
        }
        
        for (int i = 0; i < words.length; i++) {
            if (visited[i]) continue;
            
            int cnt = 0;
            for (int j = 0; j < words[i].length(); j++) {
                if (begin.charAt(j) == words[i].charAt(j)) {
                    cnt++;
                }
            }
            
            if (cnt == words[i].length() - 1) {
                visited[i] = true;                
                dfs(words[i], target, words, count + 1);
                visited[i] = false;
            }
        }
    }
}