class Solution {
    public int solution(String s) {
        int answer = 0;
        
        char firstLetter = '0';
        boolean init = false;
        int oldCount = 0;
        int newCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!init) {
                firstLetter = s.charAt(i);
                init = true;
                oldCount++;
                continue;
            }
            
            if (firstLetter == s.charAt(i)) {
                oldCount++;
                continue;
            }
            
            newCount++;
            if (oldCount == newCount) {
                answer++;
                oldCount = 0;
                newCount = 0;
                init = false;
            }
        }
        
        if (init) answer++;
        
        return answer;
    }
}