class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        int strlen = s.length();
        String lowerS = s.toLowerCase();
        char first = lowerS.charAt(0);
        
        if (!Character.isDigit(first)) {
            answer.append(Character.toUpperCase(first));
        } else {
            answer.append(first);
        }
        
        for (int i = 1; i < strlen; i++) {
            if (lowerS.charAt(i) == ' ') {
                if (i != strlen -1 && lowerS.charAt(i+1) == ' ') {
                    answer.append(" ");
                    continue;
                }
                if (i != strlen -1 && !Character.isDigit(lowerS.charAt(i+1))) {
                    answer.append(" " + Character.toUpperCase(lowerS.charAt(i+1)));
                    i++;
                    continue;
                }
            }
            answer.append(lowerS.charAt(i));
        }
        return answer.toString();
    }
}