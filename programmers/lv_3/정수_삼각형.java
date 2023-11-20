import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        List<Integer> list = new ArrayList<>();
        list.add(triangle[0][0]);
        
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    triangle[i][0] += triangle[i - 1][0];
                    list.add(triangle[i][0]);
                } else if (j == i) {
                    triangle[i][j] += triangle[i-1][j-1];
                    list.add(triangle[i][j]);
                }
                else {
                    triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
                    list.add(triangle[i][j]);
                }
            }
        }

        int answer = Collections.max(list);
        return answer;
    }
}