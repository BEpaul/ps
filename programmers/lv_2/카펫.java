class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int total = brown + yellow;
        int x, y;

        for (x = 3; x < 2500; x++) {
            for (y = 3; y <= x; y++) {
                if (x * y == total && (x-2) * (y-2) == yellow) {
                    answer[0] = x;
                    answer[1] = y;
                    break;
                }
            }
        }
        
        return answer;
    }
}